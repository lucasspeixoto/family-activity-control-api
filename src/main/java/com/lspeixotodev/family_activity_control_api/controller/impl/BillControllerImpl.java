package com.lspeixotodev.family_activity_control_api.controller.impl;

import com.lspeixotodev.family_activity_control_api.controller.BillController;
import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.CreateBillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.UpdateBillDTO;
import com.lspeixotodev.family_activity_control_api.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/bill")
public class BillControllerImpl implements BillController {

    @Autowired
    private BillService billService;

    @Override
    public ResponseEntity<CreateBillDTO> create(CreateBillDTO createBillDTO) {

        return new ResponseEntity<>(this.billService.createBill(createBillDTO), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<List<CreateBillDTO>> getAllBills() throws Exception {
        return ResponseEntity.ok(this.billService.getAllBills());
    }

    @Override
    public ResponseEntity<CreateBillDTO> findBillById(String id) {
        return ResponseEntity.ok(this.billService.findBillById(id));
    }

    @Override
    public ResponseEntity<UpdateBillDTO> updateBill(UpdateBillDTO updateBillDTO, String id) {
        return ResponseEntity.ok(this.billService.updateBill(updateBillDTO, id));
    }

    @Override
    public ResponseEntity<CreateBillDTO> deleteBill(String id) {
        return ResponseEntity.ok(this.billService.deleteBill(id));
    }

    @Override
    public ResponseEntity<BillDTO> findBillByTitle(String title) {
        return ResponseEntity.ok(this.billService.findBillByTitle(title));
    }
}
