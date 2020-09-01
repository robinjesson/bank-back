package fr.robinjesson.mybank.controller;

import fr.robinjesson.mybank.model.entities.User;
import fr.robinjesson.mybank.model.requests.UserRequest;
import fr.robinjesson.mybank.model.responses.AuthenticationResponse;
import fr.robinjesson.mybank.model.responses.UserResponse;
import fr.robinjesson.mybank.security.MyUserDetailsService;
import fr.robinjesson.mybank.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody UserRequest req) {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
            );
        } catch(BadCredentialsException e) {
            throw e;
        }

        final User user = this.userDetailsService.loadUserByUsername(req.getUsername());
        final String token = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new AuthenticationResponse(token, new UserResponse(user)));
    }
}
