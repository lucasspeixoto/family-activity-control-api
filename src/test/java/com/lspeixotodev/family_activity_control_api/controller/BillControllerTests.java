package com.lspeixotodev.family_activity_control_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lspeixotodev.family_activity_control_api.__mocks__.MockBill;
import com.lspeixotodev.family_activity_control_api.controller.impl.BillControllerImpl;
import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;
import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import com.lspeixotodev.family_activity_control_api.infra.exceptions.ResourceNotFoundException;
import com.lspeixotodev.family_activity_control_api.repository.BillRepository;
import com.lspeixotodev.family_activity_control_api.service.impl.BillServiceImpl;

import com.lspeixotodev.family_activity_control_api.service.impl.CategoryServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.text.ParseException;
import java.util.Collections;
import java.util.UUID;

@WebMvcTest(BillControllerImpl.class)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Bill Controller (Integration Tests)")
public class BillControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BillServiceImpl billService;

    @MockBean
    private CategoryServiceImpl categoryService;

    @MockBean
    private BillRepository billRepository;

    @InjectMocks
    public MockBill mockBill;

    @Autowired
    private ObjectMapper objectMapper;

    private Bill bill;

    private BillDTO billDTO;


    @BeforeEach
    public void config() throws ParseException {
        this.bill = mockBill.getBill();
        this.billDTO = mockBill.getBillDTO();
    }

    @Test
    @Order(1)
    @DisplayName("Bill Controller: Create Bill With Valid Data Then returns created")
    public void billController_CreateBillWithValidData_ThenReturnsCreated() throws Exception {
        when(billService.createBill(any(BillDTO.class))).thenReturn(this.billDTO);

        ResultActions response = mvc.perform(post("/api/v1/bill/create")
                .content(objectMapper.writeValueAsString(this.billDTO))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", CoreMatchers.is("Energia")))
                .andExpect(jsonPath("$.owner", CoreMatchers.is("Lucas P")))
                .andExpect(jsonPath("$.amount", CoreMatchers.is(89.50)))
                .andExpect(jsonPath("$.categoryId", CoreMatchers.is("8de274fd-6a14-46be-9816-4552a71f9e16")))
                .andExpect(jsonPath("$.description", CoreMatchers.is("Pagar a conta de energia")))
                .andExpect(jsonPath("$.type", CoreMatchers.is("FIXED")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(2)
    @DisplayName("Bill Controller: Create Bill With Invalid Title Then Throws UnprocessableEntity")
    public void billController_CreateBillWithInvalidTitle_ThenThrowsUnprocessableEntity() throws Exception {
        when(billService.createBill(any(BillDTO.class))).thenReturn(this.billDTO);

        this.billDTO.setTitle("ti");

        mvc.perform(post("/api/v1/bill/create")
                        .content(objectMapper.writeValueAsString(this.billDTO))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", CoreMatchers.is("The Title must contain at least 3 characters!")))
                .andDo(MockMvcResultHandlers.print());


        this.billDTO.setTitle(null);

        mvc.perform(post("/api/v1/bill/create")
                        .content(objectMapper.writeValueAsString(this.billDTO))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", CoreMatchers.is("The Title is mandatory!")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(3)
    @DisplayName("Bill Controller: Create Bill With Invalid Owner Then Throws UnprocessableEntity")
    public void billController_CreateBillWithInvalidOwner_ThenThrowsUnprocessableEntity() throws Exception {
        when(billService.createBill(any(BillDTO.class))).thenReturn(this.billDTO);

        this.billDTO.setOwner("ti");

        mvc.perform(post("/api/v1/bill/create")
                        .content(objectMapper.writeValueAsString(this.billDTO))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", CoreMatchers.is("The Owner must contain at least 3 characters!")))
                .andDo(MockMvcResultHandlers.print());


        this.billDTO.setOwner(null);

        mvc.perform(post("/api/v1/bill/create")
                        .content(objectMapper.writeValueAsString(this.billDTO))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", CoreMatchers.is("The Owner is mandatory!")))
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    @Order(4)
    @DisplayName("Bill Controller: Create Bill With Invalid Description Then Throws UnprocessableEntity")
    public void billController_CreateBillWithInvalidDescription_ThenThrowsUnprocessableEntity() throws Exception {
        when(billService.createBill(any(BillDTO.class))).thenReturn(this.billDTO);

        this.billDTO.setDescription("ti");

        mvc.perform(post("/api/v1/bill/create")
                        .content(objectMapper.writeValueAsString(this.billDTO))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", CoreMatchers.is("The Description must contain at least 3 characters!")))
                .andDo(MockMvcResultHandlers.print());


        this.billDTO.setDescription(null);

        mvc.perform(post("/api/v1/bill/create")
                        .content(objectMapper.writeValueAsString(this.billDTO))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", CoreMatchers.is("The Description is mandatory!")))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Order(5)
    @DisplayName("Bill Controller: Create Bill With Invalid FinishAt Then Throws UnprocessableEntity")
    public void billController_CreateBillWithInvalidFinishAt_ThenThrowsUnprocessableEntity() throws Exception {
        when(billService.createBill(any(BillDTO.class))).thenReturn(this.billDTO);

        this.billDTO.setFinishAt(null);

        mvc.perform(post("/api/v1/bill/create")
                        .content(objectMapper.writeValueAsString(this.billDTO))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", CoreMatchers.is("The Finish At is mandatory!")))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Order(6)
    @DisplayName("Bill Controller: getAllBills Then Returns BillDTO List")
    public void billController_getAllBills_ThenReturnsBillDTOList() throws Exception {
        when(billService.getAllBills()).thenReturn(Collections.singletonList(this.billDTO));

        mvc.perform(get("/api/v1/bill")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    @Order(7)
    @DisplayName("Bill Controller: When Find Bill By Id Then Return BillDTO")
    public void billController_WhenFindBillByIdThatExists_ThenReturnBillDTO() throws Exception {
        String requiredIdSearch = UUID.randomUUID().toString();

        when(billService.findBillById(requiredIdSearch)).thenReturn(this.billDTO);

        mvc.perform(get("/api/v1/bill/find-by-id/{id}", requiredIdSearch)
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Order(8)
    @DisplayName("Bill Controller: When Find Bill By Id Then Throws ResourceNotFoundException")
    public void billController_WhenFindBillByIdThatDoesNotExists_ThenThrowsResourceNotFoundException() throws Exception {
        String requiredIdSearch = UUID.randomUUID().toString();

        when(billService.findBillById(anyString())).thenThrow(new ResourceNotFoundException("Bill", "id", requiredIdSearch));

        mvc.perform(get("/api/v1/bill/find-by-id/{id}", requiredIdSearch)
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", CoreMatchers.is("Bill not found with id " + requiredIdSearch)))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Order(9)
    @DisplayName("Bill Controller: When Find Bill By Title Then Return BillDTO")
    public void billController_WhenFindBillByTitleThatExists_ThenReturnBillDTO() throws Exception {
        String requiredTitleSearch = "Energia";

        when(billService.findBillByTitle(requiredTitleSearch)).thenReturn(Collections.singletonList(this.billDTO));

        mvc.perform(get("/api/v1/bill/find-by-title/{title}", requiredTitleSearch)
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(10)
    @DisplayName("Bill Controller: When Find Bill By Title Then Throws ResourceNotFoundException")
    public void billController_WhenFindBillByTitleThatDoesNotExists_ThenThrowsResourceNotFoundException() throws Exception {
        String requiredTitleSearch = "Energia";

        when(billService.findBillByTitle(requiredTitleSearch)).thenThrow(new ResourceNotFoundException("Bill", "id", requiredTitleSearch));

        mvc.perform(get("/api/v1/bill/find-by-title/{title}", requiredTitleSearch)
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", CoreMatchers.is("Bill not found with id " + requiredTitleSearch)))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Order(11)
    @DisplayName("Bill Controller: Update Bill With Valid Data Then returns ok")
    public void billController_UpdateBillWithValidData_ThenReturnsCreated() throws Exception {
        when(billService.updateBill(any(BillDTO.class), anyString())).thenReturn(this.billDTO);

        ResultActions response = mvc.perform(put("/api/v1/bill/update/{id}", this.billDTO.getId())
                .content(objectMapper.writeValueAsString(this.billDTO))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", CoreMatchers.is("Energia")))
                .andExpect(jsonPath("$.owner", CoreMatchers.is("Lucas P")))
                .andExpect(jsonPath("$.amount", CoreMatchers.is(89.50)))
                .andExpect(jsonPath("$.categoryId", CoreMatchers.is("8de274fd-6a14-46be-9816-4552a71f9e16")))
                .andExpect(jsonPath("$.description", CoreMatchers.is("Pagar a conta de energia")))
                .andExpect(jsonPath("$.type", CoreMatchers.is("FIXED")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(12)
    @DisplayName("Bill Controller: Update Bill That does no exist Then Throws ResourceNotFoundException")
    public void billController_WhenUpdateByTitleThatDoesNotExists_ThenThrowsResourceNotFoundException() throws Exception {

        String requiredTitleSearch = UUID.randomUUID().toString();

        when(billService.updateBill(any(BillDTO.class), anyString()))
                .thenThrow(new ResourceNotFoundException("Bill", "id", requiredTitleSearch));

        ResultActions response = mvc.perform(put("/api/v1/bill/update/{id}", requiredTitleSearch)
                .content(objectMapper.writeValueAsString(this.billDTO))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Order(13)
    @DisplayName("Bill Controller: Delete Bill With Existing Id Then Delete Successfully")
    public void billController_DeleteBillWithExistingId_ThenDeleteSuccessfully() throws Exception {

        String requiredIdSearch = this.bill.getId().toString();

        when(billService.deleteBill(anyString()))
                .thenThrow(new ResourceNotFoundException("Bill", "id", requiredIdSearch));

        ResultActions response = mvc.perform(delete(
                "/api/v1/bill/delete/{id}",
                requiredIdSearch
        )
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Order(14)
    @DisplayName("Bill Controller: Delete Bill With Non Existing Id Then Throws ResourceNotFoundException")
    public void billController_DeleteBillWithNonExistingId_ThenThrowsResourceNotFoundException() throws Exception {

        String requiredIdSearch = this.bill.getId().toString();

        when(billService.deleteBill(anyString())).thenReturn(this.billDTO);

        ResultActions response = mvc.perform(delete(
                "/api/v1/bill/delete/{id}",
                requiredIdSearch
        )
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
