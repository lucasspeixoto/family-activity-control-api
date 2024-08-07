package com.lspeixotodev.family_activity_control_api.service;

import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;

import java.util.List;

public interface BillService {

    BillDTO createBill(BillDTO createBillDTO, String userId);

    BillDTO updateBill(BillDTO updateBillDTO, String id);

    List<BillDTO> findAllBills(String userId);

    BillDTO findBillById(String id);


    BillDTO deleteBill(String id);

    List<BillDTO> findBillByTitleAndUser(String name, String userId);

}
