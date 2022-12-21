package de.dn.petstore.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PetTest {

    @Test
    public void setName_valid() {
        Pet pet = new Pet();
        pet.setName("test");
        Assertions.assertEquals("test", pet.getName());
    }

    @Test
    public void setTag_valid() {
        Pet pet = new Pet();
        pet.setTag("123");
        Assertions.assertEquals("123", pet.getTag());
    }
}
