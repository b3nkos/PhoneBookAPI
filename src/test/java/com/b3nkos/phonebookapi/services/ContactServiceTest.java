package com.b3nkos.phonebookapi.services;

import com.b3nkos.phonebookapi.models.Contact;
import com.b3nkos.phonebookapi.models.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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
        final var contact = new Contact("Jhon Doe", "jhon@gmail.com", "988-3493-233");
        final var expectedContact = new Contact(1L, "Jhon Doe", "jhon@gmail.com", "988-3493-233");

        when(contactRepository.save(contact)).thenReturn(expectedContact);

        final var actualContact = this.contactService.saveContact(contact);

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

    @Test
    @DisplayName("Should update contact")
    public void updateContact() {
        final var expectedContact = new Contact(1L, "Jhon Doe update", "jhonupdate@gmail.com", "988-3493-233");
        final var contactFound = new Contact(1L, "Jhon Doe", "jhon@gmail.com", "988-3493-233");

        when(contactRepository.findById(1L)).thenReturn(Optional.of(contactFound));
        when(contactRepository.save(expectedContact)).thenReturn(expectedContact);

        var actualContact = contactService.updateContact(1L, expectedContact);

        assertThat(actualContact)
                .as("actual contact should not be null in save operation")
                .isNotNull();
        assertThat(actualContact.getId())
                .as("contact id is not equals to 1")
                .isEqualTo(1);
        assertThat(actualContact.getName())
                .as("contact name is no equals to Jhon Doe")
                .isEqualTo("Jhon Doe update");
        assertThat(actualContact.getEmail())
                .as("contact email is not equals to jhon@gmail.com")
                .isEqualTo("jhonupdate@gmail.com");
        assertThat(actualContact.getPhone())
                .as("contact email is not equals to 988-3493-233")
                .isEqualTo("988-3493-233");
    }

    @Test
    @DisplayName("Should return a non empty contact list")
    public void getContacts() {
        final var contactList = List.of(
                new Contact(1L, "Jhon Doe", "jhon@gmail.com", "988-3493-233"),
                new Contact(2L, "Ana Smith", "ana@gmail.com", "99447363")
        );

        when(contactRepository.findAll()).thenReturn(contactList);

        final var expectedContactList = contactService.getContacts();

        assertThat(expectedContactList)
                .as("Actual contact list should not be empty")
                .isNotEmpty();
    }

    @Test
    @DisplayName("Should get contact by a id")
    public void getContactById() {
        final var expectedContact = new Contact(1L, "Jhon Doe", "jhon@gmail.com", "988-3493-233");

        when(contactRepository.findById(1L)).thenReturn(Optional.of(expectedContact));

        final var actualContact = contactService.getContactById(1L);

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

    @Test
    @DisplayName("Should delete contact by a id")
    public void deleteContactById() {
        final var expectedContact = new Contact(1L, "Jhon Doe", "jhon@gmail.com", "988-3493-233");
        final var contact = Optional.of(expectedContact);

        when(contactRepository.findById(1L)).thenReturn(contact);

        final var actualContact = contactService.deleteContactById(1L);

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