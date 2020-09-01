package fr.robinjesson.mybank.repository;

import fr.robinjesson.mybank.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);
    public boolean existsByUsername(String username);
}

