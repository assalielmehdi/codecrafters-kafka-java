import handlers.RequestRouter;
import message.request.KafkaRequestMessage;

import java.net.ServerSocket;

public class Main {
  public static void main(String[] args) throws Exception {
    var serverSocket = new ServerSocket(9092);
    var clientSocket = serverSocket.accept();

    var requestBytes = clientSocket.getInputStream().readAllBytes();
    var request = KafkaRequestMessage.fromBytes(requestBytes);

    var responseMessage = new RequestRouter().route(request);
    var responseBytes = responseMessage.toBytes();
    clientSocket.getOutputStream().write(responseBytes);

    clientSocket.close();
    serverSocket.close();
  }
}
