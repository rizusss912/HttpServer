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

    private HashMap<String, String> questions = new HashMap<>();
    private String mainQuestion;
    HttpQuestion(InputStream input) throws IOException {
        BufferedReader message = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        // ждем первой строки запроса
        while (!message.ready()) ;
        // считываем и печатаем все что было отправлено клиентом
        //Считываем главную строку
        mainQuestion=message.readLine();
        // читаем все заголовки
        while (message.ready()) {
            String a = message.readLine();
            String[] part = a.split(":");
            if(part.length>1){
                questions.put(part[0], part[1]);

            }
        }
    }

    /**
     * возвращает главную строку
     * @return главная строка
     */
    public String getMain(){
        return mainQuestion;
    }
    /**
     * возвращает тип запроса
     * @return тип запроса
     */
    public RequestTypes getType(){
        return RequestTypes.valueOf(mainQuestion.split("/", 2)[0].replace(" ",""));
    }
    /**
     * возвращает название протокола
     * @return название протокола
     */
    public String getProtocol(){
        return mainQuestion.split("/", 3)[1];
    }
    /**
     * возвращает версию протокола
     * @return весрия протокола
     */
    public String getVersion(){
        return mainQuestion.split("/", 3)[2];
    }
    /**
     * возвращает HashMap запросов
     * @return HashMap запросов
     */
    public HashMap getMap(){
        return questions;
    }

    /**
     * Возвращает значсение заголовка по имени
     * @param header имя заголовка
     * @return значение заголовка
     */
    public String getHeader(String header){
    return questions.get(header);
    }
}