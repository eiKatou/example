import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.*;

@RunWith(Enclosed.class)
public class EnclosedTest {
    public static class メッセージを取得する {
        Sample sample;

        @Before
        public void setUp() {
            sample = new Sample();
        }

        @Test
        public void 正しいメッセージが取得できる() {
            assertThat(sample.getMessage()).isEqualTo("Sample!");
        }
    }

    public static class メッセージを取得する2 {
        Sample sample;

        @Before
        public void setUp() {
            sample = new Sample();
        }

        @Test
        public void 正しいメッセージが取得できる2() {
            assertThat(sample.getMessage()).isEqualTo("Sample!");
        }
    }
}
