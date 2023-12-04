package put.io.testing.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FailureOrErrorTest {

    @Test
    public void test1() {
        assertTrue(0 == 1);
    }

    @Test
    public void test2() throws Exception {
        //throw new Exception("Exception message");
        assertTrue(new Calculator().addPositiveNumbers(-1, -1) == 1);
    }

    @Test
    public void test3() {
        try {
            //throw new Exception("Exception message");
            //assertTrue(new Calculator().addPositiveNumbers(-1, -1) == 1);
            assertTrue(false);
        } catch (Throwable throwable) {
            System.out.println(throwable.getStackTrace());
            throwable.printStackTrace();
        }
    }
}