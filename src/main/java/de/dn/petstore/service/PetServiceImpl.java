package de.dn.petstore.service;


import de.dn.petstore.domain.Pet;
import de.dn.petstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    @Autowired
    PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Optional<Pet> findPetById(int petId) {
        return petRepository.findById(petId);
    }

    @Override
    public List<Pet> getPets(int limit, int offset) {
        Pageable paging = PageRequest.of(offset, limit);
        final Page<Pet> pagingPet = petRepository.findAll(paging);
        return pagingPet != null ? pagingPet.toList() : Collections.emptyList();
    }

    @Override
    public Pet createPet(Pet data) {
        Pet pet = new Pet();
        pet.setName(data.getName());
        pet.setTag(data.getTag());
        return petRepository.save(pet);
    }

    @Override
    public Optional<Pet> updatePet(Integer petId, Pet data) {
        return findPetById(petId)
                .map((it -> {
                    it.setName(data.getName());
                    it.setTag(data.getTag());
                    return petRepository.save(it);
                }));
    }
}
