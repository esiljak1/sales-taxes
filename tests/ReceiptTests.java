import com.esiljak.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTests {
    private final String ITEM_NAME = "name";
    private final float PRICE = 5f;
    private final int QUANTITY = 3;
    private Receipt receipt;

    private void addBasicElements(int numberOfElements) throws Exception{
        for(int i = 0; i < numberOfElements; i++){
            receipt.addItem(new BasicReceiptItem(ITEM_NAME + " " + i, PRICE, QUANTITY));
        }
    }

    private void addNonTaxableElements(int numberOfElements) throws Exception{
        for(int i = 0; i < numberOfElements; i++){
            receipt.addItem(new NonTaxableReceiptItem(ITEM_NAME + " " + i, PRICE, QUANTITY));
        }
    }

    private float calculatePrice(){
        return ((float) receipt.getItems().stream().mapToDouble(ReceiptItem::getPrice).sum());
    }

    private float calculateTax(){
        return ((float) receipt.getItems().stream().mapToDouble(ReceiptItem::calculateTax).sum());
    }

    @BeforeEach
    void initialize(){
        receipt = new Receipt();
    }

    @Test
    void basicConstructorTest(){
        assertTrue(receipt.getItems() instanceof List<ReceiptItem>, "Items have to be a list of ReceiptItems");

        assertEquals(0, receipt.getItems().size(), "Items have to be initialized with no elements");
    }

    @Test
    void setterTest() throws Exception{
        BasicReceiptItem item = new BasicReceiptItem(ITEM_NAME, PRICE, QUANTITY);
        receipt.setItems(List.of(item));

        assertEquals(1, receipt.getItems().size(), "Items not set correctly through setter");
        assertSame(item, receipt.getItems().get(0), "Element of list has to be the same element we added");
    }

    @Test
    void basicItemTest() throws Exception{
        receipt.addItem(new BasicReceiptItem(ITEM_NAME, PRICE, QUANTITY));

        assertTrue(receipt.getItems().get(0) instanceof BasicReceiptItem,
                "You should be able to add instances of BasicReceiptItem class");
    }

    @Test
    void nonTaxableItemTest() throws Exception{
        receipt.addItem(new NonTaxableReceiptItem(ITEM_NAME, PRICE, QUANTITY));

        assertTrue(receipt.getItems().get(0) instanceof NonTaxableReceiptItem,
                "You should be able to add instances of NonTaxableReceiptItem class");
    }

    @Test
    void importedBasicItemTest() throws Exception{
        receipt.addItem(new ImportedBasicReceiptItem(ITEM_NAME, PRICE, QUANTITY));

        assertTrue(receipt.getItems().get(0) instanceof ImportedBasicReceiptItem,
                "You should be able to add instances of ImportedBasicReceiptItem class");
    }

    @Test
    void importedNonTaxableItemTest() throws Exception{
        receipt.addItem(new ImportedNonTaxableReceiptItem(ITEM_NAME, PRICE, QUANTITY));

        assertTrue(receipt.getItems().get(0) instanceof ImportedNonTaxableReceiptItem,
                "You should be able to add instances of ImportedNonTaxableReceiptItem class");
    }

    @Test
    void differentItemTypesTest(){
        assertDoesNotThrow(() -> {
            receipt.addItem(new BasicReceiptItem(ITEM_NAME, PRICE, QUANTITY));
            receipt.addItem(new NonTaxableReceiptItem(ITEM_NAME, PRICE, QUANTITY));
            receipt.addItem(new ImportedBasicReceiptItem(ITEM_NAME, PRICE, QUANTITY));
            receipt.addItem(new ImportedNonTaxableReceiptItem(ITEM_NAME, PRICE, QUANTITY));
        }, "You should be able to have all the different item types in the same list");
    }

    @Test
    void basicTaxesTest() throws Exception{
        addBasicElements(2);

        assertEquals(calculateTax(), receipt.calculateTax(),
                "Tax not calculated by the correct formula");
    }

    @Test
    void totalPriceTest() throws Exception{
        addBasicElements(3);
        addNonTaxableElements(3);

        assertEquals(calculateTax() + calculatePrice(), receipt.calculateTotalPrice(),
                "Total price not calculated by the correct formula");
    }

    @Test
    void challengeTest1() throws Exception{
        receipt.addItem(new NonTaxableReceiptItem("book", 12.49f));
        receipt.addItem(new BasicReceiptItem("music CD", 14.99f));
        receipt.addItem(new NonTaxableReceiptItem("chocolate bar", 0.85f));

        assertEquals(1.50f, receipt.calculateTax(), "Tax not calculated correctly");
        assertEquals(29.83f, receipt.calculateTotalPrice(), "Total price not calculated correctly");
    }

    @Test
    void challengeTest2() throws Exception{
        receipt.addItem(new ImportedNonTaxableReceiptItem("box of chocolates", 10f));
        receipt.addItem(new ImportedBasicReceiptItem("bottle of perfume", 47.50f));

        assertEquals(7.65f, receipt.calculateTax(), "Tax not calculated correctly");
        assertEquals(65.15f, receipt.calculateTotalPrice(), "Total price not calculated correctly");
    }

    @Test
    void challengeTest3() throws Exception{
        receipt.addItem(new ImportedBasicReceiptItem("bottle of perfume", 27.99f));
        receipt.addItem(new BasicReceiptItem("bottle of perfume", 18.99f));
        receipt.addItem(new NonTaxableReceiptItem("packet of headache pills", 9.75f));
        receipt.addItem(new ImportedNonTaxableReceiptItem("box of chocolates", 11.25f));

        assertEquals(6.65f, receipt.calculateTax(), "Tax not calculated correctly");
        assertEquals(74.63f, receipt.calculateTotalPrice(), "Total price not calculated correctly");
    }

    @Test
    void toStringTest() throws Exception{
        receipt.addItem(new ImportedBasicReceiptItem("bottle of perfume", 27.99f));
        receipt.addItem(new BasicReceiptItem("bottle of perfume", 18.99f));
        receipt.addItem(new NonTaxableReceiptItem("packet of headache pills", 9.75f));
        receipt.addItem(new ImportedNonTaxableReceiptItem("box of chocolates", 11.25f));

        String expectedOutput = """
                > 1 imported bottle of perfume: 32.19
                > 1 bottle of perfume: 20.89
                > 1 packet of headache pills: 9.75
                > 1 imported box of chocolates: 11.80
                > Sales Taxes: 6.65
                > Total: 74.63""";
        assertEquals(expectedOutput, receipt.toString());
    }
}
