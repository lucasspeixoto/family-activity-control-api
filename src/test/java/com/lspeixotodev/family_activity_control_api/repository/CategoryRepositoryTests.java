package com.lspeixotodev.family_activity_control_api.repository;

import com.lspeixotodev.family_activity_control_api.__mocks__.MockCategory;
import com.lspeixotodev.family_activity_control_api.entity.category.Category;
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
@DisplayName("Category Repository (Integration Tests)")
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category category;

    public Category secondCategory;

    @InjectMocks
    public MockCategory mockCategory;

    @BeforeEach
    public void config() throws ParseException {
        this.category = mockCategory.getCategory();
        this.secondCategory = mockCategory.getSecondCategory();
    }

    @Test
    @DisplayName("Category Repository: Save a category then Returns Saved Category")
    @Order(1)
    public void categoryRepository_SaveCategory_ReturnsSavedCategory() {

        Category savedCategory = categoryRepository.save(this.category);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getTitle()).isEqualTo(this.category.getTitle());
        assertThat(savedCategory.getDescription()).isEqualTo(this.category.getDescription());
    }

    @Test
    @DisplayName("Category Repository: Find all categories Then returns two saved categories")
    @Order(2)
    public void categoryRepository_FindAll_ReturnsMoreThanOneCategory() {

        categoryRepository.save(this.category);
        categoryRepository.save(this.secondCategory);

        List<Category> allCategories = categoryRepository.findAll();

        assertThat(allCategories).isNotNull();
        assertThat(allCategories.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Category Repository: Find a category By Id then Returns Category")
    @Order(3)
    public void billRepository_FindCategoryById_ReturnsCategory() {

        Category savedCategory = categoryRepository.save(this.category);

        Optional<Category> foundBill = categoryRepository.findById(savedCategory.getId());

        assertThat(foundBill).isPresent();

    }

    @Test
    @DisplayName("Category Repository: Update a category Then returns updated category")
    @Order(4)
    public void billRepository_UpdateABill_ReturnsUpdateBill() {

        String updatedTitle = "Updated title";

        Category savedCategory = categoryRepository.save(this.category);

        savedCategory.setTitle(updatedTitle);
        categoryRepository.save(savedCategory);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getTitle()).isEqualTo(updatedTitle);
    }

    @Test
    @DisplayName("Category Repository: Save a category with specific title the call existsByTitle and return true")
    @Order(5)
    public void billRepository_ExistsByTitle_ThenReturnTrue() {

        categoryRepository.save(this.category);

        boolean existsByTitle = categoryRepository.existsByTitle(this.category.getTitle());

        assertThat(existsByTitle).isTrue();
    }

    @Test
    @DisplayName("Category Repository: Delete a category then returns empty bills list")
    @Order(6)
    public void billRepository_DeleteBill_ThenReturnsEmptyBillsList() {

        Category savedCategory = categoryRepository.save(this.category);

        categoryRepository.deleteById(savedCategory.getId());

        List<Category> bills = categoryRepository.findAll();

        assertThat(bills.size()).isEqualTo(0);
    }
}
