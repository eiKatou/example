import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MockitoTest2 {
    @Test
    public void 正しい返事が返って来る() {
        Person mockPerson = mock(Person.class);
        Sample sample = new Sample(mockPerson);
        when(mockPerson.name()).thenReturn("yamada hanako");
        String greet = sample.greet();
        assertThat(greet).isEqualTo("Hello, yamada hanako!!");
    }

    @Test
    public void 声をかけると正しい返事が返って来る() {
        Person mockPerson = mock(Person.class);
        Sample sample = new Sample(mockPerson);
        when(mockPerson.say(anyString())).thenReturn("say");

        // test
        String say = sample.say();

        // verify
        assertThat(say).isEqualTo("say");
        verify(mockPerson, atLeastOnce()).say("Hey!, ");
    }
}
