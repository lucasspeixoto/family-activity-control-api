package com.lspeixotodev.family_activity_control_api.service;

import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.CreateBillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.UpdateBillDTO;

import java.util.List;

public interface BillService {

    BillDTO createBill(CreateBillDTO bill);

    List<BillDTO> getAllBills();

    BillDTO findBillById(String id);

    BillDTO updateBill(UpdateBillDTO CreateBillDTO, String id);

    BillDTO deleteBill(String id);

    BillDTO findBillByTitle(String name);
}
