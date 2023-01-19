package tyrant;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TyrantTest {
    @Test
    public void getRetrievesWhatWasPut() throws IOException {
//        Tyrant t = new Tyrant();
//        t.put("key", "value");
//        assertThat(t.get("key"), is("value"));

        Socket socket = new Socket("localhost", 1978);
        OutputStream writer = socket.getOutputStream();
        writer.write(0xC8); //OPERATION_PREFIX
        writer.write(0x10); //OPERATION_PUT
        writer.write(0);
        writer.write(0);
        writer.write(0);
        writer.write(3); //4 byte key length
        writer.write(0);
        writer.write(0);
        writer.write(0);
        writer.write(5); //4 byte value length
        writer.write(new byte[]{'k', 'e', 'y'}); //key
        writer.write(new byte[]{'v', 'a', 'l', 'u', 'e'}); //value

        InputStream reader = socket.getInputStream();
        int status = reader.read();
        assertThat(status, is(0));

    }
}
