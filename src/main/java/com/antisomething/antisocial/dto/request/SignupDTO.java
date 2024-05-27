package com.antisomething.antisocial.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class SignupDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Password is required")
    private String password;

    public SignupDTO() {
    }

    public SignupDTO(String firstName, String lastName, String username,
                     String email, String dateOfBirth, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.dateOfBirth = parseDateString(dateOfBirth);
        this.password = password;
    }

    public static SignupDTO from(Map<String, String> map) {
        SignupDTO dto = new SignupDTO();

        dto.setFirstName(map.get("firstName"));
        dto.setLastName(map.get("lastName"));
        dto.setUsername(map.get("username"));
        dto.setEmail(map.get("email"));
        dto.setDateOfBirth(map.get("dateOfBirth"));
        dto.setPassword(map.get("password"));

        return dto;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = parseDateString(dateOfBirth);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private LocalDate parseDateString(String date) {
        return LocalDate.parse(date);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", "SignupDTO[", "]");

        sj.add("firstName=" + firstName);
        sj.add("lastName=" + lastName);
        sj.add("username=" + username);
        sj.add("email=" + email);
        sj.add("dateOfBirth=" + dateOfBirth);
        sj.add("password=" + password);

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

        SignupDTO other = (SignupDTO) obj;

        return Objects.equals(getFirstName(), other.getFirstName()) &&
                Objects.equals(getLastName(), other.getLastName()) &&
                Objects.equals(getUsername(), other.getUsername()) &&
                Objects.equals(getEmail(), other.getEmail()) &&
                Objects.equals(getDateOfBirth(), other.getDateOfBirth()) &&
                Objects.equals(getPassword(), other.getPassword());
    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();

        result = 31 * result + getLastName().hashCode();
        result = 31 * result + Objects.hashCode(getUsername());
        result = 31 * result + Objects.hashCode(getEmail());
        result = 31 * result + Objects.hashCode(getDateOfBirth());
        result = 31 * result + Objects.hashCode(getPassword());

        return result;
    }
}
