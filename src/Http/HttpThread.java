package Http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpThread extends Thread {
    private final Socket socket;
    private final ServerConfig config;
    HttpThread(ServerConfig config, Socket socket){
        this.config = config;
        this.socket = socket;
        this.setDaemon(true);
    }
    public void run() {
        // для подключившегося клиента открываем потоки
        // чтения и записи
        try		(
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                )
        {
            HttpQuestion question = new HttpQuestion(input);
            HttpAnswer answer = new HttpAnswer(question);
            output.write(answer.getMessageToByte());
            output.flush();

        } catch (IOException e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

}
