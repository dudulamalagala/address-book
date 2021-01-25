package com.pwc.address_book.data;

import com.pwc.address_book.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Dudula
 * Repository for working with Contacts Entity.
 */
@Repository
public interface ContactsRepository  extends JpaRepository<Contacts, Long> {
	
	/**
	 * Finding the list of contacts by name and phonenumber.
	 * @param listId
	 * @return
	 */
    public Optional<Contacts> findByNameAndPhoneNumber(String name, String phoneNumber);

}