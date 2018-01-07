import org.junit.Assume;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.*;

@RunWith(Enclosed.class)
public class TheoriesTest {

    @RunWith(Theories.class)
    public static class じゃんけんを行う場合 {
        @DataPoints
        public static Fixture[] PARAMs = {
                new Fixture(Janken.Hand.GU, Janken.Hand.TYOKI, Janken.Result.WIN),
                new Fixture(Janken.Hand.PA, Janken.Hand.GU, Janken.Result.WIN)
        };

        @Theory
        public void じゃんけんで勝つ場合(Fixture fixture) {
            Janken janken = new Janken();
            assertThat(janken.judge(fixture.h1, fixture.h2)).isEqualTo(fixture.result);
        }
    }

    @RunWith(Theories.class)
    public static class あいこになる場合 {
        @DataPoints
        public static Janken.Hand[] H1_PARAMs = {
                Janken.Hand.GU, Janken.Hand.TYOKI, Janken.Hand.PA
        };

        @DataPoints
        public static Janken.Hand[] H2_PARAMs = {
                Janken.Hand.GU, Janken.Hand.TYOKI, Janken.Hand.PA
        };

        @Theory
        public void じゃんけんであいこになる場合(Janken.Hand h1, Janken.Hand h2) {
            System.out.println(String.format("h1=%s, h2=%s", h1.toString(), h2.toString()));
            Assume.assumeTrue(h1 == h2);
            Janken janken = new Janken();
            assertThat(janken.judge(h1, h2)).isEqualTo(Janken.Result.DRAW);
        }

    }

    static class Fixture {
        Janken.Hand h1;
        Janken.Hand h2;
        Janken.Result result;

        public Fixture(Janken.Hand h1, Janken.Hand h2, Janken.Result result) {
            this.h1 = h1;
            this.h2 = h2;
            this.result = result;
        }
    }
}
