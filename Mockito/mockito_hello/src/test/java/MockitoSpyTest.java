import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MockitoSpyTest {
    // 引数なしコンストラクタがないので、newしないとエラーになる
    @Spy
    Person mockPerson = new Person("", "", 0);
    @InjectMocks
    Sample sample = new Sample(mockPerson);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void 正しい返事が返って来る() {
        doReturn("spy taro").when(mockPerson).name();
        String greet = sample.greet();
        assertThat(greet).isEqualTo("Hello, spy taro!!");
    }
}
