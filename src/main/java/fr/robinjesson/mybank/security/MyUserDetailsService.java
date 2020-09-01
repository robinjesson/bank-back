package fr.robinjesson.mybank.security;

import fr.robinjesson.mybank.model.entities.User;
import fr.robinjesson.mybank.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.userDao.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("No user found"));
        return user;
    }
}
