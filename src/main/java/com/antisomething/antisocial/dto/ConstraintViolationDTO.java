package com.antisomething.antisocial.dto;

import jakarta.validation.ConstraintViolation;

import java.util.Objects;
import java.util.StringJoiner;

public class ConstraintViolationDTO {

    private String field;

    private String message;

    public ConstraintViolationDTO() {
    }

    public ConstraintViolationDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public static ConstraintViolationDTO from(ConstraintViolation<?> violation) {
        ConstraintViolationDTO dto = new ConstraintViolationDTO();

        dto.setField(violation.getPropertyPath().toString());
        dto.setMessage(violation.getMessage());

        return dto;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", "ConstraintViolationDTO[", "]");

        sj.add("field=" + field);
        sj.add("message=" + message);

        return sj.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        ConstraintViolationDTO other = (ConstraintViolationDTO) obj;

        return Objects.equals(getField(), other.getField()) &&
                Objects.equals(getMessage(), other.getMessage());
    }

    @Override
    public int hashCode() {
        int result = getField().hashCode();

        result = 31 * result + getMessage().hashCode();

        return result;
    }
}
