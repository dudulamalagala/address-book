package com.pwc.address_book.model;

import javax.persistence.*;

/**
 * Model class for contacts.
 * 
 */
@Entity
@Table(name = "contact", uniqueConstraints = @UniqueConstraint(columnNames = { "name", "phone_number" }))
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@Column
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;
    
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

}
