package com.example;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private ContactService contactService;

    @BeforeEach
    public void setUp() {
        contactService = new ContactService();
    }

    @Test
    public void testAddContact() {
        Contact contact = new Contact("12345", "Sarah", "Wagner", "1234567890", "123 Broad St");
        contactService.addContact(contact);
        assertEquals(contact, contactService.getContact("12345"));
    }

    @Test
    public void testAddContactWithDuplicateId() {
        Contact contact1 = new Contact("12345", "Sarah", "Wagner", "1234567890", "123 Broad St");
        contactService.addContact(contact1);
        Contact contact2 = new Contact("12345", "Zack", "Wagner", "0987654321", "456 Market St");
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(contact2);
        });
    }

    @Test
    public void testDeleteContact() {
        Contact contact = new Contact("12345", "Sarah", "Wagner", "1234567890", "123 Broad St");
        contactService.addContact(contact);
        contactService.deleteContact("12345");
        assertNull(contactService.getContact("12345"));
    }

    @Test
    public void testUpdateContact() {
        Contact contact = new Contact("12345", "Sarah", "Wagner", "1234567890", "123 Broad St");
        contactService.addContact(contact);
        contactService.updateContact("12345", "Zack", null, "0987654321", "456 Market St");
        Contact updatedContact = contactService.getContact("12345");
        assertEquals("Zack", updatedContact.getFirstName());
        assertEquals("0987654321", updatedContact.getPhone());
        assertEquals("456 Market St", updatedContact.getAddress());
    }

    @Test
    public void testUpdateNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContact("99999", "Zack", "Wagner", "0987654321", "456 Market St");
        });
    }
}
