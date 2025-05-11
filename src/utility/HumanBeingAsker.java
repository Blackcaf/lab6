package utility;

import models.*;

import java.util.Arrays;

/**
 * Класс для ввода данных и создания объекта {@link HumanBeing}.
 */
public class HumanBeingAsker {
    private final Console console;

    public HumanBeingAsker(Console console) {
        this.console = console;
    }

    /**
     * Запрашивает данные для создания объекта {@link HumanBeing}.
     * @return новый объект HumanBeing с указанным id или новым, если id = 0
     */
    public HumanBeing askHumanBeing(long id) {
        console.println("Введите name:");
        String name = askNonEmptyString("Ошибка: name не может быть пустым. Повторите ввод:");

        Coordinates coordinates = askCoordinates();

        console.println("realHero (true/false):");
        boolean realHero = askBoolean();

        console.println("hasToothpick (true/false):");
        Boolean hasToothpick = askBoolean();

        console.println("impactSpeed (число или пустая строка для null):");
        Long impactSpeed = askLongOrNull();

        console.println("weaponType (PISTOL, SHOTGUN, KNIFE, MACHINE_GUN или пустая строка для null):");
        console.println("Доступные значения: " + Arrays.toString(WeaponType.values()));
        WeaponType weaponType = askEnumOrNull(WeaponType.class);

        console.println("mood (SADNESS, LONGING, APATHY, CALM, RAGE или пустая строка для null):");
        console.println("Доступные значения: " + Arrays.toString(Mood.values()));
        Mood mood = askEnumOrNull(Mood.class);

        console.println("car name (или пустая строка для null):");
        String carName = console.readln();
        while (carName == null) {
            console.println("Для выхода используйте команду exit.");
            console.println("car name (или пустая строка для null):");
            carName = console.readln();
        }
        Car car = new Car(carName.isEmpty() ? null : carName);

        return new HumanBeing(id, name, coordinates, realHero, hasToothpick, impactSpeed, weaponType, mood, car);
    }

    /**
     * Запрашивает данные для создания объекта {@link HumanBeing} без указания id.
     */
    public HumanBeing askHumanBeing() {
        return askHumanBeing(0); 
    }

    private Coordinates askCoordinates() {
        while (true) {
            console.println("Введите x:");
            Float x = askFloat();
            if (x == null) continue;

            console.println("Введите y:");
            Integer y = askInteger();
            if (y == null) continue;

            Coordinates coordinates = new Coordinates(x, y);
            if (coordinates.validate()) {
                return coordinates;
            }
            console.println("Повторите ввод:");
        }
    }

    private String askNonEmptyString(String errorMessage) {
        String input = console.readln();
        while (input == null || input.isEmpty()) {
            if (input == null) {
                console.println("Для выхода используйте команду exit.");
            } else {
                console.println(errorMessage);
            }
            console.println("Введите значение:");
            input = console.readln();
        }
        return input;
    }

    private boolean askBoolean() {
        while (true) {
            String input = console.readln();
            if (input == null) {
                console.println("Для выхода используйте команду exit.");
                console.println("Введите true или false:");
                continue;
            }
            input = input.toLowerCase();
            if ("true".equals(input)) return true;
            if ("false".equals(input)) return false;
            console.println("Ошибка: введите true или false. Повторите ввод:");
        }
    }

    private Long askLongOrNull() {
        while (true) {
            String input = console.readln();
            if (input == null) {
                console.println("Для выхода используйте команду exit.");
                console.println("Введите число или пустую строку для null:");
                continue;
            }
            if (input.isEmpty()) return null;
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                console.println("Ошибка: введите число или пустую строку. Повторите ввод:");
            }
        }
    }

    private <T extends Enum<T>> T askEnumOrNull(Class<T> enumClass) {
        while (true) {
            String input = console.readln();
            if (input == null) {
                console.println("Для выхода используйте команду exit.");
                console.println("Введите значение или пустую строку для null:");
                console.println("Доступные значения: " + Arrays.toString(enumClass.getEnumConstants()));
                continue;
            }
            if (input.isEmpty()) return null;
            try {
                return Enum.valueOf(enumClass, input.toUpperCase());
            } catch (IllegalArgumentException e) {
                console.println("Ошибка: введите одно из указанных значений или пустую строку. Повторите ввод:");
            }
        }
    }

    private Float askFloat() {
        while (true) {
            String input = console.readln();
            if (input == null) {
                console.println("Для выхода используйте команду exit.");
                console.println("Введите x:");
                return null;
            }
            try {
                return Float.parseFloat(input.replace(",", "."));
            } catch (NumberFormatException e) {
                console.println("Ошибка: x должен быть числом с плавающей точкой. Повторите ввод:");
            }
        }
    }

    private Integer askInteger() {
        while (true) {
            String input = console.readln();
            if (input == null) {
                console.println("Для выхода используйте команду exit.");
                console.println("Введите y:");
                return null;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                console.println("Ошибка: y должен быть целым числом. Повторите ввод:");
            }
        }
    }
}
