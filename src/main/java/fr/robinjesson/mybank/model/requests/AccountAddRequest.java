package fr.robinjesson.mybank.model.requests;

public class AccountAddRequest {
    private String name;
    private Long userId;
    private Double total;

    public AccountAddRequest() {
    }

    public AccountAddRequest(String name, Long userId, Double total) {
        this.name = name;
        this.userId = userId;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public Long getUserId() {
        return userId;
    }

    public Double getTotal() {
        return total;
    }
}
