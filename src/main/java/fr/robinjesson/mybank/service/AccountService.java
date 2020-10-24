package fr.robinjesson.mybank.service;

import fr.robinjesson.mybank.model.entities.Account;
import fr.robinjesson.mybank.model.entities.Entry;
import fr.robinjesson.mybank.model.entities.EntryPeriod;
import fr.robinjesson.mybank.repository.AccountDao;
import fr.robinjesson.mybank.repository.EntryDao;
import fr.robinjesson.mybank.repository.EntryPeriodDao;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private EntryPeriodService entryPeriodService;

    public List<Account> findByUserId(Long id) {
        return this.accountDao.findByUserId(id);
    }

    public List<Entry> regulAccount(Account account) {
        List<Entry> entries = new LinkedList<>();
        account.getEntryPeriods().forEach(entryPeriod -> {
            entries.addAll(this.entryPeriodService.regulEntryPeriod(entryPeriod, account));
        });
        return entries;
    }

    public void modifyLastUpdate(Account account) {
        account.setLastUpdate(LocalDate.now());
        this.accountDao.save(account);
    }

    public Set<EntryPeriod> getAllEntryPeriodByAccountId(Account account) {
        return account.getEntryPeriods();
    }

    public Optional<Account> findById(Long id) {
        return this.accountDao.findById(id);
    }

    public Account save(Account account) {
        return this.accountDao.save(account);
    }

    public void delete(Long id) {
        this.accountDao.deleteById(id);
    }


}
