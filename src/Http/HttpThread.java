package Http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Поток для обработки одного http-запроса.
 * Читает запрос, обрабатыает его и отправляет ответ.
 * Для заупуска небоходимо вызвать метод {@link HttpThread#run()}
 * @author Vadim Koshechkin
 */
public class HttpThread extends HttpServer implements Runnable{
    private ServerConfig config;
    private Socket socket;
    HttpThread(ServerConfig config, Socket socket){
        super(config);
        this.config = config;
        this.socket = socket;
        this.setDaemon(true);
    }

    /**
     * Обрабатывает http-запрос
     */
    public void run() {
        // для подключившегося клиента открываем потоки
        // чтения и записи
        try (
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
            )
        {
            HttpQuestion question = new HttpQuestion(input);
            HttpAnswer answer = new HttpAnswer(question);
            output.write(answer.getMessageToByte());
            output.flush();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.toString());
        }
    }

}
