package fr.robinjesson.mybank.repository;

import fr.robinjesson.mybank.model.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntryDao extends JpaRepository<Entry, Long> {

    public List<Entry> findByAccountId(Long id);

    @Query("SELECT e FROM Entry e WHERE e.entryPeriod.id = ?1 AND e.date = (SELECT max(e.date) FROM Entry e WHERE e.entryPeriod.id = ?1 )")
    public Optional<Entry> getLastEntryByPeriodId(Long periodId);

}
