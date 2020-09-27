package Http;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

public class HttpAnswer {
    private byte[] body;
    private File file;
    private HashMap<String, String> answers = new HashMap<>();
    private String protocol;
    private String version;
    private int code;
    public byte[] getMessageToByte(){
        byte[] bytes = new byte[0];
        return bytes;
    }

    /**
     * Устанавливает тело запроса в виде массива байтов
     */
    public void setBody(byte[] body){
        this.body = body;
    }
    /**
     * Устанавливает тело запроса в виде файла
     */
    public void setBody(File file){
        this.file=file;
    }
    /**
     * возвращает массив байтов
     * @return массив байтов
     * @throws Exception если файл/массив байтов отсутствует или не задан функцией {@link HttpAnswer#setBody(File)} {@link HttpAnswer#setBody(byte[])}
     */
    public byte[] getBody() throws Exception {
        if (file.exists() == true){
            FileInputStream fileInput = new FileInputStream(file);
            body = fileInput.readAllBytes();
            fileInput.close();
        } else if (body == null) {
            throw new Exception("on found body");
        }
        return body;
    }
    /**
     * помещает элемент в HashMap ответов сервера
     * @param key заголовок
     * @param value значение
     */
    public void setAnswers(String key, String value){
        answers.put(key, value);
    }

    /**
     * Возвращает HashMap ответов сервера
     * @return HashMap ответов сервера
     */
    public HashMap getMap(){
        return answers;
    }

    /**
     * Возвращает значение заголовка по ключу
     * @param key заголовок
     * @return значение заголовка
     */
    public String getAnswer(String key){return answers.get(key);}

    /**
     * Устанавливает тип протокол
     * @param protocol протокол
     */
    public void setProtocol(String protocol){ this.protocol=protocol;}
    /**
     * Возвращает тип протокола
     * @return название протокола
     */
    public String getProtocol(){
        return protocol;
    }

    /**
     * Устанавливает версию протокола
     * @param ver версия протокола
     */
    public void setVersion(String ver){ this.version = ver;}
    /**
     * возвращает версию протокола
     * @return весрия протокола
     */
    public String getVersion(){
        return version;
    }
    /**
     * Устанавливает код ответа сервера
     * @param cod код ответа сервера
     * @throws Exception переданное значение меньбше 100 или больше 599
     */
    public void setCode(int cod) throws Exception {
        if (cod >= 100 && cod < 600){
            this.code = cod;
        } else throw new Exception("code outside the range");
    }
    /**
     * Возвращает код ответа
     * @return код ответа
     */
    public int getCode(){
        return code;
    }
}
