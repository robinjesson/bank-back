package fr.robinjesson.mybank.model.responses;

import fr.robinjesson.mybank.model.entities.EWritingType;
import fr.robinjesson.mybank.model.entities.Writing;

import java.time.LocalDate;

public class WritingResponse {
    private Long id;
    private Double amount;
    private LocalDate beginDate;
    private LocalDate endDate;
    private EWritingType writingType;

    public WritingResponse(Long id, Double amount, LocalDate beginDate, LocalDate endDate, EWritingType writingType) {
        this.id = id;
        this.amount = amount;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.writingType = writingType;
    }

    public WritingResponse() {
    }

    public WritingResponse(Writing w) {
        this(w.getId(), w.getAmount(), w.getBeginDate(), w.getEndDate(), w.getWritingType());
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public EWritingType getWritingType() {
        return writingType;
    }
}
