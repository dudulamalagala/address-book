package com.pwc.address_book.requests;

import java.util.List;

public class AddressBookRequest {
    
	private String addressBookName;

	private List<ContactRequest> contacts;
	
	public String getAddressBookName() {
		return addressBookName;
	}

	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}

	public List<ContactRequest> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactRequest> contacts) {
		this.contacts = contacts;
	}
}
