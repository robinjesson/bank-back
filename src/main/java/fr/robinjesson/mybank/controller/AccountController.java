package fr.robinjesson.mybank.controller;

import fr.robinjesson.mybank.model.entities.Account;
import fr.robinjesson.mybank.model.entities.User;
import fr.robinjesson.mybank.model.requests.AccountAddRequest;
import fr.robinjesson.mybank.model.requests.UpdateFieldRequest;
import fr.robinjesson.mybank.model.responses.AccountResponse;
import fr.robinjesson.mybank.model.responses.EntryResponse;
import fr.robinjesson.mybank.repository.AccountDao;
import fr.robinjesson.mybank.repository.UserDao;
import fr.robinjesson.mybank.repository.EntryDao;
import fr.robinjesson.mybank.service.AccountService;
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
    private AccountService accountService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EntryDao entryDao;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new AccountResponse(this.accountService.findById(id).orElseThrow()));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/entryPeriods")
    public ResponseEntity<?> getAccountEntryPeriods(@PathVariable Long id) {
        try {

            Account account = this.accountService.findById(id).orElseThrow();
            return ResponseEntity.ok(this.accountService.getAllEntryPeriodByAccountId(account));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/entries/{year}/{month}")
    public ResponseEntity<?> getAccountEntryPeriodsDate(@PathVariable Long id,@PathVariable Integer year,@PathVariable Integer month) {
        try {
            Account account = this.accountService.findById(id).orElseThrow();
            return ResponseEntity.ok(this.accountService.getAllEntriesByAccountByYearMonth(account, year, month)
                    .stream().map(entry -> new EntryResponse(entry)).collect(Collectors.toList()));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/entries")
    public ResponseEntity<?> getAccountEntries(@PathVariable Long id) {
        try {

            Account account = this.accountService.findById(id).orElseThrow();
            return ResponseEntity.ok(this.accountService.getAllEntriesByAccount(account)
                    .stream().map(entry -> new EntryResponse(entry)).collect(Collectors.toList()));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody AccountAddRequest req) {
        try {
            User user = this.userDao.findById(req.getUserId()).orElseThrow();
            Account account = new Account(req.getName(), user, req.getTotal());
            return ResponseEntity.ok(this.accountService.save(account));
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody UpdateFieldRequest req) {
        try {
            Account account = accountService.findById(id).orElseThrow();
            switch (req.getField()) {
                default:
                    throw new NoSuchFieldException();
                case "name":
                    account.setName((String) req.getValue());
                    break;
                case "total":
                    account.setTotal((Double) req.getValue());
                    break;
            }
            return ResponseEntity.ok(new AccountResponse(accountService.save(account)));
        } catch (NoSuchFieldException | NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/refresh")
    public ResponseEntity<?> refreshAccount(@PathVariable Long id) {
        try {
            Account account = accountService.findById(id).orElseThrow();
            System.out.println(account);
            this.accountService.regulAccount(account);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
