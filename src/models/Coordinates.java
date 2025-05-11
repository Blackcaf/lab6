package models;

import utility.Validatable;
import java.io.Serializable;

/**
 * Представляет координаты объекта с полями x и y.
 */
public class Coordinates implements Validatable, Serializable {
    private float x; // Значение поля должно быть больше -89
    private int y;  // Значение поля должно быть больше -862

    /**
     * Создаёт новый объект Coordinates с указанными значениями x и y.
     * @param x координата x, должна быть больше -89
     * @param y координата y, должна быть больше -862
     */
    public Coordinates(float x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getX() { return x; }
    public int getY() { return y; }

    /**
     * Проверяет валидность координат: x > -89, y > -862.
     * @return true, если координаты валидны, иначе false
     */
    @Override
    public boolean validate() {
        if (x <= -89) {
            System.out.println("Ошибка: x должен быть больше -89. Текущее значение: " + x);
            return false;
        }
        if (y <= -862) {
            System.out.println("Ошибка: y должен быть больше -862. Текущее значение: " + y);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coordinates{x=" + x + ", y=" + y + "}";
    }
}