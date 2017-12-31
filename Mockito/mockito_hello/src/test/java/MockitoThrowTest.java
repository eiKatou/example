import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class MockitoThrowTest {
    @Mock
    Person mockPerson;
    @InjectMocks
    Sample sample = new Sample(mockPerson);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void 正しい返事が返って来る() {
        thrown.expect(RuntimeException.class);
        when(mockPerson.name()).thenThrow(new RuntimeException());
        sample.greet();
    }

    @Test
    public void つぶやく事ができる() {
        // test
        sample.tweet();
    }

    @Test
    public void つぶやくとRuntimeExceptionが発生する() {
        thrown.expect(RuntimeException.class);
        doThrow(new RuntimeException()).when(mockPerson).tweet(anyString());

        // test
        sample.tweet();
    }
}
