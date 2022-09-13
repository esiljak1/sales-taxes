import com.esiljak.helpers.NumberHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberHelperTests {
    @Test
    void taxRoundUpTest(){
        assertEquals(0f, NumberHelper.roundUpTax(0));
        assertEquals(3.2f, NumberHelper.roundUpTax(3.21f));
        assertEquals(0.3f, NumberHelper.roundUpTax(0.275f));
        assertEquals(-0.75f, NumberHelper.roundUpTax(-0.77f));
    }
}
