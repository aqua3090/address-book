package au.com.reece.addressbook.service;

import au.com.reece.addressbook.exception.AddressBookApplicationException;
import au.com.reece.addressbook.model.ContactInfo;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import static java.util.Collections.emptySet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author anjali
 */

public final class AddressBookService {

    public static final String REECE_ADDRESS_BOOK_US = "reece-address-book-us";
    public static final String REECE_ADDRESS_BOOK_MELBOURNE = "reece-address-book-melbourne";
    private final Map<String, List<ContactInfo>> addressBooks = new HashMap<>();

    public AddressBookService() {
        addressBooks.put(REECE_ADDRESS_BOOK_US, new ArrayList<>());
        addressBooks.put(REECE_ADDRESS_BOOK_MELBOURNE, new ArrayList<>());

        try {
            this.addContact(REECE_ADDRESS_BOOK_US, new ContactInfo("Ana", "Well", "013472556228"));
            this.addContact(REECE_ADDRESS_BOOK_US, new ContactInfo("Satya", "V", "013342556227"));

            this.addContact(REECE_ADDRESS_BOOK_MELBOURNE, new ContactInfo("John", "XYZ", "0424282111"));
            this.addContact(REECE_ADDRESS_BOOK_MELBOURNE, new ContactInfo("Sanya", "ZZZ", "0424282113"));
        } catch (AddressBookApplicationException ex) {
        }
    }

    /***
     * AddContact function will add contacts to existing address books and 
     * it will throw and exception if the user tries to add the same phone 
     * number to the existing address book.
     * 
     * @param addressBookId
     * @param contact
     * @return
     * @throws AddressBookApplicationException 
     */
    public ContactInfo addContact(String addressBookId, ContactInfo contact) throws AddressBookApplicationException {
        Optional<ContactInfo> contactToBeAdded = addressBooks.get(addressBookId).stream()
                .filter(c -> c.getPhoneNumber().equals(contact.getPhoneNumber())).findFirst();

        if (contactToBeAdded.isPresent()) {
            throw new AddressBookApplicationException(404,"The given contact phoneNumber already exists!");
        } else {
            addressBooks.get(addressBookId).add(contact);
        }
        return contact;
    }

    /***
     * Remove Contact will remove the contact info from the existing address book for the given phone number. 
     * 
     * @param addressBookId
     * @param phoneNumber
     * @return
     * @throws AddressBookApplicationException 
     */
    public ContactInfo removeContact(String addressBookId, final String phoneNumber) throws AddressBookApplicationException {
        if (!addressBooks.containsKey(addressBookId)) {
            throw new AddressBookApplicationException(404,"AddressBook with the given Id does not exist");
        }
        Optional<ContactInfo> contactToBeRemoved = addressBooks.get(addressBookId).stream()
                .filter(contact -> contact.getPhoneNumber().equals(phoneNumber)).findFirst();
        if (contactToBeRemoved.isPresent()) {
            addressBooks.get(addressBookId).remove(contactToBeRemoved.get());
            return contactToBeRemoved.get();
        } else {
            throw new AddressBookApplicationException(404,"The given contact does not exist!");
        }
    }

    /**
     * Retrieves all the contacts for the given address book id, provided it exists. 
     * 
     * @param addressBookId
     * @return
     * @throws AddressBookApplicationException 
     */
    public List<ContactInfo> getContactsForGivenAddressBookId(String addressBookId) throws AddressBookApplicationException {
        if (!addressBooks.containsKey(addressBookId)) {
            throw new AddressBookApplicationException(404,"AddressBook with the given Id does not exist");
        }
        return addressBooks.get(addressBookId);
    }

    /***
     * Retrieves all unique contacts for the comma separated string of address book id's.
     * This also takes into account if the user passes null/empty strings in the parameter.
     * 
     * @param addressBookIds
     * @return 
     */
    public List<ContactInfo> getAllUniqueContactsForGivenAddressBookIds(String  addressBookIds) {
        
        Set<String> addressBookIdSet = addressBookIds == null ? emptySet() : Sets.newHashSet(
                                Splitter.on(",").trimResults().omitEmptyStrings().split(addressBookIds));
        
        List<ContactInfo> contacts = new ArrayList<>();
        if (addressBookIdSet.isEmpty()) {
            addressBooks.values().forEach(contacts::addAll);
        } else {
            addressBookIdSet.stream().forEach(id -> {
                if (addressBooks.containsKey(id)) {
                    contacts.addAll(addressBooks.get(id));
                }
            });
        }
        return contacts.stream().distinct().collect(Collectors.toList());
    }

}
