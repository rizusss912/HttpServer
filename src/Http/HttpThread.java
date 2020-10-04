package Http;

import java.io.File;
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
            answer.setProtocol(question.getProtocol());
            answer.setProtocolVersion(question.getProtocolVersion());
            searchFile();
            answer.pushMessage(output);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.toString());
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        /**
         * Обрабатывает запрос на файл сервера
         */
    private void searchFile() throws Exception {
        File searchFile = new File(config.getResourcesPath().toString() + question.getUrl().replace("/", "\\"));
        if (searchFile.getName().indexOf(".") == -1){
            searchFile = defaultFile(searchFile);
        }
        if (searchFile != null && searchFile.exists()){
            answer.setCode(200);
            answer.setMessage("OK");
            answer.setBody(searchFile);
        } else {
            answer.setCode(404);
            answer.setMessage("Not Found");
        }
    }

        /**
         * Вазвращает дефолтный файл из директории или null, если такой файл не найден
         * @param dir директория
         * @return файл в директории
         */
        private File defaultFile(File dir) {
            File searchFile = null;
            if (question.getUrl().equals("/")) {
                for (File file : dir.listFiles()) {
                    if (file.getName().equals("index.html")) {
                        searchFile = file;
                    }
                }
                if (searchFile == null) {
                    for (File file : dir.listFiles()) {
                        if (file.getName().indexOf(".html") != -1) {
                            searchFile = file;
                        }
                    }
                }
            }
            return searchFile;
        }
}
