package com.lspeixotodev.family_activity_control_api.mapper;

import com.lspeixotodev.family_activity_control_api.dto.category.CategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryUsageDTO;
import com.lspeixotodev.family_activity_control_api.entity.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION
)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    // Entity <-> CategoryDTO ----------------------------------------
    @Mapping(target = "id", expression = "java(entity.getId() != null ? entity.getId().toString() : null)")
    CategoryDTO entityToDTO(Category entity);
    @Mapping(target = "id", expression = "java(dto.getId() != null ? java.util.UUID.fromString(dto.getId()) : null)")
    Category dtoToEntity(CategoryDTO dto);
    List<CategoryDTO> entitiesToDtos(List<Category> entities);
    // ----------------------------------------


    // Entity <-> UpdateCategoryUsageDTO ----------------------------------------
    @Mapping(source = "title", target = "value")
    @Mapping(source = "description", target = "viewValue")
    CategoryUsageDTO entityToCategoryUsageDTO(Category entity);

    @Mapping(source = "title", target = "value")
    @Mapping(source = "description", target = "viewValue")
    @Mapping(target = "id", expression = "java(entity.getId() != null ? entity.getId().toString() : null)")
    List<CategoryUsageDTO> entitiesToCategoryUsages(List<Category> entities);
    // ----------------------------------------

}
