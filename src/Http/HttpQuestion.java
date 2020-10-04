package Http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


    /**
    * Обрабатывает и хранит HTTP запрос, а так же предоставляет функционал для удобной работы с ним.
    * В конструктор передаётся поток HTTP запроса.
    * @author KiT
    */
public class HttpQuestion{

    private HashMap<String, String> headers = new HashMap<>();
    private final RequestTypes type;
    private final String url;
    private final String protocol;
    private final String protocolVersion;
    private final byte[] body;

        HttpQuestion(InputStream input) throws Exception {
        BufferedReader message = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        // ждем первой строки запроса
        while (!message.ready()) ;
        // считываем и печатаем все что было отправлено клиентом
        //Считываем главную строку
        String[] mainQuestion = message.readLine().split(" ");
        type = RequestTypes.valueOf(mainQuestion[0]);
        url = mainQuestion[1];
        protocol = mainQuestion[2].split("/")[0];
        protocolVersion = mainQuestion[2].split("/")[1];
        String a = null;
        // читаем все заголовки
        while (message.ready() || "\n".equals(a)) {
            a = message.readLine();
            String[] part = a.split(": ");
            if( part.length > 1){
                headers.put(part[0], part[1]);
            }
        }
        if (a.equals("\n")) {
            body = input.readAllBytes();
        } else {
            body = null;
        }
    }

    /**
     * возвращает главную строку запроса
     * @return главная строка
     */
    public String getMain(){
        return type + " " + url + " " + protocol + "/" + protocolVersion;
    }
    /**
     * возвращает тип запроса
     * @return тип запроса
     */
    public RequestTypes getType(){
        return type;
    }

        /**
         * Возарашает url запроса
         * @return url
         */
    public String getUrl(){
        return url;
    }
    /**
     * возвращает название протокола
     * @return название протокола
     */
    public String getProtocol(){
        return protocol;
    }
    /**
     * возвращает версию протокола
     * @return весрия протокола
     */
    public String getProtocolVersion(){
        return protocolVersion;
    }
    /**
     * возвращает HashMap запросов
     * @return HashMap запросов
     */
    public HashMap getHeaders(){
        return headers;
    }

    /**
     * Возвращает значсение заголовка по имени
     * @param header имя заголовка
     * @return значение заголовка
     */
    public String getHeader(String header){
    return headers.get(header);
    }

        /**
         *  Возвразает тело запроса или null, если тела нет
         * @return тело запроса
         */
    public byte[] getBody() {
        return body;
    }
}