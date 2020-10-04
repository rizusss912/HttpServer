package Http;

import java.io.File;
import java.nio.file.Paths;

public class MainClass {

    public static void main(String[] args) {

        HttpServer server = new HttpServer(new ServerConfig(
                8080,
                Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "resources")
        ));
        server.start();
    }
}
