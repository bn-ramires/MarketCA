import org.junit.Before;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputTest extends Input {

    Input inputClass;

    @Before
    public void setUp() {
    }

    @RepeatedTest(100)
    public void shouldGenerateRandomNumbersBetweenXandY() {
        inputClass = new Input();
        int actual = inputClass.getRandomNumber(10, 5);
        assertEquals(true, actual >= 5 && actual <= 10);
    }
}