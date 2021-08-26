package com.b3nkos.phonebookapi.services;

import com.b3nkos.phonebookapi.models.Contact;
import com.b3nkos.phonebookapi.models.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    private ContactService contactService;

    @BeforeEach
    void setUp() {
        this.contactService = new ContactService(contactRepository);
    }

    @Test
    @DisplayName("Should save a new contact")
    public void saveNewContact() {
        Contact contact = new Contact("Jhon Doe", "jhon@gmail.com", "988-3493-233");
        Contact expectedContact = new Contact(1L, "Jhon Doe", "jhon@gmail.com", "988-3493-233");

        when(contactRepository.save(contact)).thenReturn(expectedContact);

        Contact actualContact = this.contactService.saveContact(contact);

        assertThat(actualContact)
                .as("actual contact should not be null in save operation")
                .isNotNull();
        assertThat(actualContact.getId())
                .as("contact id is not equals to 1")
                .isEqualTo(1);
        assertThat(actualContact.getName())
                .as("contact name is no equals to Jhon Doe")
                .isEqualTo("Jhon Doe");
        assertThat(actualContact.getEmail())
                .as("contact email is not equals to jhon@gmail.com")
                .isEqualTo("jhon@gmail.com");
        assertThat(actualContact.getPhone())
                .as("contact email is not equals to 988-3493-233")
                .isEqualTo("988-3493-233");
    }
}