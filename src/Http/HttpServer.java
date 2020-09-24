package Http;

import jdk.jshell.spi.ExecutionControlProvider;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;

public class HttpServer extends Thread{
    private final ServerConfig config;
    private int Server = 8080;
    private boolean stop = false;
    private ServerSocket serverSocket;
    HttpServer (ServerConfig config){
        this.config = config;
    }
    public void run(){
        try (ServerSocket serverSocket = new ServerSocket(Server)) {
            this.serverSocket = serverSocket;
            System.out.println("Server started!");

            while (!stop) {
                // ожидаем подключения

                    Socket socket = serverSocket.accept();


                HttpThread request = new HttpThread(config, socket);
                request.start();
            }
            System.out.println("Server close!");
        }
        catch (IOException e) {
        }
    }

    public void correctStop() throws IOException {
        this.stop = true;
        this.serverSocket.close();
    }
}
