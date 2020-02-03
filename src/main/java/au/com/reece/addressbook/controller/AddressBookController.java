package au.com.reece.addressbook.controller;

import au.com.reece.addressbook.exception.AddressBookApplicationException;
import au.com.reece.addressbook.model.ContactInfo;
import au.com.reece.addressbook.service.AddressBookService;
import com.google.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author anjali
 */
@Path("/address-book")
public class AddressBookController {

    private static final Logger LOG = LoggerFactory.getLogger(AddressBookController.class);

    private final AddressBookService addressBookService;

    @Inject
    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get-contacts/{addressBookId}")
    public Response getContactsFromAddressBook(@PathParam("addressBookId") String addressBookId) throws AddressBookApplicationException {
        return Response.ok(addressBookService.getContactsForGivenAddressBookId(addressBookId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get-unique-contacts")
    public Response getUniqueContactsFromAddressBook(@QueryParam("addressBookIds") String addressBookIds) throws AddressBookApplicationException {
        return Response.ok(addressBookService.getAllUniqueContactsForGivenAddressBookIds(addressBookIds)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("add-contact/{addressBookId}")
    public Response addContactToAddressBook(@PathParam("addressBookId") String addressBookId, @Valid ContactInfo contactInfo) throws AddressBookApplicationException {
        return Response.ok(addressBookService.addContact(addressBookId, contactInfo)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("remove-contact/{addressBookId}")
    public Response removeContactFromAddressBook(@PathParam("addressBookId") String addressBookId, @QueryParam("phoneNumber") String phoneNumber) throws AddressBookApplicationException {
        if (phoneNumber == null|| phoneNumber.isEmpty()) {
            throw new AddressBookApplicationException(404,"Please provide the contacts phone number, to delete the record!");
        }
        return Response.ok(addressBookService.removeContact(addressBookId, phoneNumber)).build();
    }
}

