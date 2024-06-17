package com.lspeixotodev.family_activity_control_api.__mocks__;

import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import com.lspeixotodev.family_activity_control_api.entity.bill.BillType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

public class MockBill {

    public Bill getBill() {
        return mockBill();
    }

    public Bill getSecondBill() {
        return mockSecondBill();
    }


    private Bill mockBill() {
        Bill bill = new Bill();
        bill.setTitle("Energia");
        bill.setOwner("Lucas P");
        bill.setAmount(new BigDecimal(89));
        bill.setCategory("Contas");
        bill.setDescription("Pagar a conta de energia");
        bill.setFinishAt(Date.from(Instant.now()));
        bill.setType(BillType.FIXED);

        return bill;
    }

    private Bill mockSecondBill() {
        Bill anotherBill = new Bill();
        anotherBill.setTitle("Internet");
        anotherBill.setOwner("Lucas P");
        anotherBill.setAmount(new BigDecimal(120));
        anotherBill.setCategory("Contas");
        anotherBill.setDescription("Pagar a conta de internet");
        anotherBill.setFinishAt(Date.from(Instant.now()));
        anotherBill.setType(BillType.FIXED);
        
        return anotherBill;
    }
}
