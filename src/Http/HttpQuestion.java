package Http;

public class HttpQuestion extends HttpMessage{
    private ServerConfig config;

    HttpMessage(){
        this.config = super.getConfig();
    }
}
