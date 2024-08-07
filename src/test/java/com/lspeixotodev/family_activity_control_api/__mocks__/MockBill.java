package com.lspeixotodev.family_activity_control_api.__mocks__;

import com.lspeixotodev.family_activity_control_api.dto.bill.BillDTO;
import com.lspeixotodev.family_activity_control_api.entity.authentication.User;
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

    public User getFirstBillUser() {
        return mockFirstBillUser();
    }

    public User getSecondBillUser() {
        return mockSecondBillUser();
    }

    public Category getCategoryForBill() {
        return mockCategoryForBill();
    }


    private Bill mockBill() throws ParseException {

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Bill bill = new Bill();
        bill.setId(UUID.randomUUID());
        bill.setTitle("Energia");
        bill.setOwner("Lucas P");
        bill.setAmount(new BigDecimal("89.50"));
        bill.setDescription("Pagar a conta de energia");
        bill.setFinishAt(simpleDateFormat.parse("30/10/1991"));
        bill.setType(BillType.FIXED);

        bill.setUser(mockFirstBillUser());

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
        bill.setFinishAt(simpleDateFormat.parse("30/10/1991"));
        bill.setType(BillType.FIXED);

        return bill;
    }

    private Bill mockSecondBill() throws ParseException {
        Category category = mockCategoryForBill();
        Bill anotherBill = new Bill();
        anotherBill.setId(UUID.randomUUID());
        anotherBill.setTitle("Internet");
        anotherBill.setOwner("Lucas P");
        anotherBill.setAmount(new BigDecimal(120));
        anotherBill.setCategory(category);
        anotherBill.setDescription("Pagar a conta de internet");
        anotherBill.setFinishAt(simpleDateFormat.parse("30/10/1991"));
        anotherBill.setType(BillType.FIXED);

        anotherBill.setUser(mockSecondBillUser());
        
        return anotherBill;
    }

    private Category mockCategoryForBill() {
        Category category = new Category();
        category.setId(UUID.fromString("d8695c01-b127-4f1f-a1ca-d031876231a2"));
        category.setTitle("Contas");
        category.setDescription("Contas de mês");

        return category;
    }

    private User mockFirstBillUser() {
        User user = new User();
        user.setId(UUID.fromString("fd361c69-4657-44da-917f-0ee7c00e491d"));
        user.setName("Lucas Peixoto");
        user.setEmail("lspeixotodev@gmail.com");
        user.setUsername("lspeixotodev");
        user.setPassword("password123");

        return user;
    }

    private User mockSecondBillUser() {
        User user = new User();
        user.setId(UUID.fromString("b4c8d28e-a537-4a89-86de-171da4c56c5f"));
        user.setName("Admin");
        user.setEmail("admin@email.com");
        user.setUsername("admin");
        user.setPassword("admin");

        return user;
    }
}
