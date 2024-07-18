package com.lspeixotodev.family_activity_control_api.service.impl;

import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryDTO;
import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import com.lspeixotodev.family_activity_control_api.entity.category.Category;
import com.lspeixotodev.family_activity_control_api.infra.exceptions.ResourceNotFoundException;
import com.lspeixotodev.family_activity_control_api.mapper.BillMapper;
import com.lspeixotodev.family_activity_control_api.repository.BillRepository;
import com.lspeixotodev.family_activity_control_api.repository.CategoryRepository;
import com.lspeixotodev.family_activity_control_api.service.BillService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BillServiceImpl implements BillService {

    private static final Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);

    @Autowired
    private BillRepository billRepository;


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private BillMapper billMapper;

    @Override
    public BillDTO createBill(BillDTO billDTO) {
        logger.info("Start creating bill at: {}", LocalDateTime.now());

        CategoryDTO categoryDTO = categoryService.findCategoryById(billDTO.getCategoryId());

        Category category = categoryService.categoryDTOToCategory(categoryDTO);

        Bill bill = this.billMapper.dtoToEntity(billDTO);

        bill.setCategory(category);

        Bill savedBill = billRepository.save(bill);

        return this.billMapper.entityToDto(savedBill);
    }

    @Override
    public List<BillDTO> getAllBills() {
        List<Bill> bills = billRepository.findAll();

        return this.billMapper.entitiesToBillDtos(bills);
    }

    @Override
    public BillDTO findBillById(String id) {
        logger.info("Start finding bill by id at: {}", LocalDateTime.now());

        UUID uuid = UUID.fromString(id);

        Optional<Bill> entity = billRepository.findById(uuid);

        if (entity.isEmpty()) {
            logger.info("Fail to find a bill with id {}", id);
            throw new ResourceNotFoundException("Bill", "id", id);
        }

        return this.billMapper.entityToDto(entity.get());
    }

    @Override
    public BillDTO updateBill(BillDTO billDTO, String id) {
        logger.info("Start update a bill at: {}", LocalDateTime.now());

        Optional<Bill> optionalBill = billRepository.findById(UUID.fromString(id));

        if (optionalBill.isEmpty()) {
            logger.info("Fail to update a bill with id {}", id);
            throw new ResourceNotFoundException("Bill", "id", id);
        }

        CategoryDTO categoryDTO = categoryService.findCategoryById(billDTO.getCategoryId());

        Category category = categoryService.categoryDTOToCategory(categoryDTO);

        Bill existingBill = optionalBill.get();

        Bill changedBill = setBillFieldsHandler(billDTO, existingBill, category);

        Bill updatedBill = billRepository.save(changedBill);

        return this.billMapper.entityToDto(updatedBill);
    }

    private static Bill setBillFieldsHandler(BillDTO updateBillDTO, Bill existingBill, Category category) {
        existingBill.setTitle(updateBillDTO.getTitle());
        existingBill.setOwner(updateBillDTO.getOwner());
        existingBill.setAmount(updateBillDTO.getAmount());
        existingBill.setCategory(category);
        existingBill.setDescription(updateBillDTO.getDescription());
        existingBill.setFinishAt(updateBillDTO.getFinishAt());
        existingBill.setType(updateBillDTO.getType());

        return existingBill;
    }

    @Override
    public BillDTO deleteBill(String id) {
        logger.info("Start delete a bill at: {}", LocalDateTime.now());

        Optional<Bill> optionalBill = billRepository.findById(UUID.fromString(id));

        if (optionalBill.isEmpty()) {
            logger.info("Fail to delete a bill with id {}", id);
            throw new ResourceNotFoundException("Bill", "id", id);
        }

        Bill existingBill = optionalBill.get();

        billRepository.deleteById(existingBill.getId());

        return this.billMapper.entityToDto(existingBill);
    }

    @Override
    public List<BillDTO> findBillByTitle(String title) {
        logger.info("Start find a bill by title at: {}", LocalDateTime.now());

        List<Bill> bills = billRepository.findByTitleContainingIgnoreCase(title);

        if (bills.isEmpty()) {
            logger.info("Fail to find a bill by title with '{}' title", title);
            throw new ResourceNotFoundException("Bill", "title", title);
        }

        return this.billMapper.entitiesToBillDtos(bills);
    }
}
