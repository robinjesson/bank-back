package fr.robinjesson.mybank.controller;


import fr.robinjesson.mybank.model.entities.Account;
import fr.robinjesson.mybank.model.entities.Entry;
import fr.robinjesson.mybank.model.entities.EntryPeriod;
import fr.robinjesson.mybank.model.entities.PeriodUnit;
import fr.robinjesson.mybank.model.requests.EntryRequest;
import fr.robinjesson.mybank.model.responses.EntryResponse;
import fr.robinjesson.mybank.service.AccountService;
import fr.robinjesson.mybank.service.EntryPeriodService;
import fr.robinjesson.mybank.service.EntryService;
import fr.robinjesson.mybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/entries")
public class EntryController {
    @Autowired private EntryService entryService;
    @Autowired private EntryPeriodService entryPeriodService;
    @Autowired private AccountService accountService;

    @PostMapping
    public ResponseEntity<?> addEntry(@RequestBody EntryRequest req) {
        LocalDate now = LocalDate.now();
        Account account = this.accountService.findById(req.getAccountId()).orElseThrow();
        Entry entry = null;
        System.out.println(req);
        if(req.getPeriodUnit() == null
                || (req.getPeriodUnit() == PeriodUnit.Once && req.getDate().compareTo(now) <= 0)) {
            entry = this.entryService.save(new Entry(req.getTitle(), req.getAmount(), now, account), account);
        }
        else {

            EntryPeriod ep = new EntryPeriod(req.getTitle(),req.getAmount(),req.getDate(),
                    req.getEnd() == null ? LocalDate.of(9999, 12,31): req.getEnd(), req.getPeriodUnit(),
                    req.getPeriod() == null ? 0 : req.getPeriod() , account);
            ep = this.entryPeriodService.save(ep);


            if(ep.getBegin().compareTo(now) <= 0) {
                return ResponseEntity.ok(this.entryPeriodService.regulEntryPeriod(ep,account)
                        .stream().map(entry1 -> new EntryResponse(entry1)).collect(Collectors.toList()));
            }
        }
        if(entry != null)
            return ResponseEntity.ok(new EntryResponse(entry));
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public void deleteEntry(@PathVariable Long id) {
        this.entryService.delete(id);

    }

}
