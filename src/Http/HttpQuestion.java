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
 * @autor Sergey Timakov
 */
public class HttpQuestion{
        HttpQuestion(InputStream input) throws IOException {
            BufferedReader message = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            ArrayList<String> data = new ArrayList<>();
            HashMap<String, String> datable = new HashMap<>();
            while (!message.ready()) ;
            while (message.ready()) {
                String a = message.readLine();
                data.add(a);
            }

        }
}
