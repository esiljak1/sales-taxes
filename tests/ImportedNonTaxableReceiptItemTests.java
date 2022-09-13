import com.esiljak.helpers.NumberHelper;
import com.esiljak.models.NonTaxableReceiptItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImportedNonTaxableReceiptItemTests {
    private final String ITEM_NAME = "non taxable";
    private final float PRICE = 10f;
    private final int QUANTITY = 2;

    private ImportedNonTaxableReceiptItem item;

    @BeforeEach
    void initialize(){
        item = new ImportedNonTaxableReceiptItem(ITEM_NAME, PRICE, QUANTITY);
    }

    @Test
    void checkSuperclassTest(){
        assertTrue(item instanceof NonTaxableReceiptItem, "ImportedNonTaxableReceiptItem class has to be derived from NonTaxableReceiptItem class");
    }

    @Test
    void constructorTest(){
        assertEquals(ITEM_NAME, item.getName(), "Item name not set correctly through constructor");
        assertEquals(PRICE, item.getPrice(), "Item price not set correctly through constructor");
        assertEquals(QUANTITY, item.getQuantity(), "Item quantity not set correctly through constructor");
    }

    @Test
    void setterTest(){
        item.setName("new name");
        assertEquals("new name", item.getName(), "Item name not set correctly through setter");

        item.setPrice(PRICE + 1);
        assertEquals(PRICE + 1, item.getPrice(), "Item price not set correctly through setter");

        item.setQuantity(QUANTITY + 1);
        assertEquals(QUANTITY + 1, item.getQuantity(), "Item quantity not set correctly through setter");
    }

    @Test
    void calculateTaxTest(){
        float tax = 5 * (PRICE * QUANTITY) / 100;
        assertEquals(NumberHelper.roundUpTax(tax), item.calculateTax(), "Tax not calculated by the correct formula");
    }
}
