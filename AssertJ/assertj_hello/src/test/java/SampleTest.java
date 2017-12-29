import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SampleTest {
    @Test
    public void test() {
        Sample sample = new Sample();
        assertThat(sample.getMessage(), is("Sample!"));
    }
}
