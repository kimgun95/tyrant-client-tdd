package tyrant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

class TyrantMap {

    public static final int OPERATION_PREFIX = 0xC8;
    public static final int OPERATION_PUT = 0x10;
    private static final int OPERATION_GET = 0x30;
    private Socket socket;
    private DataOutputStream writer;
    private DataInputStream reader;

    public void put(String key, String value) throws IOException {

        writeHeader(OPERATION_PUT);
        writeKeyValue(key, value);
        int status = reader.read();
        assertThat(status, is(0));
    }

    private void writeKeyValue(String key, String value) throws IOException {
        writer.writeInt(key.length()); //4 byte
        writer.writeInt(value.length()); //4 byte
        writer.write(key.getBytes()); //key
        writer.write(value.getBytes()); //value
    }

    private void writeHeader(int operationPut) throws IOException {
        writer.write(OPERATION_PREFIX);
        writer.write(operationPut);
    }

    public void open() throws IOException {
        socket = new Socket("localhost", 1978);
        writer = new DataOutputStream(socket.getOutputStream());
        reader = new DataInputStream(socket.getInputStream());
    }

    public void close() throws IOException {
        socket.close();
    }

    public byte[] get(String key) throws IOException {
        writeHeader(OPERATION_GET);
        writer.writeInt(key.length()); //4 byte
        writer.write(key.getBytes()); //key
        int status = reader.read();
        assertThat(status, is(0));
        int length = reader.readInt();
        byte[] results = new byte[length];
        reader.read(results);
        return results;
    }
}
