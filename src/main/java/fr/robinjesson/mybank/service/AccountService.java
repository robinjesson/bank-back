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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private EntryPeriodService entryPeriodService;

    @Autowired
    private EntryService entryService;

    public List<Account> findByUserId(Long id) {
        return this.accountDao.findByUserId(id);
    }

    public List<Entry> regulAccount(Account account) {
        List<Entry> entries = new LinkedList<>();
        account.getEntryPeriods().forEach(entryPeriod -> {
            entries.addAll(this.entryPeriodService.regulEntryPeriod(entryPeriod, account));
        });
        return entries.stream().map(entry -> this.entryService.save(entry, account)).collect(Collectors.toList());
    }

    public void modifyLastUpdate(Account account) {
        account.setLastUpdate(LocalDate.now());
        this.accountDao.save(account);
    }

    public Set<EntryPeriod> getAllEntryPeriodByAccountId(Account account) {
        return account.getEntryPeriods();
    }
    public Set<Entry> getAllEntriesByAccount(Account account) {
        return account.getEntries();
    }
    public List<Entry> getAllEntriesByAccountByYearMonth(Account account, Integer year, Integer month) {
        return account.getEntries().stream()
                .filter(e -> e.getDate().getYear() == year && e.getDate().getMonthValue() == month)
                .sorted(Comparator.comparing(Entry::getDate))
                .collect(Collectors.toList());
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
