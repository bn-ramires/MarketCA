import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputTest {

    @RepeatedTest(100)
    public void testing() {
        Input inputClass = new Input();
        int result = inputClass.getRandomNumber(10, 5);
        assertEquals(true, result >= 5 && result <= 10);
    }
}