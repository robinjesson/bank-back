package fr.robinjesson.mybank.service;

import fr.robinjesson.mybank.model.entities.Entry;
import fr.robinjesson.mybank.model.entities.User;
import fr.robinjesson.mybank.model.responses.AccountResponse;
import fr.robinjesson.mybank.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class UserService implements UserDetailsService {
    @Autowired private UserDao userDao;
    @Autowired private AccountService accountService;

    public boolean existsByUsername(String username){
        return this.userDao.existsByUsername(username);
    }
    public User save(User user){
        return this.userDao.save(user);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDao
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));
        return user;
    }

    public List<AccountResponse> regulAccount(User user) {
        List<AccountResponse> accounts = new LinkedList<AccountResponse>();
        user.getAccounts().forEach(account -> {
            List<Entry> entries = this.accountService.regulAccount(account);
            if(entries.size() < 0)
                accounts.add(new AccountResponse(account));
        });
        return accounts;
    }


}
