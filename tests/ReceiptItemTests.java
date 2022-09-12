import com.esiljak.model.ReceiptItem;
import com.esiljak.exceptions.IllegalItemNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptItemTests {
    private final String ITEM_NAME = "item name";
    private final float PRICE = 10;
    private final int QUANTITY = 2;

    @Test
    void createBasicReceiptItemTest() throws IllegalItemNameException {
        ReceiptItem item = new ReceiptItem(ITEM_NAME, PRICE, QUANTITY);
        assertEquals(PRICE, item.getPrice(), "Price not set right through constructor");
        assertEquals(QUANTITY, item.getQuantity(), "Quantity not set right through constructor");
    }

    @Test
    void noQuantityPassedTest() throws IllegalItemNameException {
        ReceiptItem item = new ReceiptItem(ITEM_NAME, PRICE);
        assertEquals(1, item.getQuantity(), "Quantity must be 1 if it's not passed in the constructor");
    }

    @Test
    void setterTest() throws IllegalItemNameException {
        ReceiptItem item = new ReceiptItem(ITEM_NAME, PRICE, QUANTITY);

        item.setPrice(2*PRICE);
        assertEquals(2*PRICE, item.getPrice(), "Price not set right through the setter");

        item.setQuantity(2*QUANTITY);
        assertEquals(2*QUANTITY, item.getQuantity(), "Quantity not set right through the setter");

        item.setItemName(ITEM_NAME + " 2");
        assertEquals(ITEM_NAME + " 2", item.getItemName(), "Item name not set right through the setter");
    }

    @Test
    void quantityIsPositiveTest(){
        assertThrows(IllegalQuantityException.class, () -> {
            new ReceiptItem(ITEM_NAME, PRICE, 0);
        }, "Quantity cannot be negative or zero - constructor");

        assertThrows(IllegalQuantityException.class, () -> {
           ReceiptItem item = new ReceiptItem(ITEM_NAME, PRICE, QUANTITY);
           item.setQuantity(0);
        }, "Quantity cannot be negative or zero - setter");
    }

    @Test
    void priceIsNotNegativeTest(){
        assertThrows(IllegalPriceException.class, () -> {
           new ReceiptItem(ITEM_NAME, -0.5f);
        }, "Price cannot be negative - constructor");

        assertThrows(IllegalPriceException.class, () -> {
            ReceiptItem item = new ReceiptItem(ITEM_NAME, PRICE);
            item.setPrice(-0.5f);
        }, "Price cannot be negative - setter");

        assertDoesNotThrow(IllegalPriceException.class, () -> {
            ReceiptItem item = new ReceiptItem(ITEM_NAME, 0);
            item.setPrice(0);
        }, "Price can be set to zero - constructor and setter");
    }

    @Test
    void nameNotEmptyTest(){
        assertThrows(IllegalItemNameException.class, () -> {
            new ReceiptItem("", PRICE, QUANTITY);
        }, "Item name cannot be empty string - constructor");

        assertThrows(IllegalItemNameException.class, () -> {
            ReceiptItem item = new ReceiptItem(ITEM_NAME, PRICE, QUANTITY);
            item.setItemName("");
        }, "Item name cannot be empty string - setter");
    }

    @Test
    void calculateTaxBasicTest() throws IllegalItemNameException {
        ReceiptItem item = new ReceiptItem(ITEM_NAME, PRICE, QUANTITY);
        float expectedTax = (PRICE * QUANTITY) / 100;

        assertEquals(expectedTax, item.calculateTax(), "Tax not calculated by the correct formula");
    }
}
