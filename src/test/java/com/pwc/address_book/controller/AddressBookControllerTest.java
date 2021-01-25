package com.pwc.address_book.controller;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pwc.address_book.AddressBookApplication;
import com.pwc.address_book.data.AddressBookRepository;
import com.pwc.address_book.model.AddressBook;
import com.pwc.address_book.requests.AddressBookRequest;
import com.pwc.address_book.requests.ContactRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressBookApplication.class)
@WebAppConfiguration
public class AddressBookControllerTest {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private AddressBookRepository addressBookRepo;
	
	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}
	
	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		List<AddressBook> addressBookData = (List<AddressBook>) addressBookRepo.findAll();
		addressBookRepo.deleteAll(addressBookData);
	}
	
	@After
	public void clear() throws Exception {
		List<AddressBook> addressBookData = (List<AddressBook>) addressBookRepo.findAll();
		addressBookRepo.deleteAll(addressBookData);
	}
	
	@Test
    public void test_add_addressbook() throws Exception {
		
		AddressBookRequest addAddressBook = new AddressBookRequest();
		List<ContactRequest> t1 = new ArrayList<ContactRequest>();
		ContactRequest addContact = new ContactRequest();
		addContact.setName("Mary");
		addContact.setPhoneNumber("1234");
		t1.add(addContact);
		
		addAddressBook.setAddressBookName("first");
		addAddressBook.setContacts(t1);
		
		mockMvc.perform(post("/v1/address-book/createAddressBook").content(json(addAddressBook)).contentType(contentType)).andExpect(status().isOk());
    }
	
	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

	@Test
    public void test_speific_addressbook_exists() throws Exception {
		mockMvc.perform(get("/v1/address-book/getAddressBook?addressBookId=1").contentType(contentType)).andExpect(status().isNotFound());
    }

    @Test
    public void test_unique_contacts() throws Exception {
    	mockMvc.perform(get("/v1/address-book/uniqueContacts?addressBook1Id=1L&addressBook2Id=2L")
    			.contentType(contentType)).andExpect(status().isBadRequest());
    }

}
