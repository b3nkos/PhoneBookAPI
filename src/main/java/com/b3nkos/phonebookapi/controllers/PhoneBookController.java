package com.b3nkos.phonebookapi.controllers;

import com.b3nkos.phonebookapi.models.Contact;
import com.b3nkos.phonebookapi.services.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneBookController {

    private final ContactService contactService;

    public PhoneBookController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        return this.contactService.getContacts();
    }

    @PostMapping("/contacts")
    public Contact saveNewContact(@RequestBody Contact contact) {
        return this.contactService.saveContact(contact);
    }

    @GetMapping("/contacts/{id}")
    public Contact getContact(@PathVariable Long id) {
        return this.contactService.getContactById(id);
    }

    @PutMapping("/contacts")
    public Contact updateContact(@RequestBody Contact contact) {
        return contactService.updateContact(contact);
    }

    @DeleteMapping("/contacts/{id}")
    public Contact deleteContact(@PathVariable Long id) {
        return this.contactService.deleteContactById(id);
    }
}
