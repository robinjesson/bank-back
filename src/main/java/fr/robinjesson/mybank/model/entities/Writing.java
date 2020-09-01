package fr.robinjesson.mybank.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Writing {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Double amount;

    @NonNull
    private LocalDate beginDate;

    @Nullable
    private LocalDate endDate;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(length = 32, columnDefinition = "varchar(32) default 'Once'")
    private EWritingType writingType;

    @JsonIgnore
    @ManyToOne
    @NonNull
    @JoinColumn(name = "account_id")
    private Account account;

    public Writing() {
    }

    public Writing(@NonNull Long id, @NonNull Double amount, @NonNull LocalDate beginDate, @Nullable LocalDate endDate, @NonNull EWritingType writingType, @NonNull Account account) {
        this.id = id;
        this.amount = amount;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.writingType = writingType;
        this.account = account;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public Double getAmount() {
        return amount;
    }

    public void setAmount(@NonNull Double amount) {
        this.amount = amount;
    }

    @NonNull
    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(@NonNull LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    @Nullable
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@Nullable LocalDate endDate) {
        this.endDate = endDate;
    }

    @NonNull
    public EWritingType getWritingType() {
        return writingType;
    }

    public void setWritingType(@NonNull EWritingType writingType) {
        this.writingType = writingType;
    }

    @NonNull
    public Account getAccount() {
        return account;
    }

    public void setAccount(@NonNull Account account) {
        this.account = account;
    }
}
