package com.lspeixotodev.family_activity_control_api.service;

import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.CreateBillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.UpdateBillDTO;

import java.util.List;

public interface BillService {

    CreateBillDTO createBill(CreateBillDTO bill);

    List<CreateBillDTO> getAllBills();

    CreateBillDTO findBillById(String id);

    UpdateBillDTO updateBill(UpdateBillDTO CreateBillDTO, String id);

    CreateBillDTO deleteBill(String id);

    BillDTO findBillByTitle(String name);
}
