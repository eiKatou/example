import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class ThrownTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void 例外が発生せずにNGになることを確認する() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("sample");

        new SampleClass().run2();
    }

    @Test
    public void IllegalArgumentException例外が発生しOKになることを確認する() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("sample");

        new SampleClass().run();
    }

    static class SampleClass {
        public void run() {
            throw new IllegalArgumentException("sample exception");
        }

        public void run2() {
        }
    }

}
