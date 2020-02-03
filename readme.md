# Reece Address Book

##### Acceptance Criteria

- Address book should hold name and phone numbers of contact entries
- Users should be able to add a new contact entry to an existing address book
- Users should be able to remove an existing contact entry from an existing address book
- Users should be able to retrieve all contacts in an address book
- Users should be able to retrieve a unique set of all contacts across multiple address books
##### Assumptions
- There are already two existing address books initialised in memory
- REST endpoints for maintaining address books are not required (e.g AddressBook CRUD operations)
- Design & implement RESTful API services to meet the above requirements; a working user interface is not required. Data should only be persisted in memory. Deployment is NOT required. Please provide instructions on how to run the application. You may make reasonable assumptions if acceptance criteria are not clear.
- NOTE: Solution must be written in Java
 
##### This application is a demonstration of a few aspects of
 - Java 1.8
 - Dropwizard framework
 - REST
 - Validation/Exception Handling
 - Unit Testing
 
# How to use to start and use 

### Build
```mvn install```

### Test
```mvn test```

### Start
```java -jar reece-address-book-1.0-SNAPSHOT.jar server```


## REST ENDPOINTS

###### Add Contact
*PUT* ```http://localhost:8080/address-book/add-contact/reece-address-book-melbourne```

###### Payload: 
```
{
	"firstName": "tina",
	"lastName": "Well",
	"phoneNumber": "0424190776"
}
```

###### Delete contact
*DELETE* ```http://localhost:8080/address-book/remove-contact/reece-address-book-melbourne?phoneNumber=0424282111```

###### Get contacts for a given address book
*GET* ```http://localhost:8080/address-book/get-contacts/reece-address-book-melbourne```

###### Get a unique set of all contacts across multiple address books
*GET* ```http://localhost:8080/address-book/get-unique-contacts?addressBookIds=reece-address-book-melbourne,reece-address-book-us```
