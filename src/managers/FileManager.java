package managers;

import models.HumanBeing;
import java.util.Vector;

/**
 * интерфейс для работы с файлами
 */
public interface FileManager {
    Vector<HumanBeing> readCollection() throws java.io.IOException;
    void writeCollection(Vector<HumanBeing> collection) throws java.io.IOException;
}
