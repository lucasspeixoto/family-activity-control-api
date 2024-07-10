package com.lspeixotodev.family_activity_control_api.service.impl;

import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.CreateBillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.UpdateBillDTO;
import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import com.lspeixotodev.family_activity_control_api.infra.exceptions.ResourceNotFoundException;
import com.lspeixotodev.family_activity_control_api.mapper.BillMapper;
import com.lspeixotodev.family_activity_control_api.repository.BillRepository;
import com.lspeixotodev.family_activity_control_api.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    private BillMapper billMapper;

    @Override
    public BillDTO createBill(CreateBillDTO CreateBillDTO) {
        logger.info("Start creating bill at: {}", LocalDateTime.now());

        Bill bill = this.billMapper.createBillDtoToEntity(CreateBillDTO);

        Bill savedBill = billRepository.save(bill);

        return this.billMapper.toDTO(savedBill);
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

        return this.billMapper.toDTO(entity.get());
    }

    @Override
    public BillDTO updateBill(UpdateBillDTO updateBillDTO, String id) {
        logger.info("Start update a bill at: {}", LocalDateTime.now());

        Optional<Bill> optionalBill = billRepository.findById(UUID.fromString(id));

        if (optionalBill.isEmpty()) {
            logger.info("Fail to update a bill with id {}", id);
            throw new ResourceNotFoundException("Bill", "id", id);
        }

        Bill existingBill = optionalBill.get();

        Bill changedBill = setBillFieldsHandler(updateBillDTO, existingBill);

        Bill updatedBill = billRepository.save(changedBill);

        return this.billMapper.toDTO(updatedBill);
    }

    private static Bill setBillFieldsHandler(UpdateBillDTO updateBillDTO, Bill existingBill) {
        existingBill.setTitle(updateBillDTO.getTitle());
        existingBill.setOwner(updateBillDTO.getOwner());
        existingBill.setAmount(updateBillDTO.getAmount());
        existingBill.setCategory(updateBillDTO.getCategory());
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

        return this.billMapper.toDTO(existingBill);
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
