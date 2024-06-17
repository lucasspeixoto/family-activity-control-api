package com.lspeixotodev.family_activity_control_api.mapper;

import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.CreateBillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.UpdateBillDTO;
import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION
)
public interface BillMapper {

    BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);

    @Mapping(target = "id", expression = "java(entity.getId() != null ? entity.getId().toString() : null)")
    BillDTO toDTO(Bill entity);

    @Mapping(target = "id", expression = "java(dto.getId() != null ? java.util.UUID.fromString(dto.getId()) : null)")
    Bill toEntity(BillDTO dto);

    @Mapping(target = "id", expression = "java(entity.getId() != null ? entity.getId().toString() : null)")
    CreateBillDTO entityToCreateBillDTO(Bill entity);

    @Mapping(target = "id", expression = "java(dto.getId() != null ? java.util.UUID.fromString(dto.getId()) : null)")
    Bill createBillDtoToEntity(CreateBillDTO dto);

    @Mapping(target = "id", expression = "java(entity.getId() != null ? entity.getId().toString() : null)")
    UpdateBillDTO entityToUpdateBillDTO(Bill entity);

    @Mapping(target = "id", expression = "java(dto.getId() != null ? java.util.UUID.fromString(dto.getId()) : null)")
    Bill updateBillDtoToEntity(UpdateBillDTO dto);

    List<BillDTO> entitiesToBillDtos(List<Bill> entities);

    List<Bill> billDtosToEntities(List<BillDTO> dtos);

    List<CreateBillDTO> entitiesToCreateBillDtos(List<Bill> entities);

    List<Bill> createBillDtosToBillList(List<CreateBillDTO> dtos);

    List<UpdateBillDTO> entitiesToUpdateBillDtos(List<Bill> entities);

    List<Bill> updateBillDtosToBillList(List<UpdateBillDTO> dtos);

    @Named("localDateToString")
    default String localDateToString(LocalDate date) {
        if (date == null) {
            return null;
        }

        LocalDateTime dateTime = date.atStartOfDay();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return dateTime.format(formatter);
    }

    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

}
