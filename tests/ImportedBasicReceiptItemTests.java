import com.esiljak.helpers.NumberHelper;
import com.esiljak.models.BasicReceiptItem;
import com.esiljak.models.ImportedBasicReceiptItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImportedBasicReceiptItemTests {
    private final String ITEM_NAME = "item name";
    private final float PRICE = 10;
    private final int QUANTITY = 2;

    private ImportedBasicReceiptItem item;

    @BeforeEach
    void initialize() throws Exception {
        item = new ImportedBasicReceiptItem(ITEM_NAME, PRICE, QUANTITY);
    }

    @Test
    void checkSuperclassTest(){
        assertTrue(item instanceof BasicReceiptItem, "ImportedBasicReceiptItem class has to extend BasicReceiptItem class");
    }

    @Test
    void constructorTest(){
        assertEquals(ITEM_NAME, item.getName(), "Name not set correctly through the constructor");
        assertEquals(PRICE, item.getPrice(), "Price not set correctly through the constructor");
        assertEquals(QUANTITY, item.getQuantity(), "Quantity not set correctly through the constructor");
    }

    @Test
    void setterTest() throws Exception{
        item.setName(ITEM_NAME + " new");
        assertEquals(ITEM_NAME + " new", item.getName(), "Name not set correctly through the setter");

        item.setPrice(PRICE - 2);
        assertEquals(PRICE - 2, item.getPrice(), "Price not set correctly through the setter");

        item.setQuantity(QUANTITY * 2);
        assertEquals(QUANTITY * 2, item.getQuantity(), "Quantity not set correctly through the setter");
    }

    @Test
    void calculateTaxTest(){
        float tax = 15 * (PRICE * QUANTITY) / 100;
        assertEquals(NumberHelper.roundUpTax(tax), item.calculateTax(), "Tax not calculated by the correct formula");
    }

    @Test
    void toStringTest(){
        float expectedPriceWithTax = NumberHelper.roundUpTax(15 * (PRICE * QUANTITY) / 100) + item.getPrice();
        String expectedOutput = QUANTITY + " " + ITEM_NAME + ": " + expectedPriceWithTax;
        assertEquals(expectedOutput, item.toString());
    }
}
