package fr.robinjesson.mybank.controller;

import fr.robinjesson.mybank.model.entities.Account;
import fr.robinjesson.mybank.model.entities.User;
import fr.robinjesson.mybank.model.requests.AccountAddRequest;
import fr.robinjesson.mybank.model.requests.UpdateFieldRequest;
import fr.robinjesson.mybank.model.responses.AccountResponse;
import fr.robinjesson.mybank.model.responses.WritingResponse;
import fr.robinjesson.mybank.repository.AccountDao;
import fr.robinjesson.mybank.repository.UserDao;
import fr.robinjesson.mybank.repository.WritingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private WritingDao writingDao;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new AccountResponse(this.accountDao.findById(id).orElseThrow()));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/writings")
    public ResponseEntity<?> getAccountWritings(@PathVariable Long id) {
        try {
            this.accountDao.findById(id).orElseThrow();
            return ResponseEntity.ok(this.writingDao.findByAccountId(id).stream()
                    .map(w -> new WritingResponse(w))
                    .collect(Collectors.toList()));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody AccountAddRequest req) {
        try {
            User user = this.userDao.findById(req.getUserId()).orElseThrow();
            Account account = new Account(req.getName(), user, req.getTotal());
            return ResponseEntity.ok(this.accountDao.save(account));
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody UpdateFieldRequest req) {
        try {
            Account account = accountDao.findById(id).orElseThrow();
            switch (req.getField()) {
                default:
                    throw new NoSuchFieldException();
                case "name":
                    account.setName((String) req.getValue());
                    break;
                case "maxRed":
                    account.setMaxRed((Integer)req.getValue());
                    break;
                case "maxGreen":
                    account.setMinGreen((Integer)req.getValue());
                    break;
                case "total":
                    account.setTotal((Double) req.getValue());
                    break;
            }
            return ResponseEntity.ok(new AccountResponse(accountDao.save(account)));
        } catch (NoSuchFieldException | NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
