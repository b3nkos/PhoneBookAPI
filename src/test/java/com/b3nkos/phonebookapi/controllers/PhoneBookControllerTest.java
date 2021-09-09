package com.b3nkos.phonebookapi.controllers;

import com.b3nkos.phonebookapi.models.Contact;
import com.b3nkos.phonebookapi.services.ContactService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PhoneBookControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ContactService contactService;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Should save a new contact")
    void getAllContacts() {
        get("/contacts")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", hasItems(1, 2, 3));
    }

    @Test
    @DisplayName("Should save a new contact")
    void saveNewContact() {
        final Contact contact = new Contact("Rest Assured", "restassured@gmail.com", "988-3493-233");
        given()
                .request()
                .contentType(ContentType.JSON)
                .body(contact)
                .when()
                .post("/contacts")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("name", equalTo("Rest Assured"))
                .body("email", equalTo("restassured@gmail.com"))
                .body("phone", equalTo("988-3493-233"));
    }

    @Test
    @DisplayName("Should get contact by a id")
    void getContact() {
        final Contact contact = new Contact("Rest Assured", "restassured@gmail.com", "988-3493-233");
        final Contact contactSaved = contactService.saveContact(contact);

        given()
                .request()
                .pathParam("id", contactSaved.getId().intValue())
                .when()
                .get("/contacts/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(contactSaved.getId().intValue()))
                .body("name", equalTo("Rest Assured"))
                .body("email", equalTo("restassured@gmail.com"))
                .body("phone", equalTo("988-3493-233"));
    }

    @Test
    @DisplayName("Should update contact")
    void updateContact() {

        final Contact contact = new Contact("Rest Assured", "restassured@gmail.com", "988-3493-233");
        final Contact contactSaved = contactService.saveContact(contact);

        given()
                .request()
                .contentType(ContentType.JSON)
                .pathParam("id", contactSaved.getId().intValue())
                .body(Map.of("name", "Rest Assured Updated"))
                .when()
                .put("/contacts/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(contactSaved.getId().intValue()))
                .body("name", equalTo("Rest Assured Updated"))
                .body("email", equalTo("restassured@gmail.com"))
                .body("phone", equalTo("988-3493-233"));
    }

    @Test
    @DisplayName("Should delete contact by a id")
    void deleteContact() {
        final Contact contact = new Contact("Rest Assured", "restassured@gmail.com", "988-3493-233");
        final Contact contactSaved = contactService.saveContact(contact);

        given()
                .request()
                .pathParam("id", contactSaved.getId().intValue())
                .when()
                .delete("/contacts/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(contactSaved.getId().intValue()))
                .body("name", equalTo("Rest Assured"))
                .body("email", equalTo("restassured@gmail.com"))
                .body("phone", equalTo("988-3493-233"));
    }
}