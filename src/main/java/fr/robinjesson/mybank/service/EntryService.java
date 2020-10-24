package fr.robinjesson.mybank.service;

import fr.robinjesson.mybank.model.entities.Account;
import fr.robinjesson.mybank.model.entities.Entry;
import fr.robinjesson.mybank.repository.AccountDao;
import fr.robinjesson.mybank.repository.EntryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EntryService {
    @Autowired
    private EntryDao entryDao;

    @Autowired
    private AccountService accountService;

    public Optional<Account> findAccountById(Long id){
        return this.accountService.findById(id);
    }

    public Optional<Entry> findById(Long id) {
        return this.entryDao.findById(id);
    }

    /**
     * Saves in database the writing and updates the account total.
     * @param w  Writing to save
     * @param a  Account to update
     * @return Saved writing
     */
    public Entry save(Entry w, Account a) {
        a.setTotal(a.getTotal() - w.getAmount());
        a.setLastUpdate(LocalDate.now());
        this.accountService.save(a);
        return this.entryDao.save(w);
    }

    /**
     * Deletes a writing and updates the account total.
     * @param id  Writing id to delete
     * @throws NoSuchElementException
     */
    public void delete(Long id) throws NoSuchElementException{
        try {
            Entry w = this.entryDao.findById(id).orElseThrow();
            Account a = this.findAccountById(w.getAccount().getId()).orElseThrow();
            a.setTotal(a.getTotal() + w.getAmount());
            this.accountService.save(a);
            this.entryDao.delete(w);
        } catch(NoSuchElementException e) {
            throw e;
        }
    }

    public Optional<Entry> getLastEntryByPeriodId(Long periodId) {
        return this.entryDao.getLastEntryByPeriodId(periodId);
    }
}
