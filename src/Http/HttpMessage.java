package Http;

public class HttpMessage extends HttpServer{
    private ServerConfig config;

    HttpMessage(){
        this.config = super.getConfig();
    }
}
