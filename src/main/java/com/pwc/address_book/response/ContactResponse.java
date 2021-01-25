package com.pwc.address_book.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Response class for contact.
 * 
 */
public class ContactResponse {
	
	private int id;
    private String name;
    private String phoneNumber;
    private List<ContactResponse> uniqueContacts = new ArrayList<ContactResponse>();
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
	public List<ContactResponse> getUniqueContacts() {
		return uniqueContacts;
	}
	public void setUniqueContacts(List<ContactResponse> uniqueContacts) {
		this.uniqueContacts = uniqueContacts;
	}

}
