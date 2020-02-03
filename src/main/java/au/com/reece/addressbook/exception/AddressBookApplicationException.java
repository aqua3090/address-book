package au.com.reece.addressbook.exception;

/**
 *
 * @author anjali
 */
public class AddressBookApplicationException extends Exception {

    Integer status;
    String developerMessage;

    public AddressBookApplicationException() {
    }

    public AddressBookApplicationException(int status,
            String developerMessage) {
        this.status = status;
        this.developerMessage = developerMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
