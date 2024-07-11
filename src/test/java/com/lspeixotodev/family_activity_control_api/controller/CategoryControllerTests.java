package com.lspeixotodev.family_activity_control_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lspeixotodev.family_activity_control_api.__mocks__.MockCategory;
import com.lspeixotodev.family_activity_control_api.__mocks__.MockCategory;
import com.lspeixotodev.family_activity_control_api.controller.impl.CategoryControllerImpl;
import com.lspeixotodev.family_activity_control_api.controller.impl.CategoryControllerImpl;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryUsageDTO;
import com.lspeixotodev.family_activity_control_api.entity.category.Category;
import com.lspeixotodev.family_activity_control_api.infra.exceptions.ResourceNotFoundException;
import com.lspeixotodev.family_activity_control_api.repository.CategoryRepository;
import com.lspeixotodev.family_activity_control_api.repository.CategoryRepository;
import com.lspeixotodev.family_activity_control_api.service.impl.CategoryServiceImpl;
import com.lspeixotodev.family_activity_control_api.service.impl.CategoryServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.text.ParseException;
import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryControllerImpl.class)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Category Controller (Integration Tests)")
public class CategoryControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryServiceImpl categoryService;

    @MockBean
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ObjectMapper objectMapper;

    private Category category;

    private CategoryDTO categoryDTO;

    private CategoryUsageDTO categoryUsageDTO;

    @InjectMocks
    public MockCategory mockCategory;


    @BeforeEach
    public void config() throws ParseException {
        this.category = mockCategory.getCategory();
        this.categoryDTO = mockCategory.getCategoryDTO();
        this.categoryUsageDTO = mockCategory.getCategoryUsageDTO();
    }

    @Test
    @Order(1)
    @DisplayName("Category Controller: Create Category With Valid Data Then returns created")
    public void categoryController_CreateCategoryWithValidData_ThenReturnsCreated() throws Exception {
        when(categoryService.create(any(CategoryDTO.class))).thenReturn(this.categoryDTO);

        ResultActions response = mvc.perform(post("/api/v1/category/create")
                .content(objectMapper.writeValueAsString(this.categoryDTO))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", CoreMatchers.is("Academia/Ginástica")))
                .andExpect(jsonPath("$.description", CoreMatchers.is("Academia/Ginástica para os filhos")))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Order(2)
    @DisplayName("Category Controller: Create Category With Invalid Title Then Throws UnprocessableEntity")
    public void categoryController_CreateCategoryWithInvalidTitle_ThenThrowsUnprocessableEntity() throws Exception {
        when(categoryService.create(any(CategoryDTO.class))).thenReturn(this.categoryDTO);

        this.categoryDTO.setTitle("ti");

        mvc.perform(post("/api/v1/category/create")
                        .content(objectMapper.writeValueAsString(this.categoryDTO))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", CoreMatchers.is("The title must contain at least 3 characters!")))
                .andDo(MockMvcResultHandlers.print());


        this.categoryDTO.setTitle(null);

        mvc.perform(post("/api/v1/category/create")
                        .content(objectMapper.writeValueAsString(this.categoryDTO))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", CoreMatchers.is("The title is mandatory!")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(3)
    @DisplayName("Category Controller: Create Category With Invalid Description Then Throws UnprocessableEntity")
    public void categoryController_CreateCategoryWithInvalidDescription_ThenThrowsUnprocessableEntity() throws Exception {
        when(categoryService.create(any(CategoryDTO.class))).thenReturn(this.categoryDTO);

        this.categoryDTO.setDescription("ti");

        mvc.perform(post("/api/v1/category/create")
                        .content(objectMapper.writeValueAsString(this.categoryDTO))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", CoreMatchers.is("The description must contain at least 3 characters!")))
                .andDo(MockMvcResultHandlers.print());


        this.categoryDTO.setDescription("Academia/Ginástica para os filhos");
        this.categoryDTO.setTitle(null);

        mvc.perform(post("/api/v1/category/create")
                        .content(objectMapper.writeValueAsString(this.categoryDTO))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", CoreMatchers.is("The title is mandatory!")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(4)
    @DisplayName("Category Controller: getAllCategories Then Returns CategoryDTO List")
    public void categoryController_getAllCategories_ThenReturnsCategoryDTOList() throws Exception {
        when(categoryService.getAllCategories()).thenReturn(Collections.singletonList(this.categoryDTO));

        mvc.perform(get("/api/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @Order(5)
    @DisplayName("Category Controller: getAllCategoriesUsages Then Returns CategoryUsageDTO List")
    public void categoryController_getAllCategoriesUsages_ThenReturnsCategoryUsageDTOList() throws Exception {
        when(categoryService.getCategoryUsages()).thenReturn(Collections.singletonList(this.categoryUsageDTO));

        mvc.perform(get("/api/v1/category/usages")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @Order(6)
    @DisplayName("Category Controller: When Find Category By Id Then Return CategoryDTO")
    public void categoryController_WhenFindCategoryByIdThatExists_ThenReturnCategoryDTO() throws Exception {
        String requiredIdSearch = UUID.randomUUID().toString();

        when(categoryService.findCategoryById(requiredIdSearch)).thenReturn(this.categoryDTO);

        mvc.perform(get("/api/v1/category/find-by-id/{id}", requiredIdSearch)
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(7)
    @DisplayName("Category Controller: When Find Category By Id Then Throws ResourceNotFoundException")
    public void categoryController_WhenFindCategoryByIdThatDoesNotExists_ThenThrowsResourceNotFoundException() throws Exception {
        String requiredIdSearch = UUID.randomUUID().toString();

        when(categoryService.findCategoryById(anyString())).thenThrow(new ResourceNotFoundException("Category", "id", requiredIdSearch));

        mvc.perform(get("/api/v1/category/find-by-id/{id}", requiredIdSearch)
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", CoreMatchers.is("Category not found with id " + requiredIdSearch)))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Order(8)
    @DisplayName("Category Controller: Update Category With Valid Data Then returns created")
    public void categoryController_UpdateCategoryWithValidData_ThenReturnsCreated() throws Exception {
        when(categoryService.updateCategory(any(CategoryDTO.class), anyString())).thenReturn(this.categoryDTO);

        ResultActions response = mvc.perform(put("/api/v1/category/update/{id}", this.categoryDTO.getId())
                .content(objectMapper.writeValueAsString(this.categoryDTO))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", CoreMatchers.is("Academia/Ginástica")))
                .andExpect(jsonPath("$.description", CoreMatchers.is("Academia/Ginástica para os filhos")))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Order(9)
    @DisplayName("Category Controller: Update Category That does no exist Then Throws ResourceNotFoundException")
    public void categoryController_WhenUpdateByTitleThatDoesNotExists_ThenThrowsResourceNotFoundException() throws Exception {

        String requiredTitleSearch = UUID.randomUUID().toString();

        when(categoryService.updateCategory(any(CategoryDTO.class), anyString()))
                .thenThrow(new ResourceNotFoundException("Category", "id", requiredTitleSearch));

        ResultActions response = mvc.perform(put("/api/v1/category/update/{id}", requiredTitleSearch)
                .content(objectMapper.writeValueAsString(this.categoryDTO))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", CoreMatchers.is("Category not found with id " + requiredTitleSearch)))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Order(10)
    @DisplayName("Category Controller: Delete Category With Existing Id Then Delete Successfully")
    public void categoryController_DeleteCategoryWithExistingId_ThenDeleteSuccessfully() throws Exception {
        when(categoryService.deleteCategory(anyString())).thenReturn(this.categoryDTO);

        ResultActions response = mvc.perform(delete("/api/v1/category/delete/{id}", this.categoryDTO.getId())
                .content(objectMapper.writeValueAsString(this.categoryDTO))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", CoreMatchers.is("Academia/Ginástica")))
                .andExpect(jsonPath("$.description", CoreMatchers.is("Academia/Ginástica para os filhos")))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Order(11)
    @DisplayName("Category Controller: Delete Category With Non Existing Id Then Delete Successfully")
    public void categoryController_DeleteCategoryWithNonExistingId_ThenThrowsResourceNotFoundException() throws Exception {
        String requiredTitleSearch = UUID.randomUUID().toString();

        when(categoryService.deleteCategory(anyString()))
                .thenThrow(new ResourceNotFoundException("Category", "id", requiredTitleSearch));

        ResultActions response = mvc.perform(delete("/api/v1/category/delete/{id}", requiredTitleSearch)
                .content(objectMapper.writeValueAsString(this.categoryDTO))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", CoreMatchers.is("Category not found with id " + requiredTitleSearch)))
                .andDo(MockMvcResultHandlers.print());

    }
}
