package com.pwc.address_book.data;

import com.pwc.address_book.model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dudula
 * Repository for working with AddressBook Entity.
 */
@Repository
public interface AddressBookRepository  extends JpaRepository<AddressBook, Long> {
	
}

