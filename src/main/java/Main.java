import java.io.IOException;
import java.net.ServerSocket;

public class Main {
  public static void main(String[] args) {
    try (
      var serverSocket = new ServerSocket(Constants.SERVER_PORT);
      var clientSocket = serverSocket.accept();
    ) {


    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }
}
