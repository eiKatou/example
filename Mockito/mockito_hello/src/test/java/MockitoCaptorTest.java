import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MockitoCaptorTest {
    @Mock
    Person mockPerson;
    @InjectMocks
    Sample sample = new Sample(mockPerson);
    @Captor
    ArgumentCaptor<String> captor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void 返事が返って来る() {
        when(mockPerson.say(anyString())).thenReturn("hi");

        // test
        String greet = sample.say();

        // verify
        assertThat(greet).isEqualTo("hi");
        verify(mockPerson).say(captor.capture());
        assertThat(captor.getValue()).isEqualTo("Hei!, ");
    }
}
