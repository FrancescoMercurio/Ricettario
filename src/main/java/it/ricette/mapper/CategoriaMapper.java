package it.ricette.mapper;

import org.mapstruct.Mapper;
import it.ricette.dto.CategoriaDto;
import it.ricette.model.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaDto toDto(Categoria categoria);
    Categoria toEntity(CategoriaDto categoriaDto);
}