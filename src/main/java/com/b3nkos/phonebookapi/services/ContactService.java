package com.b3nkos.phonebookapi.services;

import com.b3nkos.phonebookapi.models.Contact;
import com.b3nkos.phonebookapi.models.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact saveContact(Contact contact) {
        return this.contactRepository.save(contact);
    }

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    public Contact deleteContactById(Long id) {
        final var contact = contactRepository.findById(id).orElseThrow();
        contactRepository.deleteById(id);
        return contact;
    }

    public Contact updateContact(Long id, Contact contact) {
        final var contactFound = contactRepository.findById(id).orElseThrow();

        Optional.ofNullable(contact.getName()).ifPresent(contactFound::setName);
        Optional.ofNullable(contact.getEmail()).ifPresent(contactFound::setEmail);
        Optional.ofNullable(contact.getPhone()).ifPresent(contactFound::setPhone);

        return contactRepository.save(contactFound);
    }
}
