import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputTest {

    Input inputClass = new Input();

    @RepeatedTest(100)
    public void shouldGenerateRandomNumbersBetweenXandY() {
        int actual = inputClass.getRandomNumber(10, 5);
        assertEquals(true, actual >= 5 && actual <= 10);
    }
}