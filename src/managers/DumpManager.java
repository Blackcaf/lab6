package managers;

import models.*;
import java.io.*;
import java.util.Vector;

/**
 * Управляет чтением и записью коллекции объектов {@link HumanBeing} в CSV-файл.
 * Реализует интерфейс {@link FileManager}.
 */
public class DumpManager implements FileManager {
    private final String fileName;

    /**
     * Создаёт экземпляр DumpManager, используя путь к файлу из переменной окружения COLLECTION_FILE.
     *
     * @throws IllegalStateException если переменная окружения COLLECTION_FILE не установлена
     */
    public DumpManager() {
        this.fileName = System.getenv("COLLECTION_FILE");
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalStateException("Переменная окружения COLLECTION_FILE не установлена");
        }
        System.out.println("Используемый файл: " + fileName);
    }

    /**
     * Считывает коллекцию объектов {@link HumanBeing} из настроенного CSV-файла.
     *
     * @return {@link Vector}, содержащий загруженные объекты {@link HumanBeing}
     * @throws IOException если произошла ошибка ввода-вывода при чтении файла
     */
    @Override
    public Vector<HumanBeing> readCollection() throws IOException {
        Vector<HumanBeing> collection = new Vector<>();
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Файл не существует: " + fileName);
            return collection;
        }
        if (!file.canRead()) throw new IOException("Нет прав на чтение файла: " + fileName);

        System.out.println("Читаем файл: " + fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                lineNumber++;
                if (!line.isEmpty()) {
                    System.out.println("Прочитана строка " + lineNumber + ": " + line);
                    try {
                        HumanBeing human = CsvParser.parseHumanBeing(line);
                        if (human != null) {
                            collection.add(human);
                            System.out.println("Добавлен объект: " + human);
                        } else {
                            System.out.println("Строка " + lineNumber + " вернула null");
                        }
                    } catch (Exception e) {
                        System.err.println("Ошибка парсинга строки " + lineNumber + ": " + line + " (" + e.getMessage() + ")");
                    }
                } else {
                    System.out.println("Пропущена пустая строка " + lineNumber);
                }
            }
            if (lineNumber == 0) {
                System.out.println("Файл пустой или не удалось прочитать ни одной строки");
            }
        }
        return collection;
    }

    /**
     * Записывает коллекцию объектов {@link HumanBeing} в настроенный CSV-файл.
     *
     * @param collection коллекция для записи
     * @throws IOException если произошла ошибка ввода-вывода при записи в файл
     */
    @Override
    public void writeCollection(Vector<HumanBeing> collection) throws IOException {
        File file = new File(fileName);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) parentDir.mkdirs();
        if (file.exists() && !file.canWrite()) throw new IOException("Нет прав на запись: " + fileName);

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (HumanBeing human : collection) {
                String csvLine = CsvParser.humanToCsv(human);
                writer.println(csvLine);
                System.out.println("Записана строка: " + csvLine);
            }
        }
    }
}