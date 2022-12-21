package de.dn.petstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ErrorResponse implements Serializable {
    @JsonProperty
    @NotNull
    private int code;

    @JsonProperty
    @NotNull
    private String message;

    @JsonIgnore
    @NotNull
    private HttpStatus status;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.code = status.value();
        this.message = message;
    }

    public ErrorResponse(HttpStatus status) {
        this.status = status;
        this.code = status.value();
        this.message = status.getReasonPhrase();
    }

    public int getCode() {
        return this.status.value();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
