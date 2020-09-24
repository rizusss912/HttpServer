package Http;

import java.io.InputStream;

public class HttpAnswer {
    private HttpQuestion question;
    public HttpAnswer(HttpQuestion question) {
        this.question = question;
    }
    public byte[] getMessageToByte(){
        byte[] bytes = new byte[0];
        return bytes;
    }
}
