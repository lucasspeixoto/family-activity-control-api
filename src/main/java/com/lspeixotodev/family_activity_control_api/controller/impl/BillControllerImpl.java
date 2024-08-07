package com.lspeixotodev.family_activity_control_api.controller.impl;

import com.lspeixotodev.family_activity_control_api.controller.BillController;
import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;

import com.lspeixotodev.family_activity_control_api.service.impl.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/api/v1/bill")
public class BillControllerImpl implements BillController {

    @Autowired
    private BillServiceImpl billService;

    @Override
    public ResponseEntity<BillDTO> create(
            @RequestBody BillDTO billDTO,
            @RequestParam(value = "userId") String userId
    ) {
        return new ResponseEntity<>(this.billService.createBill(billDTO, userId), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BillDTO> update(
            @RequestBody BillDTO billDTO, @PathVariable String id
    ) {
        return new ResponseEntity<>(this.billService.updateBill(billDTO, id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BillDTO>> findAllBills(
            @RequestParam(value = "userId") String userId
    ) {
        return new ResponseEntity<>(this.billService.findAllBills(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BillDTO> findBillById(@PathVariable String id) {
        return new ResponseEntity<>(this.billService.findBillById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BillDTO>> findBillByTitleAndUser(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "userId") String userId
    ) {
        return new ResponseEntity<>(this.billService.findBillByTitleAndUser(title, userId), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<BillDTO> deleteBill(@PathVariable String id) {
        return new ResponseEntity<>(this.billService.deleteBill(id), HttpStatus.OK);
    }
}
