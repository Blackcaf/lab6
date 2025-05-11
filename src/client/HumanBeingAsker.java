package client;

import models.*;
import utility.Console;

import java.util.Arrays;
import java.util.Scanner;

public class HumanBeingAsker {
    private final Console console;

    public HumanBeingAsker(Console console) {
        this.console = console;
    }

    public HumanBeing askHumanBeing() {
        console.println("Введите name:");
        String name = askNonEmptyString();

        Coordinates coordinates = askCoordinates();

        console.println("realHero (true/false):");
        boolean realHero = askBoolean();

        console.println("hasToothpick (true/false):");
        Boolean hasToothpick = askBoolean();

        console.println("impactSpeed (число или пусто для null):");
        Long impactSpeed = askLongOrNull();

        console.println("weaponType (PISTOL, SHOTGUN, KNIFE, MACHINE_GUN):");
        WeaponType weaponType = askEnumOrNull(WeaponType.class);

        console.println("mood (SADNESS, LONGING, APATHY, CALM, RAGE):");
        Mood mood = askEnumOrNull(Mood.class);

        console.println("car name (может быть пустым):");
        String carName = console.readln();
        Car car = new Car(carName.isEmpty() ? null : carName);

        return new HumanBeing(0, name, coordinates, realHero, hasToothpick, impactSpeed, weaponType, mood, car);
    }

    private Coordinates askCoordinates() {
        console.println("Введите координату x (float > -89):");
        float x = Float.parseFloat(console.readln());

        console.println("Введите координату y (int > -862):");
        int y = Integer.parseInt(console.readln());

        return new Coordinates(x, y);
    }

    private String askNonEmptyString() {
        String str = console.readln();
        while (str == null || str.trim().isEmpty()) {
            console.println("Поле не может быть пустым, введите снова:");
            str = console.readln();
        }
        return str;
    }

    private boolean askBoolean() {
        while (true) {
            String input = console.readln();
            if ("true".equalsIgnoreCase(input)) return true;
            if ("false".equalsIgnoreCase(input)) return false;
            console.println("Введите true или false:");
        }
    }

    private Long askLongOrNull() {
        String input = console.readln();
        if (input.isEmpty()) return null;
        return Long.parseLong(input);
    }

    private <T extends Enum<T>> T askEnumOrNull(Class<T> enumType) {
        String input = console.readln();
        if (input.isEmpty()) return null;
        return Enum.valueOf(enumType, input.toUpperCase());
    }
}
