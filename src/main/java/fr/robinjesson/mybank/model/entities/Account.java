package fr.robinjesson.mybank.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @JsonIgnore
    @ManyToOne
    @NonNull
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "account")
    private Set<Writing> writings;

    @NonNull
    @Column(nullable = false, columnDefinition = "int default 500")
    private Integer maxRed = 500;

    @NonNull
    @Column(nullable = false, columnDefinition = "int default 1000")
    private Integer minGreen = 1000;

    @NonNull
    private Double total;

    public Account(@NonNull String name, @NonNull User user, @NonNull Double total) {
        this.name = name;
        this.user = user;
        this.total = total;
    }

    public Account() {
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public User getUser() {
        return user;
    }

    public void setUser(@NonNull User user) {
        this.user = user;
    }

    public Set<Writing> getWritings() {
        return writings;
    }

    public void setWritings(Set<Writing> writings) {
        this.writings = writings;
    }

    @NonNull
    public Integer getMaxRed() {
        return maxRed;
    }

    public void setMaxRed(@NonNull Integer maxRed) {
        this.maxRed = maxRed;
    }

    @NonNull
    public Integer getMinGreen() {
        return minGreen;
    }

    public void setMinGreen(@NonNull Integer minGreen) {
        this.minGreen = minGreen;
    }

    @NonNull
    public Double getTotal() {
        return total;
    }

    public void setTotal(@NonNull Double total) {
        this.total = total;
    }
}
