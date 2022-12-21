package de.dn.petstore.api.controller;

import de.dn.petstore.api.dto.PetDto;
import de.dn.petstore.domain.Pet;
import de.dn.petstore.service.PetService;
import de.dn.petstore.utils.mapper.PetMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pets")
@Validated
public class PetController {
    private PetService petService;
    private PetMapper petMapper;

    @Autowired
    PetController(PetService petService, PetMapper petMapper) {
        this.petService = petService;
        this.petMapper = petMapper;
    }

    @GetMapping
    public ResponseEntity<List<Pet>> listPets(
            @RequestParam(required = false, defaultValue = "100") @NotNull @Max(100) int limit,
            @RequestParam(required = false, defaultValue = "0") @NotNull int offset
    ) {
        final List<Pet> pets = petService.getPets(limit, offset);
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody @Valid PetDto dto) {
        final Pet createdPet = petService.createPet(petMapper.toPet(dto));
        return new ResponseEntity<>(createdPet, HttpStatus.OK);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> showPetById(@PathVariable @NotNull Integer petId) {
        return petService.findPetById(petId)
                .map(it -> new ResponseEntity<>(it, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{petId}")
    public ResponseEntity<Pet> updatePetById(@PathVariable @NotNull Integer petId, @RequestBody @Valid Pet data) {
        return petService.updatePet(petId, data)
                .map(it -> new ResponseEntity<>(it, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
