import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

public class MockitoTest {
    @Mock
    Person mockPerson;
    @InjectMocks
    Sample sample = new Sample(mockPerson);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void 正しい返事が返って来る() {
        when(mockPerson.name()).thenReturn("yamada hanako");
        String greet = sample.greet();
        assertThat(greet).isEqualTo("Hello, yamada hanako!!");
    }
}
