import bytes.ToBytes;
import handlers.RequestRouter;
import message.request.KafkaRequestMessage;
import types.Int32;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args) {
    try (var serverSocket = new ServerSocket(9092)) {
      while (true) {
        var clientSocket = serverSocket.accept();
        Thread.ofVirtual().start(() -> handleConnection(clientSocket));
      }
    } catch (IOException ignored) {}
  }

  private static void handleConnection(Socket clientSocket) {
    try {
      var requestLengthBytes = new byte[0];
      while ((requestLengthBytes = clientSocket.getInputStream().readNBytes(4)).length > 0) {
        var requestBytes = ToBytes.join(
          requestLengthBytes,
          clientSocket.getInputStream().readNBytes(Int32.fromBytes(requestLengthBytes).value())
        );
        var request = KafkaRequestMessage.fromBytes(requestBytes);

        var response = new RequestRouter().route(request);
        var responseBytes = response.toBytes();
        clientSocket.getOutputStream().write(responseBytes);
        clientSocket.getOutputStream().flush();
      }
    } catch (IOException ignored) {}
  }
}
