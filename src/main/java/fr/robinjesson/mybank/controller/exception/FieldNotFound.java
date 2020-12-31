package fr.robinjesson.mybank.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FieldNotFound extends ResponseEntity<String> {
    /**
     * Bad request response giving the filedName and table.
     * @param fieldName
     * @param table
     */
    public FieldNotFound(String fieldName, String table) {
        super("The field '" + fieldName + "' in table '" + table + "' doesn't exist.", HttpStatus.BAD_REQUEST);
    }
}
