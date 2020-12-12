package fr.robinjesson.mybank.service;

import fr.robinjesson.mybank.model.entities.Account;
import fr.robinjesson.mybank.model.entities.Entry;
import fr.robinjesson.mybank.model.entities.EntryPeriod;
import fr.robinjesson.mybank.repository.EntryDao;
import fr.robinjesson.mybank.repository.EntryPeriodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class EntryPeriodService {
    @Autowired private EntryPeriodDao entryPeriodDao;
    @Autowired private EntryService entryService;

    public EntryPeriod save(EntryPeriod entryPeriod) {
        return this.entryPeriodDao.save(entryPeriod);
    }

    public Optional<EntryPeriod> findById(Long id) {
        return this.entryPeriodDao.findById(id);
    }

    public List<EntryPeriod> findAll() {
        return this.entryPeriodDao.findAll();
    }

    public void deleteById(Long id) {
        this.entryPeriodDao.deleteById(id);
    }

    public List<Entry> regulEntryPeriod(EntryPeriod entryPeriod, Account account) {
        List<Entry> entries = new LinkedList<>();
        Entry entry = this.entryService.getLastEntryByPeriodId(entryPeriod.getId()).orElseGet(() -> {
            System.out.println(entryPeriod);
            if(entryPeriod.getBegin().compareTo(LocalDate.now()) <= 0) {
                Entry e = new Entry(entryPeriod.getTitle(), entryPeriod.getAmount(), entryPeriod.getBegin(), entryPeriod, entryPeriod.getAccount());
                //e = this.entryService.save(e, account);
                entries.add(e);
                return e;
            }
            return null;
        });
        if(entry != null) {
            while (entry.getDate().isBefore(LocalDate.now()) &&
                    entry.getDate().isBefore(entryPeriod.getEnd())) {
                LocalDate next = null;
                switch (entryPeriod.getPeriodUnit()) {
                    case Once:
                        next = LocalDate.now();
                        break;
                    case Day:
                        next = entry.getDate().plus(entryPeriod.getPeriod(), ChronoUnit.DAYS);
                        break;
                    case Week:
                        next = entry.getDate().plus(entryPeriod.getPeriod(), ChronoUnit.WEEKS);
                        break;
                    case Month:
                        next = entry.getDate().plus(entryPeriod.getPeriod(), ChronoUnit.MONTHS);
                        break;
                    case Year:
                        next = entry.getDate().plus(entryPeriod.getPeriod(), ChronoUnit.YEARS);
                        break;
                }

                if(next.compareTo(LocalDate.now()) <= 0 &&
                        next.compareTo(entryPeriod.getEnd()) <= 0) {
                    entry = new Entry(entry.getTitle(), entry.getAmount(), next, entryPeriod, entry.getAccount());
                    //entry = this.entryService.save(entry, account);
                    entries.add(entry);
                } else {
                    break;
                }

            }
        }
        return entries;
    }

}
