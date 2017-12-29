import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class JUnitRuleTest {
    @Rule
    public JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Rule
    public TestName testName = new TestName();

    @Test
    public void OKになることを確認する() {
        System.out.println("Method Name is " + testName.getMethodName());
        softly.assertThat(10).isEqualTo(10);
        softly.assertThat("hoge").isEqualTo("hoge");
        softly.assertThat(true).isEqualTo(true);
    }
}
