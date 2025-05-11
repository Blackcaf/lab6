package models;
import java.io.Serializable;
/**
 * Класс автомобиля
 */
public class Car implements Serializable {
    private String name; // Поле может быть null

    /**
     * Конструктор класса Car
     * @param name имя автомобиля
     */
    public Car(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    /**
     * Возвращает строковое представление объекта
     * @return строка с именем автомобиля
     */
    @Override
    public String toString() {
        return String.format("Car{name='%s'}", name == null ? "null" : name);
    }
}