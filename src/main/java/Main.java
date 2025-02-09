import bytes.ToBytes;
import handlers.RequestRouter;
import message.request.KafkaRequestMessage;
import types.Int32;

import java.net.ServerSocket;

public class Main {
  public static void main(String[] args) throws Exception {
    var serverSocket = new ServerSocket(9092);
    var clientSocket = serverSocket.accept();

    var requestLengthBytes = clientSocket.getInputStream().readNBytes(4);
    var requestBytes = ToBytes.join(
      requestLengthBytes,
      clientSocket.getInputStream().readNBytes(Int32.fromBytes(requestLengthBytes).value())
    );
    var request = KafkaRequestMessage.fromBytes(requestBytes);

    var response = new RequestRouter().route(request);
    var responseBytes = response.toBytes();
    clientSocket.getOutputStream().write(responseBytes);

    clientSocket.close();
    serverSocket.close();
  }
}
