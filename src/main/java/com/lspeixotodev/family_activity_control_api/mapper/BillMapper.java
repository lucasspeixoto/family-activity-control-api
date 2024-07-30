package com.lspeixotodev.family_activity_control_api.mapper;

import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;
import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION
)
public interface BillMapper {

    BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);

    @Mapping(target = "id", expression = "java(entity.getId() != null ? entity.getId().toString() : null)")
    @Mapping(target = "categoryId", expression = "java(entity.getCategory() != null ? entity.getCategory().getId().toString() : null)")
    BillDTO entityToDto(Bill entity);

    @Mapping(target = "id", expression = "java(dto.getId() != null ? java.util.UUID.fromString(dto.getId()) : null)")
    Bill dtoToEntity(BillDTO dto);

    @Mapping(target = "categoryId", expression = "java(entity.getCategory() != null ? entity.getCategory().getId().toString() : null)")
    List<BillDTO> entitiesToDtos(List<Bill> entities);

}
