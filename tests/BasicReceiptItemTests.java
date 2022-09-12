import com.esiljak.exceptions.IllegalItemNameException;
import com.esiljak.exceptions.IllegalPriceException;
import com.esiljak.exceptions.IllegalQuantityException;
import com.esiljak.helpers.NumberHelper;
import com.esiljak.models.BasicReceiptItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasicReceiptItemTests {
    private final String ITEM_NAME = "item name";
    private final float PRICE = 10;
    private final int QUANTITY = 2;

    @Test
    void createBasicReceiptItemTest() throws Exception {
        BasicReceiptItem item = new BasicReceiptItem(ITEM_NAME, PRICE, QUANTITY);
        assertEquals(PRICE, item.getPrice(), "Price not set right through constructor");
        assertEquals(QUANTITY, item.getQuantity(), "Quantity not set right through constructor");
    }

    @Test
    void noQuantityPassedTest() throws Exception {
        BasicReceiptItem item = new BasicReceiptItem(ITEM_NAME, PRICE);
        assertEquals(1, item.getQuantity(), "Quantity must be 1 if it's not passed in the constructor");
    }

    @Test
    void setterTest() throws Exception {
        BasicReceiptItem item = new BasicReceiptItem(ITEM_NAME, PRICE, QUANTITY);

        item.setPrice(2*PRICE);
        assertEquals(2*PRICE, item.getPrice(), "Price not set right through the setter");

        item.setQuantity(2*QUANTITY);
        assertEquals(2*QUANTITY, item.getQuantity(), "Quantity not set right through the setter");

        item.setName(ITEM_NAME + " 2");
        assertEquals(ITEM_NAME + " 2", item.getName(), "Item name not set right through the setter");
    }

    @Test
    void nonNegativeQuantityTest(){
        assertThrows(IllegalQuantityException.class, () -> {
            new BasicReceiptItem(ITEM_NAME, PRICE, 0);
        }, "Quantity cannot be negative or zero - constructor");

        assertThrows(IllegalQuantityException.class, () -> {
           BasicReceiptItem item = new BasicReceiptItem(ITEM_NAME, PRICE, QUANTITY);
           item.setQuantity(0);
        }, "Quantity cannot be negative or zero - setter");
    }

    @Test
    void negativePriceTest(){
        assertThrows(IllegalPriceException.class, () -> {
           new BasicReceiptItem(ITEM_NAME, -0.5f);
        }, "Price cannot be negative - constructor");

        assertThrows(IllegalPriceException.class, () -> {
            BasicReceiptItem item = new BasicReceiptItem(ITEM_NAME, PRICE);
            item.setPrice(-0.5f);
        }, "Price cannot be negative - setter");

        assertDoesNotThrow(() -> {
            BasicReceiptItem item = new BasicReceiptItem(ITEM_NAME, 0);
            item.setPrice(0);
        }, "Price can be set to zero - constructor and setter");
    }

    @Test
    void emptyNameTest(){
        assertThrows(IllegalItemNameException.class, () -> {
            new BasicReceiptItem("", PRICE, QUANTITY);
        }, "Item name cannot be empty string - constructor");

        assertThrows(IllegalItemNameException.class, () -> {
            BasicReceiptItem item = new BasicReceiptItem(ITEM_NAME, PRICE, QUANTITY);
            item.setName("");
        }, "Item name cannot be empty string - setter");

        assertThrows(IllegalItemNameException.class, () -> {
            new BasicReceiptItem(null, PRICE, QUANTITY);
        }, "Item name cannot be null - constructor");

        assertThrows(IllegalItemNameException.class, () -> {
            BasicReceiptItem item = new BasicReceiptItem(ITEM_NAME, PRICE, QUANTITY);
            item.setName(null);
        }, "Item name cannot be null - setter");
    }

    @Test
    void calculateTaxBasicTest() throws Exception {
        BasicReceiptItem item = new BasicReceiptItem(ITEM_NAME, PRICE, QUANTITY);
        float expectedTax = 10 * (PRICE * QUANTITY) / 100;

        assertEquals(NumberHelper.roundUpTax(expectedTax), item.calculateTax(), "Tax not calculated by the correct formula");
    }
}
