package com.lspeixotodev.family_activity_control_api.service.impl;

import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.CreateBillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.UpdateBillDTO;
import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import com.lspeixotodev.family_activity_control_api.infra.exceptions.ResourceNotFoundException;
import com.lspeixotodev.family_activity_control_api.mapper.BillMapper;
import com.lspeixotodev.family_activity_control_api.mapper.CreateBillMapper;
import com.lspeixotodev.family_activity_control_api.mapper.UpdateBillMapper;
import com.lspeixotodev.family_activity_control_api.repository.BillRepository;
import com.lspeixotodev.family_activity_control_api.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BillServiceImpl implements BillService {

    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CreateBillMapper createBillMapper;

    @Autowired
    private UpdateBillMapper updateBillMapper;

    @Autowired
    private BillMapper billMapper;

    @Override
    public CreateBillDTO createBill(CreateBillDTO CreateBillDTO) {

        Bill bill = this.createBillMapper.toEntity(CreateBillDTO);

        Bill savedBill = billRepository.save(bill);

        return this.createBillMapper.toDTO(savedBill);
    }

    @Override
    public List<CreateBillDTO> getAllBills() {
        List<Bill> bills = billRepository.findAll();

        return this.createBillMapper.toDTOs(bills);
    }

    @Override
    public CreateBillDTO findBillById(String id) {
        UUID uuid = UUID.fromString(id);

        Bill entity = billRepository
                .findById(uuid)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Conta", "id", id)
                );

        return this.createBillMapper.toDTO(entity);
    }

    @Override
    public UpdateBillDTO updateBill(UpdateBillDTO updateBillDTO, String id) {

        Optional<Bill> optionalBill = billRepository.findById(UUID.fromString(id));

        if (optionalBill.isEmpty()) {
            throw new ResourceNotFoundException("Conta", "id", id);
        }

        Bill existingBill = optionalBill.get();

        Bill changedBill = setBillFieldsHandler(updateBillDTO, existingBill);

        Bill updatedBill = billRepository.save(changedBill);

        return this.updateBillMapper.toDTO(updatedBill);
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
    public CreateBillDTO deleteBill(String id) {
        Optional<Bill> optionalBill = billRepository.findById(UUID.fromString(id));

        if (optionalBill.isEmpty()) {
            throw new ResourceNotFoundException("Conta", "id", id);
        }

        Bill existingBill = optionalBill.get();

        billRepository.deleteById(existingBill.getId());

        return this.createBillMapper.toDTO(existingBill);
    }

    @Override
    public BillDTO findBillByTitle(String title) {

        Optional<Bill> optionalBill = billRepository.findByTitleIgnoreCase(title);

        if (optionalBill.isEmpty()) {
            throw new ResourceNotFoundException("Bill", "title", title);
        }

        return this.billMapper.toDTO(optionalBill.get());
    }
}
