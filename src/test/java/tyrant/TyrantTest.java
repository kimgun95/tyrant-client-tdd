package tyrant;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TyrantTest {

    private TyrantMap tyrantMap;
    @Before
    public void setUp() throws IOException {
        tyrantMap = new TyrantMap();
        tyrantMap.open();
    }
    @After
    public void tearDown() throws IOException {
        tyrantMap.close();
    }
    @Test
    public void getRetrievesWhatWasPut() throws IOException {
//        Tyrant t = new Tyrant();
//        t.put("key", "value");
//        assertThat(t.get("key"), is("value"));

        tyrantMap.put("key", "value");
        assertThat(tyrantMap.get("key"), is("value".getBytes()));
    }
}
