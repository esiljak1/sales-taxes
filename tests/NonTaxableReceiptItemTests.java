import com.esiljak.exceptions.IllegalItemNameException;
import com.esiljak.exceptions.IllegalPriceException;
import com.esiljak.exceptions.IllegalQuantityException;
import com.esiljak.models.NonTaxableReceiptItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NonTaxableReceiptItemTests {
    private final String ITEM_NAME = "non taxable";
    private final float PRICE = 5f;
    private final int QUANTITY = 3;
    private NonTaxableReceiptItem item;

    @BeforeEach
    void initialize() throws Exception{
        item = new NonTaxableReceiptItem(ITEM_NAME, PRICE, QUANTITY);
    }

    @Test
    void basicCreateTest(){
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
            item.setQuantity(0);
        }, "Quantity cannot be negative or zero - setter");

        assertThrows(IllegalQuantityException.class, () -> {
           new NonTaxableReceiptItem(ITEM_NAME, PRICE, 0);
        }, "Quantity cannot be negative or zero - constructor");
    }

    @Test
    void negativePriceTest(){
        assertThrows(IllegalPriceException.class, () -> {
            item.setPrice(-1f);
        }, "Price cannot be negative - setter");

        assertThrows(IllegalPriceException.class, () -> {
            new NonTaxableReceiptItem(ITEM_NAME, -1f);
        }, "Price cannot be negative - constructor");

        assertDoesNotThrow(() -> {
            NonTaxableReceiptItem testItem = new NonTaxableReceiptItem(ITEM_NAME, 0f);
            testItem.setPrice(0f);
        }, "Price can be zero - constructor and setter");
    }

    @Test
    void emptyNameTest(){
        assertThrows(IllegalItemNameException.class, () -> {
            item.setName("  ");
        }, "Name cannot be empty - setter");

        assertThrows(IllegalItemNameException.class, () -> {
            item.setName(null);
        }, "Name cannot be null - setter");

        assertThrows(IllegalItemNameException.class, () -> {
           new NonTaxableReceiptItem("", PRICE);
        }, "Name cannot be empty - constructor");

        assertThrows(IllegalItemNameException.class, () -> {
            new NonTaxableReceiptItem(null, PRICE);
        }, "Name cannot be null - constructor");
    }

    @Test
    void calculateTaxTest() throws Exception{
        assertEquals(0, item.calculateTax(), "For non taxable item tax has to be zero");

        item.setQuantity(1);
        assertEquals(0, item.calculateTax(), "For non taxable item tax has to be zero");
    }

    @Test
    void priceWithTaxTest(){
        float expectedPriceWithTax = item.getPrice();

        assertEquals(expectedPriceWithTax, item.getPriceWithTax(), "Tax not correctly added to the price");
    }
}
