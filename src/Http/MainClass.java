package Http;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        ServerConfig config = new ServerConfig(
                8080,
                Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "resources")
        );
        HttpServer server = new HttpServer(config);
        server.start();
        Scanner in = new Scanner(System.in);
        System.out.println("enter \"stop\" to stop the server");
        while(true) {
            String num = in.nextLine();
            if("stop".equals(num)){
                try {
                    server.correctStop();
                } catch (IOException e) {
                    System.out.print(e.getMessage());
                }
                break;
            }
        }
    }
}
