package com.pwc.address_book.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class for addressbook.
 * 
 */
@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "address_book_name", nullable = false, unique = true)
    private String addressBookName;

	@ManyToMany(cascade = CascadeType.ALL)
    private List<Contacts> contacts = new ArrayList<Contacts>();
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAddressBookName() {
		return addressBookName;
	}

	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}

	public void setContacts(List<Contacts> contacts) {
		this.contacts = contacts;
	}

	public List<Contacts> getContacts() {
		return contacts;
	}

}
