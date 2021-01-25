package com.pwc.address_book.response;

import java.util.ArrayList;
import java.util.List;


/**
 * Response class for addressbook.
 * 
 */
public class AddressBookResponse {
    private Long id;
    private String addressBookName;
    private List<ContactResponse> contactResponse = new ArrayList<ContactResponse>();
    
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
	public List<ContactResponse> getContactResponse() {
		return contactResponse;
	}
	public void setContactResponse(List<ContactResponse> contactResponse) {
		this.contactResponse = contactResponse;
	}
    
    
}
