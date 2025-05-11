package managers;

import models.HumanBeing;
import java.util.Vector;

/**
 * Генерирует уникальные идентификаторы для объектов {@link HumanBeing} на основе существующих идентификаторов в коллекции.
 */
public class IdGenerator {
    private long nextId;
    private final Vector<HumanBeing> collection;

    /**
     * Создаёт генератор идентификаторов для указанной коллекции.
     * Устанавливает следующий идентификатор как значение, превышающее максимальный существующий.
     *
     * @param collection коллекция объектов {@link HumanBeing}, на основе которой генерируются идентификаторы
     */
    public IdGenerator(Vector<HumanBeing> collection) {
        this.collection = collection;
        if (collection.isEmpty()) {
            nextId = 1;
        } else {
            nextId = collection.stream()
                    .mapToLong(HumanBeing::getId)
                    .max()
                    .getAsLong() + 1;
        }
        System.out.println("Инициализирован IdGenerator с nextId = " + nextId);
    }

    /**
     * Генерирует уникальный идентификатор для нового объекта {@link HumanBeing}.
     * Гарантирует, что идентификатор не совпадает с уже существующими в коллекции.
     *
     * @return уникальный идентификатор
     */
    public long generateId() {
        while (collection.stream().anyMatch(h -> h.getId() == nextId)) {
            nextId++;
        }
        long id = nextId;
        nextId++;
        System.out.println("Сгенерирован новый id: " + id);
        return id;
    }
}