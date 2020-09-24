package Http;

import java.nio.file.Path;

public class HttpServer{
    private ServerConfig config;

    public boolean setConfig (ServerConfig config){
        this.config = config;
        return false;
    }
    protected ServerConfig getConfig() {
        return config;
    }
}
