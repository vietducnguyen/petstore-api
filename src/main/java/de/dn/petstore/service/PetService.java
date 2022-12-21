package de.dn.petstore.service;

import de.dn.petstore.domain.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {

    List<Pet> getPets(int limit, int offset);

    Optional<Pet> findPetById(int petId);

    Pet createPet(Pet data);

    Optional<Pet> updatePet(Integer petId, Pet data);
}
