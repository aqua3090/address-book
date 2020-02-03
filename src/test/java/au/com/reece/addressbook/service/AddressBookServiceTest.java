package au.com.reece.addressbook.service;

import au.com.reece.addressbook.exception.AddressBookApplicationException;
import au.com.reece.addressbook.model.ContactInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anjali
 */
public class AddressBookServiceTest {

    private AddressBookService addressBookService;
    private ContactInfo contactInfo1;
    private ContactInfo contactInfo2;

    public AddressBookServiceTest() {
    }

    @Before
    public void setUp() {
        addressBookService = new AddressBookService();
        //creating duplicate record that exists in reece-address-book-us
        contactInfo1 = new ContactInfo("Ana", "Well", "013472556228");
        contactInfo2 = new ContactInfo("Sany", "S", "0134725560");

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addContact method, of class AddressBookService.
     *
     * @throws au.com.reece.addressbook.exception.AddressBookApplicationException
     */
    @Test
    public void testAddContactToAddressBook() throws AddressBookApplicationException {
        ContactInfo addedContact = addressBookService.addContact(AddressBookService.REECE_ADDRESS_BOOK_MELBOURNE, contactInfo1);
        assertEquals(contactInfo1, addedContact);
        assertEquals(3, addressBookService.getContactsForGivenAddressBookId(AddressBookService.REECE_ADDRESS_BOOK_MELBOURNE).size());
    }

    /**
     * Negative Test of adding duplicate content to address book.
     */
    @Test
    public void testAddContactToAddressBookWithDuplicatePhoneNumber() {
        try {
            addressBookService.addContact(AddressBookService.REECE_ADDRESS_BOOK_US, contactInfo1);
        } catch (AddressBookApplicationException ex) {
            assertEquals(ex.getDeveloperMessage(), "The given contact phoneNumber already exists!");
        }
    }

    /**
     * Test of removeContact method, of class AddressBookService.
     *
     * @throws au.com.reece.addressbook.exception.AddressBookApplicationException
     */
    @Test
    public void testDeleteContactFromAddressBook() throws AddressBookApplicationException {
        addressBookService.addContact(AddressBookService.REECE_ADDRESS_BOOK_MELBOURNE, contactInfo1);
        ContactInfo removedContact = addressBookService.removeContact(AddressBookService.REECE_ADDRESS_BOOK_MELBOURNE, "013472556228");
        assertEquals(contactInfo1, removedContact);
        assertEquals(2, addressBookService.getContactsForGivenAddressBookId(AddressBookService.REECE_ADDRESS_BOOK_MELBOURNE).size());
    }

    /**
     * Test of getContactsForGivenAddressBookId method, of class AddressBookService.
     *
     * @throws au.com.reece.addressbook.exception.AddressBookApplicationException
     */
    @Test
    public void testRetriveAllContactsFromAddressBook() throws AddressBookApplicationException {
        assertEquals(2, addressBookService.getContactsForGivenAddressBookId(AddressBookService.REECE_ADDRESS_BOOK_MELBOURNE).size());
        assertEquals(2, addressBookService.getContactsForGivenAddressBookId(AddressBookService.REECE_ADDRESS_BOOK_US).size());
    }

    /**
     * Test of getAllUniqueContactsForGivenAddressBookIds method, of class AddressBookService.
     *
     * @throws au.com.reece.addressbook.exception.AddressBookApplicationException
     */
    @Test
    public void testRetrieveUniqueContactsFromAddressBook() throws AddressBookApplicationException {
        addressBookService.addContact(AddressBookService.REECE_ADDRESS_BOOK_MELBOURNE, contactInfo1);
        addressBookService.addContact(AddressBookService.REECE_ADDRESS_BOOK_US, contactInfo2);
        StringBuilder addressBookIdsBuilder = new StringBuilder();
        addressBookIdsBuilder.append(AddressBookService.REECE_ADDRESS_BOOK_MELBOURNE + " , " + " , " + AddressBookService.REECE_ADDRESS_BOOK_US);
        
        assertEquals(3, addressBookService.getAllUniqueContactsForGivenAddressBookIds(AddressBookService.REECE_ADDRESS_BOOK_US).size());
        assertEquals(5, addressBookService.getAllUniqueContactsForGivenAddressBookIds(addressBookIdsBuilder.toString()).size());
        assertEquals(5, addressBookService.getAllUniqueContactsForGivenAddressBookIds(null).size());
    }
}
