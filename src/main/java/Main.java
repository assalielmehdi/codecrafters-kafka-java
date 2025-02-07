import java.io.ByteArrayOutputStream;
import java.net.ServerSocket;

public class Main {
  public static void main(String[] args) throws Exception {
    var serverSocket = new ServerSocket(Constants.SERVER_PORT);
    var clientSocket = serverSocket.accept();

    var responseBuffer = new ByteArrayOutputStream();

    var messageSize = 0;
    var correlationId = 7;

    ByteUtils.writeInt(responseBuffer, messageSize);
    ByteUtils.writeInt(responseBuffer, correlationId);

    clientSocket.getOutputStream().write(responseBuffer.toByteArray());

    clientSocket.close();
    serverSocket.close();
  }
}
