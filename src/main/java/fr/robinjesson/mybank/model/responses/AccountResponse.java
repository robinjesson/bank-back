package fr.robinjesson.mybank.model.responses;

import fr.robinjesson.mybank.model.entities.Account;

public class AccountResponse {
    private Long id;
    private String name;
    private Integer maxRed;
    private Integer minGreen;
    private Double total;

    public AccountResponse() {
    }

    public AccountResponse(Long id, String name, Integer maxRed, Integer minGreen, Double total) {
        this.id = id;
        this.name = name;
        this.maxRed = maxRed;
        this.minGreen = minGreen;
        this.total = total;
    }

    public AccountResponse(Account account) {
        this(account.getId(), account.getName(), account.getMaxRed(), account.getMinGreen(), account.getTotal());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMaxRed() {
        return maxRed;
    }

    public Integer getMinGreen() {
        return minGreen;
    }

    public Double getTotal() {
        return total;
    }
}
