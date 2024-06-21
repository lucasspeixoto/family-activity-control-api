package com.lspeixotodev.family_activity_control_api.service.impl;

import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.CreateBillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.UpdateBillDTO;
import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import com.lspeixotodev.family_activity_control_api.infra.exceptions.ResourceNotFoundException;
import com.lspeixotodev.family_activity_control_api.mapper.BillMapper;
import com.lspeixotodev.family_activity_control_api.repository.BillRepository;
import com.lspeixotodev.family_activity_control_api.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillMapper billMapper;

    @Override
    public BillDTO createBill(CreateBillDTO CreateBillDTO) {

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
        UUID uuid = UUID.fromString(id);

        Bill entity = billRepository
                .findById(uuid)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Bill", "id", id)
                );

        return this.billMapper.toDTO(entity);
    }

    @Override
    public BillDTO updateBill(UpdateBillDTO updateBillDTO, String id) {

        Optional<Bill> optionalBill = billRepository.findById(UUID.fromString(id));

        if (optionalBill.isEmpty()) {
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
        Optional<Bill> optionalBill = billRepository.findById(UUID.fromString(id));

        if (optionalBill.isEmpty()) {
            throw new ResourceNotFoundException("Bill", "id", id);
        }

        Bill existingBill = optionalBill.get();

        billRepository.deleteById(existingBill.getId());

        return this.billMapper.toDTO(existingBill);
    }

    @Override
    public BillDTO findBillByTitle(String title) {

        Optional<Bill> optionalBill = billRepository.findByTitleContainingIgnoreCase(title);

        if (optionalBill.isEmpty()) {
            throw new ResourceNotFoundException("Bill", "title", title);
        }

        return this.billMapper.toDTO(optionalBill.get());
    }
}
