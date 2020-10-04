package Http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class HttpAnswer {
    private byte[] body = null;
    private File file = null;
    private HashMap<String, String> headers = new HashMap<>();
    private String protocol = "HTTP";
    private String version = "1.1";
    private int code = 501;
    private String message = "Not Implemented";

    /**
     * Отправляет сообщение
     * @param out поток для отправки сообщения
     * @throws IOException проблемы с потоком
     */
    public void pushMessage(OutputStream out) throws IOException {
        out.write((this.getProtocol() + "/" + this.getProtocolVersion() + " " + this.getCode() + " " + this.getMessage() + "\n").getBytes());
        for (Map.Entry<String, String> entry : headers.entrySet()){
            out.write((entry.getKey() + ": " + entry.getValue() + "\n").getBytes());
        }
        if ( !(body == null && file == null) ){
            out.write("\n".getBytes());
            if(file != null){
                Files.copy(file.toPath(), out);
            } else{
                out.write(body);
            }
        }
        out.flush();
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
        this.file = file;
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
    public void setHeader(String key, String value){
        headers.put(key, value);
    }

    /**
     * Возвращает HashMap ответов сервера
     * @return HashMap ответов сервера
     */
    public HashMap getHeaders(){
        return headers;
    }

    /**
     * Возвращает значение заголовка по ключу
     * @param key заголовок
     * @return значение заголовка
     */
    public String getHeaders(String key){return headers.get(key);}

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
    public void setProtocolVersion(String ver){ this.version = ver;}
    /**
     * возвращает версию протокола
     * @return весрия протокола
     */
    public String getProtocolVersion(){
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

    /**
     * Устанавливает сообщение ответа
     * @param message сообщение
     */
    public void setMessage(String message){
        this.message = message;
    }

    /**
     * Возвращает сообение ответа
     * @return сообение
     */
    public String getMessage(){
        return message;
    }
}
