package Http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Обрабатывает и хранит HTTP запрос, а так же предоставляет функционал для удобной работы с ним.
 * В конструктор передаётся поток HTTP запроса.
 */
public class HttpQuestion{
        HttpQuestion(InputStream input) throws IOException {
            BufferedReader message = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            ArrayList<String> data = new ArrayList<>();
            HashMap<String, String> datable = new HashMap<>();
            // ждем первой строки запроса
            while (!message.ready()) ;
            // считываем и печатаем все что было отправлено клиентом
            while (message.ready()) {
                String a = message.readLine();
                data.add(a);
                //System.out.println(a);
            }
            datable.put
            System.out.println(data.get(1));
        }
    /*public Array getData(Array data){
            return data;
    }*/
}
