package tyrant;

import org.junit.Test;

import java.io.DataOutputStream;
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

        new TyrantMap().put();

    }

    private class TyrantMap {

        public static final int OPERATION_PREFIX = 0xC8;
        public static final int OPERATION_PUT = 0x10;
        private Socket socket;
        private DataOutputStream writer;
        private InputStream reader;

        public void put() throws IOException {
            String key = "key";
            String value = "value";
            socket = new Socket("localhost", 1978);
            writer = new DataOutputStream(socket.getOutputStream());
            writer.write(OPERATION_PREFIX);
            writer.write(OPERATION_PUT);
            writer.writeInt(key.length()); //4 byte
            writer.writeInt(value.length()); //4 byte
            writer.write(key.getBytes()); //key
            writer.write(value.getBytes()); //value

            reader = socket.getInputStream();
            int status = reader.read();
            assertThat(status, is(0));
        }
    }
}
