import bytes.BytesWriterVisitor;
import kafka.KafkaParser;
import types.Int32;

import java.net.ServerSocket;

public class Main {
  public static void main(String[] args) throws Exception {
    var serverSocket = new ServerSocket(Constants.SERVER_PORT);
    var clientSocket = serverSocket.accept();

    var kafkaParser = new KafkaParser(clientSocket.getInputStream());
    var message = kafkaParser.parseMessage();

    var bytesWriterVisitor = new BytesWriterVisitor();
    clientSocket.getOutputStream().write(bytesWriterVisitor.visitInt32(new Int32(0)));
    clientSocket.getOutputStream().write(bytesWriterVisitor.visitInt32(message.header().correlationId()));

    clientSocket.close();
    serverSocket.close();
  }
}
