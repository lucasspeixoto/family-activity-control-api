package com.lspeixotodev.family_activity_control_api.service;

import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;

import java.util.List;

public interface BillService {

    BillDTO createBill(BillDTO createBillDTO);

    BillDTO updateBill(BillDTO updateBillDTO, String id);

    List<BillDTO> getAllBills();

    BillDTO findBillById(String id);


    BillDTO deleteBill(String id);

    List<BillDTO> findBillByTitle(String name);
}
