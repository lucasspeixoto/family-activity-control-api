package com.lspeixotodev.family_activity_control_api.service;

import com.lspeixotodev.family_activity_control_api.__mocks__.MockBill;
import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;
import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import com.lspeixotodev.family_activity_control_api.infra.exceptions.ResourceNotFoundException;
import com.lspeixotodev.family_activity_control_api.mapper.BillMapper;
import com.lspeixotodev.family_activity_control_api.repository.BillRepository;
import com.lspeixotodev.family_activity_control_api.service.impl.BillServiceImpl;
import com.lspeixotodev.family_activity_control_api.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.ParseException;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Bill Service (Unit Tests)")
public class BillServiceTests {

    @Mock
    private BillRepository billRepository;

    @Mock
    private CategoryServiceImpl categoryService;

    @InjectMocks
    private BillServiceImpl billService;

    @Spy
    private BillMapper billMapper = Mappers.getMapper(BillMapper.class);

    private Bill bill;

    private BillDTO billDTO;

    @InjectMocks
    public MockBill mockBill;

    @BeforeEach
    public void config() throws ParseException {
        this.bill = mockBill.getBill();
        this.billDTO = mockBill.getBillDTO();
    }

    @Test
    @Order(1)
    @DisplayName("Bill Service: Create Bill then return created Bill Dto")
    public void billService_WhenCreateABill_ThenReturnCreateBillDto() {
        when(billRepository.save(any(Bill.class))).thenReturn(this.bill);

        BillDTO mappedCreateBillFromBill = this.billMapper.entityToDto(this.bill);

        BillDTO billDTO = this.billService.createBill(this.billDTO);

        assertThat(billDTO).isNotNull();
        assertThat(billDTO.getTitle()).isEqualTo(mappedCreateBillFromBill.getTitle());
        assertThat(billDTO.getOwner()).isEqualTo(mappedCreateBillFromBill.getOwner());
        assertThat(billDTO.getCategoryId()).isEqualTo(mappedCreateBillFromBill.getCategoryId());
        assertThat(billDTO.getAmount()).isEqualTo(mappedCreateBillFromBill.getAmount());
        assertThat(billDTO.getDescription()).isEqualTo(mappedCreateBillFromBill.getDescription());
        assertThat(billDTO.getFinishAt()).isEqualTo(mappedCreateBillFromBill.getFinishAt());
        assertThat(billDTO.getType()).isEqualTo(mappedCreateBillFromBill.getType());

    }

    @Test
    @Order(2)
    @DisplayName("Bill Service: When Get All Bill then return a List of BillDTO")
    public void billService_WhenGetAllBills_ThenReturnCreateBillDto() {
        List<Bill> bills = Collections.singletonList(this.bill);
        when(billRepository.findAll()).thenReturn(bills);

        List<BillDTO> mappedBillsDto = this.billMapper.entitiesToBillDtos(bills);

        List<BillDTO> billsDto = this.billService.getAllBills();

        assertThat(billsDto).isNotNull();
        assertThat(billsDto.size()).isEqualTo(mappedBillsDto.size());
    }

    @Test
    @Order(3)
    @DisplayName("Bill Service: When Find Bill by id then return BillDTO")
    public void billService_WhenFindBillById_ThenReturnBillDto() {

        when(billRepository.findById(any())).thenReturn((Optional.ofNullable(this.bill)));

        BillDTO billDTO = this.billService.findBillById(this.bill.getId().toString());

        BillDTO mappedBillDTO = this.billMapper.entityToDto(this.bill);

        assertThat(billDTO).isNotNull();
        assertThat(billDTO).isEqualTo(mappedBillDTO);

    }

    @Test
    @Order(4)
    @DisplayName("Bill Service: When Find Bill by id then throws ResourceNotFoundException")
    public void billService_WhenFindBillById_ThenThrowsResourceNotFoundException() {

        when(billRepository.findById(any())).thenReturn((Optional.empty()));

        assertThrows(ResourceNotFoundException.class, () -> this.billService.findBillById(this.bill.getId().toString()));

    }

    @Test
    @Order(5)
    @DisplayName("Bill Service: When Update Bill then throws ResourceNotFoundException")
    public void billService_WhenUpdateBill_ThenThrowsResourceNotFoundException() {

        when(billRepository.findById(any())).thenReturn((Optional.empty()));

        assertThrows(ResourceNotFoundException.class, () ->
                this.billService.updateBill(this.billDTO, this.bill.getId().toString())
        );

    }

    @Test
    @Order(6)
    @DisplayName("Bill Service: When Update Bill then return BillDTO")
    public void billService_WhenUpdateBill_ThenReturnsBillDTO() {

        String newTitle = "New Title";
        this.bill.setTitle(newTitle);

        BillDTO billDTO = this.billMapper.entityToDto(this.bill);

        when(billRepository.findById(any())).thenReturn((Optional.of(this.bill)));
        when(billRepository.save(any(Bill.class))).thenReturn(this.bill);

        BillDTO updatedBill = this.billService.updateBill(billDTO, this.bill.getId().toString());

        assertThat(updatedBill).isNotNull();
        assertThat(updatedBill.getTitle()).isEqualTo(newTitle);

    }

    @Test
    @Order(7)
    @DisplayName("Bill Service: When Delete Bill then throws ResourceNotFoundException")
    public void billService_WhenDeleteBill_ThenThrowsResourceNotFoundException() {

        when(billRepository.findById(any())).thenReturn((Optional.empty()));

        assertThrows(ResourceNotFoundException.class, () -> this.billService.deleteBill(this.bill.getId().toString()));

    }

    @Test
    @Order(8)
    @DisplayName("Bill Service: When Delete Bill then return BillDTO")
    public void billService_WhenDeleteBill_ThenReturnsBillDTO() {

        BillDTO billDTO = this.billMapper.entityToDto(this.bill);

        when(billRepository.findById(any())).thenReturn((Optional.of(this.bill)));

        BillDTO deletedBill = this.billService.deleteBill(this.bill.getId().toString());

        assertThat(deletedBill).isNotNull();
        assertThat(deletedBill).isEqualTo(billDTO);

    }
}