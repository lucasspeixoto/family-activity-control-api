package com.lspeixotodev.family_activity_control_api.repository;

import com.lspeixotodev.family_activity_control_api.__mocks__.MockBill;
import com.lspeixotodev.family_activity_control_api.entity.authentication.User;
import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import com.lspeixotodev.family_activity_control_api.entity.category.Category;
import com.lspeixotodev.family_activity_control_api.integrationtests.AbstractIntegrationTest;
import com.lspeixotodev.family_activity_control_api.repository.authentication.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("it")
@DisplayName("Bill Repository (Integration Tests)")
public class BillRepositoryTests extends AbstractIntegrationTest {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Bill bill;

    public Bill secondBill;

    public Category billCategory;

    @InjectMocks
    public MockBill mockBill;


    @BeforeEach
    public void config() throws ParseException {
        this.bill = mockBill.getBill();
        this.bill.setCategory(this.billCategory);

        this.secondBill = mockBill.getSecondBill();
        this.secondBill.setCategory(this.billCategory);
    }

    @BeforeAll
    public void setup() {
        Category category = this.mockBill.getCategoryForBill();
        this.billCategory = categoryRepository.save(category);
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

        Bill savedBill = billRepository.save(this.bill);

        List<Bill> optionalBill = billRepository.findByTitleAndUserId(
                savedBill.getTitle(),
                savedBill.getUser().getId()
        );

        assertThat(optionalBill.size()).isGreaterThan(0);
    }
}
