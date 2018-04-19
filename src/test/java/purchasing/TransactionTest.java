package purchasing;

import models.Depot;
import models.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionTest {

    Transaction transactionClass = Transaction.getInstance();
    Depot buyer = new Depot();
    Depot seller = new Depot();

    @Before
    public void setUp() {

        int stockMin = 30;
        int storageMin = 50;

        List<Product> storage = new ArrayList<>();
        List<Product> stock = new ArrayList<>();


        for (int i = 0; i < stockMin; i++) {

            Product newProduct = new Product();
            stock.add(newProduct);
        }

        for (int i = 0; i < storageMin; i++) {

            Product newProduct = new Product();
            storage.add(newProduct);
        }

        buyer.setCashAllowance(75);
        buyer.setDelivery(5);
        buyer.setOwner("Buyer Depot");
        buyer.setStockMax(50);
        buyer.setStockMin(15);
        buyer.setStorageMax(80);
        buyer.setStorageMin(6);
        buyer.setStorageList(storage);
        buyer.setStockList(stock);

        seller.setCashAllowance(60);
        seller.setDelivery(5);
        seller.setOwner("Seller Depot");
        seller.setStockMax(50);
        seller.setStockMin(15);
        seller.setStorageMax(80);
        seller.setStorageMin(6);
        seller.setStorageList(storage);
        seller.setStockList(stock);
    }

    @Test
    public void should_Be_Ready_To_Buy() {
        boolean actual = transactionClass.isReadyToBuy(buyer, seller);
        assertTrue(actual);
    }

    @Test
    public void should_Not_Surpass_Max_Cash_Allowance() {
        int maxSellersCashAllowance = 100;
        int allowedIncome = (maxSellersCashAllowance - seller.getCashAllowance()) - seller.getDelivery();

        int actual = transactionClass.accountForMaxCashAllowance(
                10,
                5,
                allowedIncome);

        assertEquals(7, actual);
    }

    @Test
    public void IDontKnowForNow() {
        int maxSellersCashAllowance = 100;
        int allowedIncome = (maxSellersCashAllowance - seller.getCashAllowance()) - seller.getDelivery();

        int actual = transactionClass.quantityToBuy(
                seller,
                15);

        assertEquals(7, actual);
    }
}