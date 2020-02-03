package au.com.reece.addressbook.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import javax.validation.constraints.Size;

/**
 *
 * @author anjali
 */
public class ContactInfo {

    @NotNull(message = "Please provide first Name")
    @Size(min = 1, max = 25)
    @Pattern(regexp = "^[a-zA-Z\\s]+", message = "First name must only consist of alphabets with the exception of spaces")
    private String firstName;

    @NotNull(message = "Please provide last Name")
    @Pattern(regexp = "^[a-zA-Z]+", message = "Last name must only consist of alphabets")
    private String lastName;

    @NotNull(message = "Please provide phone numbers")
    @Pattern(regexp = "^[d]*$", message = "Numers only")
    @Size(min = 10, max = 12)
    String phoneNumber;

    public ContactInfo() {
    }
    
    public ContactInfo(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContactInfo contact = (ContactInfo) o;
        return Objects.equals(firstName, contact.firstName)
                && Objects.equals(lastName, contact.lastName)
                && Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber);
    }
}

