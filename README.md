# AddressBook Application

AddressBook requirements:
- Add a contact to addressbook
- Get a contact from addressbook
- Get unique contacts from two addressbooks

Technologies Used:
- Spring Boot
- Maven build tool
- DB: H2 Database

Structure:
src/main: 
 Package info : com.pwc.address-book
 AddressBookApplication.java 	-- Spring boot application class
 
 AddressBook related classes :
    AddressBook.java			-- Model class
	Contacts.java				-- Model class
    AddressBookController.java     -- Controller class
    AddressBookRepository.java     -- Repository for the model
    AddressBookService.java        -- Business Service Class

 Custom Exception classes: 
    AddressBookNotFoundException.java

 resources:				-- Application properties
    application.properties

 test:  					-- Test classes for Junit.
    AddressBookApplicationTest.java
    AddressBookControllerTest.java

To build and run:
	run .mvn install command