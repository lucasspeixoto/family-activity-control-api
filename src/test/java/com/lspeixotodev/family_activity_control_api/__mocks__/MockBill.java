package com.lspeixotodev.family_activity_control_api.__mocks__;

import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.CreateBillDTO;
import com.lspeixotodev.family_activity_control_api.dto.bill.UpdateBillDTO;
import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import com.lspeixotodev.family_activity_control_api.entity.bill.BillType;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class MockBill {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Bill getBill() throws ParseException {
        return mockBill();
    }

    public Bill getSecondBill() throws ParseException {
        return mockSecondBill();
    }

    public CreateBillDTO getCreateBillDTO() throws ParseException {
        return mockCreateBillDTO();
    }

    public UpdateBillDTO getUpdateBillDTO() throws ParseException {
        return mockUpdateBillDTO();
    }

    public BillDTO getBillDTO() throws ParseException {
        return mockBillDTO();
    }


    private Bill mockBill() throws ParseException {
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Bill bill = new Bill();
        bill.setId(UUID.randomUUID());
        bill.setTitle("Energia");
        bill.setOwner("Lucas P");
        bill.setAmount(new BigDecimal(89.50));
        bill.setCategory("Contas");
        bill.setDescription("Pagar a conta de energia");
        bill.setFinishAt(simpleDateFormat.parse("30/10/2991"));
        bill.setType(BillType.FIXED);

        return bill;
    }

    private CreateBillDTO mockCreateBillDTO() throws ParseException {
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        CreateBillDTO bill = new CreateBillDTO();
        bill.setId(UUID.randomUUID().toString());
        bill.setTitle("Energia");
        bill.setOwner("Lucas P");
        bill.setAmount(new BigDecimal(89.50));
        bill.setCategory("Contas");
        bill.setDescription("Pagar a conta de energia");
        bill.setFinishAt(simpleDateFormat.parse("30/10/2991"));
        bill.setType(BillType.FIXED);

        return bill;
    }

    private UpdateBillDTO mockUpdateBillDTO() throws ParseException {
        UpdateBillDTO bill = new UpdateBillDTO();
        bill.setId(UUID.randomUUID().toString());
        bill.setTitle("Energia");
        bill.setOwner("Lucas P");
        bill.setAmount(new BigDecimal(89.50));
        bill.setCategory("Contas");
        bill.setDescription("Pagar a conta de energia");
        bill.setFinishAt(simpleDateFormat.parse("30/10/2991"));
        bill.setType(BillType.FIXED);

        return bill;
    }

    private BillDTO mockBillDTO() throws ParseException {
        BillDTO bill = new BillDTO();
        bill.setId(UUID.randomUUID().toString());
        bill.setTitle("Energia");
        bill.setOwner("Lucas P");
        bill.setAmount(new BigDecimal(89.50));
        bill.setCategory("Contas");
        bill.setDescription("Pagar a conta de energia");
        bill.setFinishAt(simpleDateFormat.parse("30/10/2991"));
        bill.setType(BillType.FIXED);

        return bill;
    }

    private Bill mockSecondBill() throws ParseException {
        Bill anotherBill = new Bill();
        anotherBill.setId(UUID.randomUUID());
        anotherBill.setTitle("Internet");
        anotherBill.setOwner("Lucas P");
        anotherBill.setAmount(new BigDecimal(120));
        anotherBill.setCategory("Contas");
        anotherBill.setDescription("Pagar a conta de internet");
        anotherBill.setFinishAt(simpleDateFormat.parse("30/10/2991"));
        anotherBill.setType(BillType.FIXED);
        
        return anotherBill;
    }
}
