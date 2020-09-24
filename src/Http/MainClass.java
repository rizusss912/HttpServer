package Http;

import java.io.IOException;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        ServerConfig config = new ServerConfig();
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
