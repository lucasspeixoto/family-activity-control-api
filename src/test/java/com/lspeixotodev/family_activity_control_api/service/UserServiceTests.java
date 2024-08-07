package com.lspeixotodev.family_activity_control_api.service;

import com.lspeixotodev.family_activity_control_api.__mocks__.MockImage;
import com.lspeixotodev.family_activity_control_api.__mocks__.MockUser;
import com.lspeixotodev.family_activity_control_api.dto.ImageDTO;
import com.lspeixotodev.family_activity_control_api.dto.authentication.UserDTO;
import com.lspeixotodev.family_activity_control_api.dto.authentication.UserFullDataDTO;
import com.lspeixotodev.family_activity_control_api.entity.Image;
import com.lspeixotodev.family_activity_control_api.entity.authentication.User;
import com.lspeixotodev.family_activity_control_api.infra.exceptions.ResourceNotFoundException;
import com.lspeixotodev.family_activity_control_api.mapper.authentication.UserMapper;
import com.lspeixotodev.family_activity_control_api.repository.ImageRepository;
import com.lspeixotodev.family_activity_control_api.repository.authentication.UserRepository;
import com.lspeixotodev.family_activity_control_api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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
@DisplayName("User Service (Unit Tests)")
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private User firstUser;

    private User secondUser;

    private UserDTO firstUserDTO;

    private UserFullDataDTO userFullDataDTO;

    private Image image;

    private ImageDTO imageDTO;

    @InjectMocks
    public MockUser mockUser;

    @InjectMocks
    public MockImage mockImage;

    @BeforeEach
    public void config() throws ParseException {
        this.firstUser = mockUser.getFirstUser();
        this.secondUser = mockUser.getSecondUser();
        this.firstUserDTO = mockUser.getFirstUserDTO();
        this.userFullDataDTO = mockUser.getUserFullDataDTO();

        this.image = mockImage.getImage();
        this.imageDTO = mockImage.getImageDTO();
    }

    @Test
    @Order(1)
    @DisplayName("User Service: When Find All Users then return a List of UserDTO")
    public void userService_WhenFindAll_ThenReturnAListOfUserDTO() {
        List<User> users = Collections.singletonList(this.firstUser);
        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> mappedUsersDTO = this.userMapper.entitiesToDtos(users);

        List<UserDTO> usersDTO = this.userService.findAll();

        assertThat(usersDTO).isNotNull();
        assertThat(usersDTO.size()).isEqualTo(mappedUsersDTO.size());
    }

    @Test
    @Order(2)
    @DisplayName("User Service: When Find User By Username Or Email then return UserDTO")
    public void userService_WhenFindUserByUsernameOrEmail_ThenReturnUserDTO() {
        when(userRepository.findUserByUsernameOrEmail(anyString(), anyString()))
                .thenReturn((Optional.ofNullable(this.firstUser)));

        UserDTO userDTO = this.userService.findUserByUsernameOrEmail(
                this.firstUser.getUsername(), this.firstUser.getEmail()
        );

        assertThat(userDTO).isNotNull();
        assertThat(userDTO.getName()).isEqualTo(this.firstUser.getName());
        assertThat(userDTO.getUsername()).isEqualTo(this.firstUser.getUsername());
        assertThat(userDTO.getEmail()).isEqualTo(this.firstUser.getEmail());
        assertThat(userDTO.getId()).isEqualTo(this.firstUser.getId().toString());
    }

    @Test
    @Order(3)
    @DisplayName("User Service: When Find User By Username Or Email With Non existing user then Throws ResourceNotFound")
    public void userService_WhenFindUserByUsernameOrEmail_ThenThrowsResourceNotFound() {
        when(userRepository.findUserByUsernameOrEmail(anyString(), anyString()))
                .thenReturn((Optional.empty()));

        assertThrows(ResourceNotFoundException.class, () -> this.userService.findUserByUsernameOrEmail(
                this.firstUser.getUsername(), this.firstUser.getEmail()
        ));
    }

    @Test
    @Order(4)
    @DisplayName("User Service: When Find By ID then return UserFullDataDTO")
    public void userService_WhenFindById_ThenReturnUserFullDataDTO() {
        UUID id = this.firstUser.getId();

        when(userRepository.findById(any()))
                .thenReturn((Optional.ofNullable(this.firstUser)));

        when(imageRepository.findByUserId(id))
                .thenReturn((Optional.ofNullable(this.image)));

        UserFullDataDTO userFullDataDTO = this.userService.findById(id.toString());

        assertThat(userFullDataDTO).isNotNull();
        assertThat(userFullDataDTO.getName()).isEqualTo(this.firstUser.getName());
        assertThat(userFullDataDTO.getUsername()).isEqualTo(this.firstUser.getUsername());
        assertThat(userFullDataDTO.getEmail()).isEqualTo(this.firstUser.getEmail());
        assertThat(userFullDataDTO.getId()).isEqualTo(this.firstUser.getId().toString());
    }

    @Test
    @Order(5)
    @DisplayName("User Service: When Find User By Id With Non existing user then Throws ResourceNotFound")
    public void userService_WhenFindById_ThenThrowsResourceNotFound() {
        when(userRepository.findById(any())).thenReturn((Optional.empty()));

        assertThrows(ResourceNotFoundException.class, () -> this.userService.findById(
                this.firstUser.getId().toString()
        ));
    }

    @Test
    @Order(6)
    @DisplayName("User Service: When Find User By Existing User By Id then return UserFullDataDTO")
    public void userService_WhenFindExistingUserById_ThenReturnUserFullDataDTO() {
        UUID id = this.firstUser.getId();

        when(userRepository.findById(any()))
                .thenReturn((Optional.ofNullable(this.firstUser)));


        User user = this.userService.findExistingUserById(id.toString());

        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo(this.firstUser.getName());
        assertThat(user.getUsername()).isEqualTo(this.firstUser.getUsername());
        assertThat(user.getEmail()).isEqualTo(this.firstUser.getEmail());
        assertThat(user.getId()).isEqualTo(this.firstUser.getId());
    }

    @Test
    @Order(7)
    @DisplayName("User Service: When Find User By Existing User By Id With Non existing user then Throws ResourceNotFound")
    public void userService_WhenFindExistingUserById_ThenThrowsResourceNotFound() {
        when(userRepository.findById(any())).thenReturn((Optional.empty()));

        assertThrows(ResourceNotFoundException.class, () -> this.userService.findExistingUserById(
                this.firstUser.getId().toString()
        ));
    }

    /*
    @Test
    @Order(1)
    @DisplayName("Bill Service: Create Bill then return created Bill Dto")
    public void billService_WhenCreateABill_ThenReturnCreateBillDto() {
        String userId = this.bill.getUser().getId().toString();

        when(userService.findExistingUserById(userId)).thenReturn(this.bill.getUser());
        when(billRepository.save(any(Bill.class))).thenReturn(this.bill);

        BillDTO mappedCreateBillFromBill = this.billMapper.entityToDto(this.bill);

        BillDTO billDTO = this.billService.createBill(this.billDTO, userId);

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
    public void billService_WhenFindAllBills_ThenReturnCreateBillDto() {
        UUID uuid = this.bill.getUser().getId();
        List<Bill> bills = Collections.singletonList(this.bill);
        when(billRepository.findAllBillsByUser(uuid)).thenReturn(bills);

        List<BillDTO> mappedBillsDto = this.billMapper.entitiesToDtos(bills);

        List<BillDTO> billsDto = this.billService.findAllBills(uuid.toString());

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
     */
}