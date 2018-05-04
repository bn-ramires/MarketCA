package purchasing;

import models.Depot;
import models.Product;
import models.Ticket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TransactionTest {

    Transaction transactionClass = Transaction.getInstance();
    Depot buyerDepot = new Depot();
    Depot sellerDepot = new Depot();

    @Before
    public void setUp() {

        int stockMin = 30;
        int storageMin = 50;

        List<Product> storage = new ArrayList<>();
        List<Product> stock = new ArrayList<>();


        initProducts(stockMin, stock);
        initProducts(storageMin, storage);

        buyerDepot.setCashAllowance(75);
        buyerDepot.setDeliveryCost(5);
        buyerDepot.setOwner("Buyer Depot");
        buyerDepot.setStockMax(50);
        buyerDepot.setStockMin(15);
        buyerDepot.setStorageMax(80);
        buyerDepot.setStorageMin(6);
        buyerDepot.setStorageList(storage);
        buyerDepot.setStockList(stock);

        sellerDepot.setCashAllowance(60);
        sellerDepot.setDeliveryCost(5);
        sellerDepot.setOwner("Seller Depot");
        sellerDepot.setStockMax(50);
        sellerDepot.setStockMin(15);
        sellerDepot.setStorageMax(80);
        sellerDepot.setStorageMin(6);
        sellerDepot.setStorageList(storage);
        sellerDepot.setStockList(stock);

        System.out.println("Before: \n" + buyerDepot.toString());
        System.out.println("Before: \n" + sellerDepot.toString());
    }

    private void initProducts(int stockMin, List<Product> stock) {
        for (int i = 0; i < stockMin; i++) {

            Product newProduct = new Product();
            newProduct.setBrand("test");
            newProduct.setPrice(5);
            stock.add(newProduct);
        }
    }

    @After
    public void tearDown(){
        System.out.println("After: \n" + buyerDepot.toString());
        System.out.println("After: \n" + sellerDepot.toString());
    }

    @Test
    public void should_Be_Ready_To_Buy() {
        boolean actual = transactionClass.isCapableOfBuying(buyerDepot, sellerDepot);
        assertTrue(actual);
    }

    @Test
    public void should_Not_Surpass_Max_Cash_Allowance() {
        int maxSellersCashAllowance = 100;
        int allowedIncome = (maxSellersCashAllowance - sellerDepot.getCashAllowance()) - sellerDepot.getDeliveryCost();

        int actual = transactionClass.accountForMaxCashAllowance(
                10,
                5,
                allowedIncome);

        assertEquals(7, actual);
    }

    @Test
    public void should_Perform_Purchase_With_All_Requirements_Accounted_For() {
        int maxSellersCashAllowance = 100;
        int allowedIncome = (maxSellersCashAllowance - sellerDepot.getCashAllowance()) - sellerDepot.getDeliveryCost();

        transactionClass.buy(25, buyerDepot, sellerDepot);

    }

    @Test
    public void ticket_Properties_Should_Match_With_Tested_One() {
        Ticket actual = transactionClass.generateTicket
                (7, buyerDepot, sellerDepot);

        Ticket expected = new Ticket(
                "Buyer Depot",
                "Seller Depot",
                -1,
                -1,
                5,
                7,
                5
        );

        System.out.println(actual.toString());
        System.out.println(expected.toString());
        assertThat(actual, samePropertyValuesAs(expected));
    }
}