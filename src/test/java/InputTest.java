import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InputTest extends Input {

    Input inputClass;

    @Before
    public void setUp() {
        inputClass = new Input();
    }

    @Test
    public void shouldGenerateRandomNumbersBetweenXandY() {
        int actual = inputClass.getRandomNumber(10, 5);
        assertEquals(true, actual >= 5 && actual <= 10);
    }
}