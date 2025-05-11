package managers;

import models.*;

/**
 * Содержит статические методы для преобразования строк CSV в объекты {@link HumanBeing} и обратно.
 */
public class CsvParser {

    /**
     * Преобразует строку CSV в объект {@link HumanBeing}.
     *
     * @param csvLine строка CSV, содержащая данные об объекте
     * @return объект {@link HumanBeing} или выбрасывает исключение при некорректных данных
     * @throws IllegalArgumentException если данные в строке некорректны
     */
    public static HumanBeing parseHumanBeing(String csvLine) {
        String[] parts = csvLine.split(",", -1);
        if (parts.length < 10 || parts.length > 11) {
            System.err.println("Неверное количество полей: " + parts.length + " (ожидается 10 или 11) в строке: " + csvLine);
            return null;
        }

        try {
            long id = Long.parseLong(parts[0].trim());
            if (id <= 0) throw new IllegalArgumentException("id должен быть больше 0");

            String name = parts[1].trim();
            if (name.isEmpty()) throw new IllegalArgumentException("name не может быть пустым");

            String xStr = parts[2].trim().replace(",", ".");
            float x = Float.parseFloat(xStr);

            int y = Integer.parseInt(parts[3].trim());

            boolean realHero = Boolean.parseBoolean(parts[5].trim());

            String hasToothpickStr = parts[6].trim();
            if (hasToothpickStr.isEmpty()) throw new IllegalArgumentException("hasToothpick не может быть пустым");
            Boolean hasToothpick = Boolean.parseBoolean(hasToothpickStr);

            String impactSpeedStr = parts[7].trim();
            Long impactSpeed = impactSpeedStr.isEmpty() ? null : Long.parseLong(impactSpeedStr);

            String weaponTypeStr = parts[8].trim();
            WeaponType weaponType = weaponTypeStr.isEmpty() ? null : WeaponType.valueOf(weaponTypeStr.toUpperCase());

            String moodStr = parts[9].trim();
            Mood mood = moodStr.isEmpty() ? null : Mood.valueOf(moodStr.toUpperCase());

            String carName = parts.length > 10 ? parts[10].trim() : null;
            if (carName != null && carName.isEmpty()) carName = null;

            Coordinates coordinates = new Coordinates(x, y);
            Car car = new Car(carName);
            return new HumanBeing(id, name, coordinates, realHero, hasToothpick, impactSpeed, weaponType, mood, car);
        } catch (Exception e) {
            throw new IllegalArgumentException("Некорректные данные в строке '" + csvLine + "': " + e.getMessage());
        }
    }

    /**
     * Преобразует объект {@link HumanBeing} в строку CSV.
     *
     * @param human объект для преобразования
     * @return строка CSV, представляющая объект
     */
    public static String humanToCsv(HumanBeing human) {
        return String.format("%d,%s,%.1f,%d,%b,%s,%s,%s,%s,%s",
                human.getId(), human.getName(), human.getCoordinates().getX(), human.getCoordinates().getY(),
                human.isRealHero(), human.getHasToothpick(),
                human.getImpactSpeed() == null ? "" : human.getImpactSpeed(),
                human.getWeaponType() == null ? "" : human.getWeaponType(),
                human.getMood() == null ? "" : human.getMood(),
                human.getCar().getName() == null ? "" : human.getCar().getName());
    }
}