package Http;


import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

public class HttpAnswer {
    private byte[] body;
    private File file;
    private HashMap<String, String> answers = new HashMap<>();
    private String protocol, version, code;
    public byte[] getMessageToByte(){
        byte[] bytes = new byte[0];
        return bytes;
    }

    /**
     * устанавливает тело функции
     * @param body массив байтов
     */
    public void setBody(byte[] body){
        this.body = body;
    }
    public void setBody(File file){
        this.file=file;
    }

    /**
     * возвращает массив байтов
     * @return массив байтов [ ]body
     * @throws Exception если файл отсутствует или не задан
     */
    public byte[] getBody() throws Exception {
        if (body==null){
            if (file.exists()==true){
                try (FileInputStream fileInput = new FileInputStream(file)){
                    return fileInput.readAllBytes();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                throw new Exception("File Not Found");
            }
        }
        else{
            return body;
        }
        return new byte[0];
    }
    /**
     * помещает элемент в мапу ответов сервера
     * @param key заголовок
     * @param value значение
     */
    public void setAnswers(String key, String value){
        answers.put(key, value);
    }

    /**
     * возвращает мапу ответов сервера
     * @return мапа ответов сервера
     */
    public HashMap getMap(){
        return answers;
    }

    /**
     * возвращает значение заголовка по ключу
     * @param key заголовок
     * @return значение заголовка
     */
    public String getAnswer(String key){return answers.get(key);}

    /**
     * записывает протокол
     * @param pr протокол
     */
    public void setProtocol(String pr){ this.protocol=pr;}
    /**
     * возвращает название протокола
     * @return название протокола
     */
    public String getProtocol(){
        return protocol;
    }

    /**
     * записывает версию протокола
     * @param ver версия протокола
     */
    public void setVersion(String ver){ this.version=ver;}
    /**
     * возвращает версию протокола
     * @return весрия протокола
     */
    public String getVersion(){
        return version;
    }
    /**
     * записывает код ответа сервера
     * @param cod код ответа сервера
     */
    public void setCode(String cod){ this.code=cod;}
    /**
     * возвращает код ответа
     * @return код ответа
     */
    public String getCode(){
        return code;
    }


}
