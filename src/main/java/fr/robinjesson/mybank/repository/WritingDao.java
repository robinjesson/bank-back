package fr.robinjesson.mybank.repository;

import fr.robinjesson.mybank.model.entities.Writing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WritingDao extends JpaRepository<Writing, Long> {

    public List<Writing> findByAccountId(Long id);


}
