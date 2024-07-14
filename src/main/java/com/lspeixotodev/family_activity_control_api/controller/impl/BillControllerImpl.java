package com.lspeixotodev.family_activity_control_api.controller.impl;

import com.lspeixotodev.family_activity_control_api.controller.BillController;
import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;

import com.lspeixotodev.family_activity_control_api.service.impl.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/api/v1/bill")
public class BillControllerImpl implements BillController {

    @Autowired
    private BillServiceImpl billService;

    @Override
    public ResponseEntity<BillDTO> create(@RequestBody BillDTO billDTO) {
        return new ResponseEntity<>(this.billService.createBill(billDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BillDTO> update(@RequestBody BillDTO billDTO, @PathVariable String id
    ) {
        return new ResponseEntity<>(this.billService.updateBill(billDTO, id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BillDTO>> getAllBills() {
        return new ResponseEntity<>(this.billService.getAllBills(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BillDTO> findBillById(@PathVariable String id) {
        return new ResponseEntity<>(this.billService.findBillById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BillDTO>> findBillByTitle(@PathVariable String title) {
        return new ResponseEntity<>(this.billService.findBillByTitle(title), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<BillDTO> deleteBill(@PathVariable String id) {
        return new ResponseEntity<>(this.billService.deleteBill(id), HttpStatus.OK);
    }
}
