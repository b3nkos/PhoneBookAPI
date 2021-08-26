package com.b3nkos.phonebookapi.controllers;

import com.b3nkos.phonebookapi.models.Contact;
import com.b3nkos.phonebookapi.models.ContactRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneBookController {

    private final ContactRepository contactRepository;

    public PhoneBookController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {

        return this.contactRepository.findAll();
    }

    @PostMapping("/contacts")
    public Contact saveNewContact(@RequestBody Contact contact) {
        return this.contactRepository.save(contact);
    }

    @GetMapping("/contacts/{id}")
    public Contact getContact(@PathVariable Long id) {
        return this.contactRepository.findById(id).get();
    }
}
