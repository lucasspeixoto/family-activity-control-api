package com.lspeixotodev.family_activity_control_api.repository;

import com.lspeixotodev.family_activity_control_api.__mocks__.MockBill;
import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Bill Repository (Integration Tests)")
public class BillRepositoryTests {

    @Autowired
    private BillRepository billRepository;

    public Bill bill;

    public Bill secondBill;

    @InjectMocks
    public MockBill mockBill;


    @BeforeEach
    public void config() throws ParseException {
        this.bill = mockBill.getBill();
        this.secondBill = mockBill.getSecondBill();
    }

    @Test
    @DisplayName("Bill Repository: Save a bill then Returns Saved Bill")
    @Order(1)
    public void billRepository_SaveBill_ReturnsSavedBill() {


        Bill savedBill = billRepository.save(this.bill);

        assertThat(savedBill).isNotNull();
        assertThat(savedBill.getTitle()).isEqualTo(this.bill.getTitle());
        assertThat(savedBill.getOwner()).isEqualTo(this.bill.getOwner());
        assertThat(savedBill.getDescription()).isEqualTo(this.bill.getDescription());
        assertThat(savedBill.getFinishAt().getTime()).isEqualTo(this.bill.getFinishAt().getTime());
        assertThat(savedBill.getAmount()).isEqualTo(this.bill.getAmount());
        assertThat(savedBill.getType()).isEqualTo(this.bill.getType());
    }

    @Test
    @DisplayName("Bill Repository: Find a bill By Id then Returns Bill")
    @Order(2)
    public void billRepository_FindBillById_ReturnsBill() {

        Bill savedBill = billRepository.save(this.bill);

        Optional<Bill> foundBill = billRepository.findById(savedBill.getId());

        assertThat(foundBill).isPresent();

    }

    @Test
    @DisplayName("Bill Repository: Find all bills Then returns two saved bills")
    @Order(3)
    public void billRepository_FindAll_ReturnsMoreThanOneBill() {

        billRepository.save(this.bill);
        billRepository.save(this.secondBill);

        List<Bill> listOfBills = billRepository.findAll();

        assertThat(listOfBills).isNotNull();
        assertThat(listOfBills.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Bill Repository: Update a bill Then returns updated bill")
    @Order(4)
    public void billRepository_UpdateABill_ReturnsUpdateBill() {

        String updatedTitle = "Updated title";

        Bill savedBill = billRepository.save(this.bill);

        savedBill.setTitle(updatedTitle);
        billRepository.save(savedBill);

        assertThat(savedBill).isNotNull();
        assertThat(savedBill.getTitle()).isEqualTo(updatedTitle);
    }

    @Test
    @DisplayName("Bill Repository: Save a bill with specific title the call existsByTitle and return true")
    @Order(5)
    public void billRepository_ExistsByTitle_ThenReturnTrue() {

        billRepository.save(this.bill);

        boolean existsByTitle = billRepository.existsByTitle(this.bill.getTitle());

        assertThat(existsByTitle).isTrue();
    }

    @Test
    @DisplayName("Bill Repository: Delete a bill then returns empty bills list")
    @Order(6)
    public void billRepository_DeleteBill_ThenReturnsEmptyBillsList() {

        Bill savedBill = billRepository.save(this.bill);

        billRepository.deleteById(savedBill.getId());

        List<Bill> bills = billRepository.findAll();

        assertThat(bills.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Bill Repository: Save a bill with specific title then the call findByTitleContainingIgnoreCase and return Bill")
    @Order(7)
    public void billRepository_findByTitleContainingIgnoreCase_ThenReturnBill() {

        billRepository.save(this.bill);

        List<Bill> optionalBill = billRepository.findByTitleContainingIgnoreCase(this.bill.getTitle());

        assertThat(optionalBill.size()).isGreaterThan(0);
    }
}
