package com.pwc.address_book.controller;

import com.pwc.address_book.model.AddressBook;
import com.pwc.address_book.model.Contacts;
import com.pwc.address_book.exceptions.AddressBookNotFoundException;
import com.pwc.address_book.requests.AddressBookRequest;
import com.pwc.address_book.requests.ContactRequest;
import com.pwc.address_book.response.AddressBookResponse;
import com.pwc.address_book.response.ContactResponse;
import com.pwc.address_book.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/address-book")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @PostMapping(path = "createAddressBook")
    public void createAddressBook(@RequestBody AddressBookRequest reservationRequest) {

        AddressBook addressBook = new AddressBook();
        addressBook.setAddressBookName(reservationRequest.getAddressBookName());

        for(ContactRequest createContactRequest : reservationRequest.getContacts()) {
            Contacts contact = new Contacts();
            contact.setName(createContactRequest.getName());
            contact.setPhoneNumber(createContactRequest.getPhoneNumber());

            addressBook.getContacts().add(contact);
        }

        addressBookService.save(addressBook);
    }

    @GetMapping(path = "getAddressBook")
    public AddressBookResponse getAddressBook(@RequestParam(name = "addressBookId") Long addressBookId) {

        Optional<AddressBook> addressBookOptional = addressBookService.getAddressBook(addressBookId);

        if(!addressBookOptional.isPresent()) {
            throw new AddressBookNotFoundException(addressBookId);
        }

        AddressBook addressBook = addressBookOptional.get();

        AddressBookResponse addressBookResponse = new AddressBookResponse();
        addressBookResponse.setId(addressBook.getId());
        addressBookResponse.setAddressBookName(addressBook.getAddressBookName());

        for(Contacts contact : addressBook.getContacts()) {

            ContactResponse contactResponse = new ContactResponse();
            contactResponse.setId(contact.getId());
            contactResponse.setName(contact.getName());
            contactResponse.setPhoneNumber(contact.getPhoneNumber());

            addressBookResponse.getContactResponse().add(contactResponse);
        }
        
        Collections.sort(addressBookResponse.getContactResponse(), new Comparator<ContactResponse>() {
            public int compare(ContactResponse a1, ContactResponse a2) {
                return a1.getName().compareToIgnoreCase(a2.getName());
            }
        });

        return addressBookResponse;

    }

    @GetMapping(path = "uniqueContacts")
    public ContactResponse getUniqueContacts(@RequestParam(name = "addressBook1Id") Long addressBook1Id, @RequestParam(name = "addressBook2Id") Long addressBook2Id) {

        List<Contacts> diff = addressBookService.getUniqueContacts(addressBook1Id, addressBook2Id);

        ContactResponse contactsResponse = new ContactResponse();

        for(Contacts contact : diff) {
            ContactResponse contactResponse = new ContactResponse();
            contactResponse.setId(contact.getId());
            contactResponse.setName(contact.getName());
            contactResponse.setPhoneNumber(contact.getPhoneNumber());

            contactsResponse.getUniqueContacts().add(contactResponse);
        }

        return contactsResponse;

    }


}
