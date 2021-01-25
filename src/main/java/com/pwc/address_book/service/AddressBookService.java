package com.pwc.address_book.service;

import com.pwc.address_book.model.AddressBook;
import com.pwc.address_book.model.Contacts;
import com.pwc.address_book.data.AddressBookRepository;
import com.pwc.address_book.data.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Dudula
 * Service class for addressbook operations.
 */
@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private ContactsRepository contactsRepository;
    
    
    /**
	 * Save a addressbook.
	 * @param addressbook
	 */
	@Transactional
    public void save(AddressBook addressBook) {

        List<Contacts> resolvedContacts = new ArrayList<Contacts>();

        for (Contacts contact : addressBook.getContacts()) {
            resolvedContacts.add(contactsRepository.findByNameAndPhoneNumber(contact.getName(), contact.getPhoneNumber()).orElse(contact));
        }

        addressBook.getContacts().clear();

        addressBook.getContacts().addAll(resolvedContacts);

        addressBookRepository.save(addressBook);

    }
	
	/**
	 * Get a addressbook.
	 * @param id
	 */
    public Optional<AddressBook> getAddressBook(Long id) {
        return addressBookRepository.findById(id);
    }
    
    /**
	 * get unique contacts in a addressbook.
	 * @param addressbook1 , addressbook2
	 */
    public List<Contacts> getUniqueContacts(Long addressBook1Id, Long addressBook2Id) {

        Optional<AddressBook> addressBook1 = addressBookRepository.findById(addressBook1Id);
        Optional<AddressBook> addressBook2 = addressBookRepository.findById(addressBook2Id);

        List<Contacts> diff1 = difference(addressBook1.get().getContacts(), addressBook2.get().getContacts());
        List<Contacts> diff2 = difference(addressBook2.get().getContacts(), addressBook1.get().getContacts());

        diff1.addAll(diff2);

        return null;
    }

    public List<Contacts> difference(List<Contacts> first, List<Contacts> second) {
        List<Contacts> toReturn = new ArrayList<Contacts>(first);
        toReturn.removeAll(second);
        return toReturn;
    }
}
