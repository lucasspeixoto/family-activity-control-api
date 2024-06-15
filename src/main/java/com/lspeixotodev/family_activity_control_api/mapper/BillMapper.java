package com.lspeixotodev.family_activity_control_api.mapper;

import com.lspeixotodev.family_activity_control_api.dto.BillDTO;
import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface BillMapper {

    BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);


    @Mapping(target = "id", expression = "java(entity.getId() != null ? entity.getId().toString() : null)")
    BillDTO toDTO(Bill entity);

    @Mapping(target = "id", expression = "java(dto.getId() != null ? java.util.UUID.fromString(dto.getId()) : null)")
    Bill toEntity(BillDTO dto);

    List<BillDTO> toDTOs(List<Bill> entities);

    List<Bill> toEntities(List<BillDTO> dtos);
}
