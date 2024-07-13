package com.lspeixotodev.family_activity_control_api.mapper;

import com.lspeixotodev.family_activity_control_api.dto.category.CategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CreateCategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryUsageDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.UpdateCategoryDTO;
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
    Category dTOToEntity(CategoryDTO dto);
    List<CategoryDTO> entitiesToDtos(List<Category> entities);
    // ----------------------------------------

    // Entity <-> CreateCategoryDTO ----------------------------------------
    @Mapping(target = "id", expression = "java(entity.getId() != null ? entity.getId().toString() : null)")
    CreateCategoryDTO entityToCreateDTO(Category entity);
    @Mapping(target = "id", expression = "java(dto.getId() != null ? java.util.UUID.fromString(dto.getId()) : null)")
    Category createDTOToEntity(CreateCategoryDTO dto);
    List<CreateCategoryDTO> entitiesToCreateDtos(List<Category> entities);
    // ----------------------------------------


    // Entity <-> UpdateCategoryDTO ----------------------------------------
    @Mapping(target = "id", expression = "java(entity.getId() != null ? entity.getId().toString() : null)")
    UpdateCategoryDTO entityToUpdateDTO(Category entity);
    @Mapping(target = "id", expression = "java(dto.getId() != null ? java.util.UUID.fromString(dto.getId()) : null)")
    Category updateDTOToEntity(UpdateCategoryDTO dto);
    List<UpdateCategoryDTO> entitiesToUpdateDtos(List<Category> entities);
    // ----------------------------------------


    // Entity <-> UpdateCategoryUsageDTO ----------------------------------------
    @Mapping(source = "title", target = "value")
    @Mapping(source = "description", target = "viewValue")
    CategoryUsageDTO entityToCategoryUsageDTO(Category entity);

    @Mapping(source = "title", target = "value")
    @Mapping(source = "description", target = "viewValue")
    List<CategoryUsageDTO> entitiesToCategoryUsages(List<Category> entities);
    // ----------------------------------------

}
