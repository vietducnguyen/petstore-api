package de.dn.petstore.mapper;

import de.dn.petstore.api.dto.PetDto;
import de.dn.petstore.domain.Pet;
import de.dn.petstore.utils.mapper.PetMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PetMapperTest {

    @Test
    public void shouldMapDtoToPet() {
        PetDto dto = new PetDto("dto name", "dto tag");
        Pet pet = PetMapper.INSTANCE.toPet(dto);

        assertNotNull(dto);
        assertEquals(dto.name(), pet.getName());
        assertEquals(dto.tag(), pet.getTag());
    }
}
