package com.lspeixotodev.family_activity_control_api.mapper;

import com.lspeixotodev.family_activity_control_api.dto.ImageDTO;
import com.lspeixotodev.family_activity_control_api.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION
)
public interface ImageMapper {

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    @Mapping(target = "id", expression = "java(entity.getId() != null ? entity.getId().toString() : null)")
    @Mapping(target = "user", ignore = true)
    ImageDTO entityToDto(Image entity);

    @Mapping(target = "id", expression = "java(dto.getId() != null ? java.util.UUID.fromString(dto.getId()) : null)")
    Image dtoToEntity(ImageDTO dto);

    @Mapping(target = "user", ignore = true)
    List<ImageDTO> entitiesToDtos(List<Image> entities);

}
