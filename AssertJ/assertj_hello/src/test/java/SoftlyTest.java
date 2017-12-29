import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

public class SoftlyTest {
    @Rule
    public JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test
    public void NGになることを確認する() {
        softly.assertThat(10).isEqualTo(9);
        softly.assertThat("hoge").isEqualTo("hoge");
        softly.assertThat(true).isEqualTo(false);
    }

    @Test
    public void OKになることを確認する() {
        softly.assertThat(10).isEqualTo(10);
        softly.assertThat("hoge").isEqualTo("hoge");
        softly.assertThat(true).isEqualTo(true);
    }
}
