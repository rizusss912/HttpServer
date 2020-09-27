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
    private HttpAnswer answer;
    private HttpQuestion question;

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
            question = new HttpQuestion(input);
            answer = new HttpAnswer();
            switch (question.getType()){
                case GET:
                    goToGet();
                    break;
                case POST:
                    goToPost();
                    break;
                case PUT:
                    break;
                case HEAD:
                    break;
                case PATCH:
                    break;
                case TRACE:
                    break;
                case DELETE:
                    break;
                case CONNECT:
                    break;
                case OPTIONS:
                    break;
                default:
                    throw new Exception("unknown request type");
            }
            output.write(answer.getMessageToByte());
            output.flush();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.toString());
        }
    }
    void goToGet(){
    }
    void goToPost(){

    }
}
