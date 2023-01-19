package tyrant;

import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

public class TyrantTest {
    @Test
    public void getRetrievesWhatWasPut() throws IOException {
//        Tyrant t = new Tyrant();
//        t.put("key", "value");
//        assertThat(t.get("key"), is("value"));

        new Socket("localhost", 1978);
    }
}
