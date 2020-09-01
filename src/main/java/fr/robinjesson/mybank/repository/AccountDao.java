package fr.robinjesson.mybank.repository;

import fr.robinjesson.mybank.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {
    public List<Account> findByUserId(Long id);
}
