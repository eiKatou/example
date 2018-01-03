import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MockitoSpyTest {
    @Test
    public void Spyを使ってテストを実施する() {
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        doReturn("Mockito").when(spy).get(1);
        spy.add("Hello");
        spy.add("World");

        verify(spy).add("Hello");
        verify(spy).add("World");
        assertThat("Hello").isEqualTo(spy.get(0));
        assertThat("Mockito").isEqualTo(spy.get(1));
    }
}
