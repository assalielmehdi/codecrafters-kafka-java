import handlers.Router;

import java.net.ServerSocket;

public class Main {
  public static void main(String[] args) throws Exception {
    var serverSocket = new ServerSocket(Constants.SERVER_PORT);
    var clientSocket = serverSocket.accept();

    var kafkaParser = new KafkaParser(clientSocket.getInputStream());
    var message = kafkaParser.parseMessage();

    var responseMessage = new Router().route(message);

    var kafkaWriter = new KafkaWriter(clientSocket.getOutputStream());
    kafkaWriter.writeMessage(responseMessage);

    clientSocket.close();
    serverSocket.close();
  }
}
