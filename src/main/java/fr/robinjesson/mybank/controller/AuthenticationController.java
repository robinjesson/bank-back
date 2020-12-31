package fr.robinjesson.mybank.controller;

import fr.robinjesson.mybank.model.entities.Entry;
import fr.robinjesson.mybank.model.entities.User;
import fr.robinjesson.mybank.model.requests.LoginRequest;
import fr.robinjesson.mybank.model.responses.AuthenticationResponse;
import fr.robinjesson.mybank.model.responses.UserResponse;
import fr.robinjesson.mybank.service.UserService;
import fr.robinjesson.mybank.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest req) {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
            );
        } catch(BadCredentialsException e) {
            return new ResponseEntity<>("Bad credantials.", HttpStatus.UNAUTHORIZED);
        }

        final User user = this.userDetailsService.loadUserByUsername(req.getUsername());
        final String token = jwtUtil.generateToken(user);
        user.setLastConnection();
        this.userService.save(user);

        return ResponseEntity.ok(new AuthenticationResponse(token, new UserResponse(user)));
    }
}
