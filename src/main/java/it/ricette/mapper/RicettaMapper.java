package it.ricette.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.ricette.dto.RicettaDto;
import it.ricette.model.Ricetta;

@Mapper(componentModel = "spring", uses = {CategoriaMapper.class})
public interface RicettaMapper {
    @Mapping(target = "categoria", source = "categoria")
    RicettaDto toDto(Ricetta ricetta);

    @Mapping(target = "categoria", source = "categoria")
    Ricetta toEntity(RicettaDto ricettaDto);
}
