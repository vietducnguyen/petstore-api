package de.dn.petstore.utils.mapper;

import de.dn.petstore.api.dto.PetDto;
import de.dn.petstore.domain.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PetMapper {

    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    @Mapping(target = "id", ignore = true)
    Pet toPet(PetDto petDto);
}
