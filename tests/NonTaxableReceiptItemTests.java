import com.esiljak.exceptions.IllegalItemNameException;
import com.esiljak.exceptions.IllegalPriceException;
import com.esiljak.exceptions.IllegalQuantityException;
import com.esiljak.models.NonTaxableReceiptItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NonTaxableReceiptItemTests {
    private final String ITEM_NAME = "non taxable";
    private final float PRICE = 5f;
    private final int QUANTITY = 3;

    @Test
    void basicCreateTest() throws Exception {
        NonTaxableReceiptItem item = new NonTaxableReceiptItem(ITEM_NAME, PRICE, QUANTITY);

        assertEquals(ITEM_NAME, item.getName(), "Item name not set correctly through the constructor");
        assertEquals(PRICE, item.getPrice(), "Item price not set correctly through the constructor");
        assertEquals(QUANTITY, item.getQuantity(), "Item quantity not set correctly through the constructor");
    }

    @Test
    void noQuantityPassedTest() throws Exception {
        NonTaxableReceiptItem item = new NonTaxableReceiptItem(ITEM_NAME, PRICE);
        assertEquals(1, item.getQuantity(), "Quantity must be 1 if it's not passed in the constructor");
    }

    @Test
    void setterTest() throws Exception{
        NonTaxableReceiptItem item = new NonTaxableReceiptItem(ITEM_NAME, PRICE, QUANTITY);

        item.setName("new name");
        assertEquals("new name", item.getName(), "Name not set properly through setter");

        item.setPrice(PRICE * 2);
        assertEquals(PRICE * 2, item.getPrice(), "Price not set properly through setter");

        item.setQuantity(QUANTITY - 2);
        assertEquals(QUANTITY - 2, item.getQuantity(), "Quantity not set properly through setter");
    }

    @Test
    void nonPositiveQuantity() {
        assertThrows(IllegalQuantityException.class, () -> {
           new NonTaxableReceiptItem(ITEM_NAME, PRICE, 0);
        }, "Quantity cannot be negative or zero - constructor");

        assertThrows(IllegalQuantityException.class, () -> {
            NonTaxableReceiptItem item = new NonTaxableReceiptItem(ITEM_NAME, PRICE, QUANTITY);
            item.setQuantity(0);
        }, "Quantity cannot be negative or zero - setter");
    }

    @Test
    void negativePriceTest(){
        assertThrows(IllegalPriceException.class, () -> {
            new NonTaxableReceiptItem(ITEM_NAME, -1f);
        }, "Price cannot be negative - constructor");

        assertThrows(IllegalPriceException.class, () -> {
            NonTaxableReceiptItem item = new NonTaxableReceiptItem(ITEM_NAME, PRICE);
            item.setPrice(-1f);
        }, "Price cannot be negative - setter");

        assertDoesNotThrow(() -> {
            NonTaxableReceiptItem item = new NonTaxableReceiptItem(ITEM_NAME, 0f);
            item.setPrice(0f);
        }, "Price can be zero - constructor and setter");
    }

    @Test
    void emptyNameTest(){
        assertThrows(IllegalItemNameException.class, () -> {
           new NonTaxableReceiptItem("", PRICE);
        }, "Name cannot be empty - constructor");

        assertThrows(IllegalItemNameException.class, () -> {
            NonTaxableReceiptItem item = new NonTaxableReceiptItem(ITEM_NAME, PRICE);
            item.setName("  ");
        }, "Name cannot be empty - setter");

        assertThrows(IllegalItemNameException.class, () -> {
            new NonTaxableReceiptItem(null, PRICE);
        }, "Name cannot be null - constructor");

        assertThrows(IllegalItemNameException.class, () -> {
            NonTaxableReceiptItem item = new NonTaxableReceiptItem(ITEM_NAME, PRICE);
            item.setName(null);
        }, "Name cannot be null - setter");
    }

    @Test
    void calculateTaxTest() throws Exception{
        NonTaxableReceiptItem item = new NonTaxableReceiptItem(ITEM_NAME, PRICE, QUANTITY);
        assertEquals(0, item.calculateTax(), "For non taxable item tax has to be zero");

        item.setQuantity(1);
        assertEquals(0, item.calculateTax(), "For non taxable item tax has to be zero");
    }
}