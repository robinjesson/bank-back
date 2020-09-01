package fr.robinjesson.mybank.model.requests;

public class UpdateFieldRequest {
    private String field;
    private Object value;

    public UpdateFieldRequest(Long id, String field, Object value) {
        this.field = field;
        this.value = value;
    }

    public UpdateFieldRequest() {
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}
