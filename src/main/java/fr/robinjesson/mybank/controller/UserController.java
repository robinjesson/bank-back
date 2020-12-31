package fr.robinjesson.mybank.controller;

import fr.robinjesson.mybank.controller.exception.FieldNotFound;
import fr.robinjesson.mybank.model.entities.Entry;
import fr.robinjesson.mybank.model.entities.User;
import fr.robinjesson.mybank.model.requests.UpdateFieldRequest;
import fr.robinjesson.mybank.model.requests.LoginRequest;
import fr.robinjesson.mybank.model.responses.AccountResponse;
import fr.robinjesson.mybank.model.responses.UserResponse;
import fr.robinjesson.mybank.repository.AccountDao;
import fr.robinjesson.mybank.repository.UserDao;
import fr.robinjesson.mybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDao dao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(dao.findAll().stream().map(user -> new UserResponse(user)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user;
        try {
            user = dao.findById(id).orElseThrow();
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("No user found with id " + id + ".", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new UserResponse(user));
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<?> getUserAccounts(@PathVariable Long id) {
        try {
            dao.findById(id).orElseThrow();
            return ResponseEntity.ok(this.accountDao.findByUserId(id).stream()
                    .map(account -> new AccountResponse(account))
                    .collect(Collectors.toList()));
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> addUser(@RequestBody LoginRequest req) {
        if(this.dao.existsByUsername(req.getUsername()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        String sha512 = this.encryptThisString(req.getPassword());
        String bcrypt = passwordEncoder.encode(sha512);
        System.out.println(bcrypt);
        User user = new User(req.getUsername(), bcrypt);
        return ResponseEntity.ok(new UserResponse(dao.save(user)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UpdateFieldRequest req) {
        User user;
        try {
            user = dao.findById(id).orElseThrow();
            switch (req.getField()) {
                default:
                    throw new NoSuchFieldException(req.getField());
                case "password":
                    String sha512 = this.encryptThisString((String)req.getValue());
                    String bcrypt = passwordEncoder.encode(sha512);
                    user.setPassword(bcrypt);
                    break;
            }
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("No user found with id " + id + ".", HttpStatus.NOT_FOUND);
        } catch(NoSuchFieldException e) {
            return new FieldNotFound(req.getField(), "USER");
        }
        return ResponseEntity.ok(new UserResponse(dao.save(user)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUser(@PathVariable Long id) {
        try {
            dao.delete(dao.findById(id).orElseThrow());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("No user found with id " + id + ".", HttpStatus.NOT_FOUND);
        }
 
    }

    private String encryptThisString(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> regalUserAccounts(@PathVariable Long id) {
        User user;
        try {
            user = dao.findById(id).orElseThrow();

            List<AccountResponse> accountsRegul = this.userService.regulAccount(user);

            return new ResponseEntity<>(accountsRegul, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("No user found with id " + id + ".", HttpStatus.NOT_FOUND);
        }
    }
}
