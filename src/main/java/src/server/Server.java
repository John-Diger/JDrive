package src.server;

import src.dao.BucketDao;
import src.dto.ResponseDto.FindAllForm;
import src.ioagent.InputAgent;
import src.ioagent.OutputAgent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.List;
import java.util.Objects;

public class Server {

    private final InputAgent inputAgent;
    private final OutputAgent outputAgent;
    private final BucketDao bucketDao;

    public Server(InputAgent inputAgent, OutputAgent outputAgent, BucketDao bucketDao) {
        this.inputAgent = inputAgent;
        this.outputAgent = outputAgent;
        this.bucketDao = bucketDao;
    }

    public void upload() {

    }

    public void download() {
        try (DatagramSocket datagramSocket = new DatagramSocket(new InetSocketAddress("127.0.0.1", 8080))) {
            List<FindAllForm> loadedAll = bucketDao.findAll();
            File dir = new File("E:\\Priority\\Study\\NetworkProgramming-JDrive\\JDrive\\resources\\shared");
            for (FindAllForm findAllForm : loadedAll) {
                File test = (File) findAllForm.getLoadedFile();
                System.out.println("test = " + test);
            }

            File[] files = dir.listFiles();
            int filesLength = Objects.requireNonNull(files).length;
            outputAgent.loadSharedFolderList(files, filesLength);
            outputAgent.inputRequestFile();

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
}
