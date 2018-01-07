import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Verifier;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

public class VerifierExternalObjectExampleTest {
    ErrorLog log = new ErrorLog();

    @Rule
    public ErrorLogVerifier errorLogVerifier = new ErrorLogVerifier(log);

    @Test
    public void 正常系_テストケース() {
        System.out.println("OK");
    }

    @Test
    public void 異常系_エラーが出力される場合() {
        // コメントインするとエラーになる
//        log.add("Error");
    }
}

class ErrorLogVerifier extends Verifier{
    final ErrorLog log;

    public ErrorLogVerifier(ErrorLog log) {
        this.log = log;
    }

    @Override
    protected void verify() throws Throwable {
        super.verify();
        assertThat(log.size()).isEqualTo(0);
    }
}

class ErrorLog extends ArrayList<String> {
}