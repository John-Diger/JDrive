import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Objects;

public class Server implements Runnable {

    InputAgent inputAgent = new InputAgent();
    PrintAgent printAgent = new PrintAgent();

    private void uploader() {

    }

    private void downloader() {
        try (DatagramSocket datagramSocket = new DatagramSocket(new InetSocketAddress("127.0.0.1", 8282))) {
            File dir = new File("E:\\Priority\\Study\\NetworkProgramming-JDrive\\JDrive\\resources");
            File[] files = dir.listFiles();
            int filesLength = Objects.requireNonNull(files).length;
            printAgent.loadSharedFolderList(files, filesLength);
            printAgent.inputRequestFile();

            int inputFileNumber = inputAgent.convertInputToInteger(inputAgent.inputOption(), filesLength);
            File requestFile = files[inputFileNumber - 1];
            FileInputStream fileInputStream = new FileInputStream(requestFile);
            byte[] buffer = new byte[10000000];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.send(datagramPacket);

        } catch (SocketException | FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        uploader();
        downloader();
    }
}
