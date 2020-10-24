package fr.robinjesson.mybank.repository;

import fr.robinjesson.mybank.model.entities.EntryPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryPeriodDao extends JpaRepository<EntryPeriod, Long> {
}
