package Http;

import java.nio.file.Path;

/** Хранит всю необходимую информацию для сервера.
 * @author Vadim Koshechkin
 */
public class ServerConfig {
    private final int port;
    private final Path resourcesPath;

    ServerConfig(int port, Path resourcesPath){
        this.port = port;
        this.resourcesPath = resourcesPath;
    }

    /** Возаращает порт сервера.
     * @return порт
     */
    public int getPort() {
        return port;
    }

    /** Возвращает путь к папке с рескурсами сервера
     * @return путь
     */
    public Path getResourcesPath(){
        return resourcesPath;
    }
}
