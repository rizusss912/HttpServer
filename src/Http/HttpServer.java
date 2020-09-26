package Http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Поток для Http-сервера.
 * @author Vadim Koshechkin
 */
public class HttpServer extends Thread{

    private final ServerConfig config;
    private boolean stop = false;
    private ServerSocket serverSocket;

    HttpServer (ServerConfig config){
        this.config = config;
    }

    /**
     * Запускает сервер используя конфигурацию.
     */
    public void run(){
        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            this.serverSocket = serverSocket;
            System.out.println("Server started!");

            while (!stop) {
                // ожидаем подключения
                HttpThread request;
                try (Socket socket = serverSocket.accept()) {
                    request = new HttpThread(config, socket);
                    request.start();
                }
            }
            System.out.println("Server close!");
        }
        catch (IOException ignored) {
        }
    }

    /**
     * Останавливает сервер.
     * @throws IOException ошибка
     */
    public void correctStop() throws IOException {
        this.stop = true;
        this.serverSocket.close();
    }

    /**
     * Организует доступ к конигкрации сервера для всех классов сервера.
     * @return конфигурация
     */
    protected ServerConfig getConfig() {
        return config;
    }
}
