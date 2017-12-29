import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class AssertJTest {
    @Test
    public void test() {
        Sample sample = new Sample();
        assertThat(sample.getMessage()).isEqualTo("Sampl!");
        assertThat("hoge")
                .as("Hogeのテストをします")
                .isEqualTo("hoge2");
    }

    @Test
    public void softTest() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(10).isEqualTo(9);
        softly.assertThat("hoge").isEqualTo("hoge");
        softly.assertThat(true).isEqualTo(false);

        softly.assertAll();
    }
}
