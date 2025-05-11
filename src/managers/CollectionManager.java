package managers;

import models.HumanBeing;

import java.time.LocalDateTime;
import java.util.Vector;

/**
 * Управляет коллекцией объектов {@link HumanBeing}, предоставляет методы для загрузки, сохранения и изменения коллекции.
 */
public class CollectionManager {
    private final Vector<HumanBeing> collection;
    private final LocalDateTime initializationDate;
    private final FileManager fileManager;
    private final IdGenerator idGenerator;
    private HumanBeing tempHuman = null;

    public CollectionManager(FileManager fileManager) {
        this.collection = new Vector<>();
        this.initializationDate = LocalDateTime.now();
        this.fileManager = fileManager;
        this.idGenerator = new IdGenerator(collection);
        loadCollection();
    }

    private void loadCollection() {
        try {
            Vector<HumanBeing> loaded = fileManager.readCollection();
            collection.addAll(loaded);
            System.out.println("Загружено " + loaded.size() + " элементов из файла:");
            for (HumanBeing human : collection) {
                System.out.println(human);
            }
        } catch (Exception e) {
            System.err.println("Ошибка при загрузке коллекции: " + e.getMessage());
        }
    }

    public void saveCollection() {
        try {
            fileManager.writeCollection(collection);
            System.out.println("Коллекция сохранена в файл. Всего элементов: " + collection.size());
        } catch (Exception e) {
            System.err.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    public long generateId() {
        return idGenerator.generateId();
    }

    public void add(HumanBeing human) {
        long newId = generateId();
        human.setId(newId);
        collection.add(human);
        System.out.println("Добавлен элемент с id=" + newId + ": " + human);
    }

    public void addWithId(HumanBeing human) {
        collection.add(human);
        System.out.println("Добавлен элемент с фиксированным id=" + human.getId() + ": " + human);
    }

    public void removeById(long id) {
        collection.removeIf(h -> h.getId() == id);
    }

    public void clear() {
        collection.clear();
    }

    public HumanBeing getById(long id) {
        return collection.stream().filter(h -> h.getId() == id).findFirst().orElse(null);
    }

    public Vector<HumanBeing> getCollection() {
        return collection;
    }

    public String getInfo() {
        return String.format("Тип: %s, Дата инициализации: %s, Количество элементов: %d",
                collection.getClass().getSimpleName(), initializationDate, collection.size());
    }

    public HumanBeing getTempHuman() {
        return tempHuman;
    }

    public void setTempHuman(HumanBeing tempHuman) {
        this.tempHuman = tempHuman;
    }
}
