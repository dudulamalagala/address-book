package com.pwc.address_book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Dudula
 *	Exception if the addressbook entry with id is not found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AddressBookNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AddressBookNotFoundException(Long id){
		super("could not find addressbook with '" + id + "'.");
	}
}
