package com.b3nkos.phonebookapi;

import com.b3nkos.phonebookapi.models.Contact;
import com.b3nkos.phonebookapi.models.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PhoneBookApiApplication {

    private static final Logger logger = LoggerFactory.getLogger(PhoneBookApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PhoneBookApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner initContacts(ContactRepository contactRepository) {
        return (args) -> {
            contactRepository.save(new Contact("Joan Jaime", "jaume@yahoo.com", "984884-399848"));
            contactRepository.save(new Contact("Jhon Doe", "doe@gmail.com", "333-334552"));
            contactRepository.save(new Contact("Ana Doll", "anadoll@gmail.com", "83783-39383"));
            logger.info("Contacts already saved");
        };
    }

}
