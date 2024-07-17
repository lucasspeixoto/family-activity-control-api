package com.lspeixotodev.family_activity_control_api.__mocks__;

import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;
import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import com.lspeixotodev.family_activity_control_api.entity.bill.BillType;
import com.lspeixotodev.family_activity_control_api.entity.category.Category;

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

    public BillDTO getBillDTO() throws ParseException {
        return mockBillDTO();
    }


    private Bill mockBill() throws ParseException {

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Bill bill = new Bill();
        bill.setId(UUID.randomUUID());
        bill.setTitle("Energia");
        bill.setOwner("Lucas P");
        bill.setAmount(new BigDecimal("89.50"));
        bill.setDescription("Pagar a conta de energia");
        bill.setFinishAt(simpleDateFormat.parse("30/10/2991"));
        bill.setType(BillType.FIXED);

        return bill;
    }

    private BillDTO mockBillDTO() throws ParseException {
        Category category = mockCategoryForBill();

        BillDTO bill = new BillDTO();
        bill.setId(UUID.randomUUID().toString());
        bill.setTitle("Energia");
        bill.setOwner("Lucas P");
        bill.setAmount(new BigDecimal("89.50"));
        bill.setCategoryId(category.getId().toString());
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
        anotherBill.setDescription("Pagar a conta de internet");
        anotherBill.setFinishAt(simpleDateFormat.parse("30/10/2991"));
        anotherBill.setType(BillType.FIXED);
        
        return anotherBill;
    }

    private Category mockCategoryForBill() {
        Category category = new Category();
        category.setId(UUID.fromString("8de274fd-6a14-46be-9816-4552a71f9e16"));
        category.setTitle("Academia/Ginástica");
        category.setDescription("Academia/Ginástica para os filhos");

        return category;
    }
}
