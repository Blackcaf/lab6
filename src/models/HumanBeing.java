package models;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * Представляет объект "человек" с изменяемым идентификатором и набором неизменяемых характеристик.
 */
public class HumanBeing implements Comparable<HumanBeing>, Serializable {
    private long id;
    private final HumanBeingData data;

    private record HumanBeingData(
            String name,
            Coordinates coordinates,
            LocalDateTime creationDate,
            boolean realHero,
            Boolean hasToothpick,
            Long impactSpeed,
            WeaponType weaponType,
            Mood mood,
            Car car
    ) implements Serializable {}

    public HumanBeing(long id, String name, Coordinates coordinates, boolean realHero, Boolean hasToothpick,
                      Long impactSpeed, WeaponType weaponType, Mood mood, Car car) {
        this.id = id;
        this.data = new HumanBeingData(name, coordinates, LocalDateTime.now(), realHero, hasToothpick,
                impactSpeed, weaponType, mood, car);
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return data.name();
    }

    public Coordinates getCoordinates() {
        return data.coordinates();
    }

    public LocalDateTime getCreationDate() {
        return data.creationDate();
    }

    public boolean isRealHero() {
        return data.realHero();
    }

    public Boolean getHasToothpick() {
        return data.hasToothpick();
    }

    public Long getImpactSpeed() {
        return data.impactSpeed();
    }

    public WeaponType getWeaponType() {
        return data.weaponType();
    }

    public Mood getMood() {
        return data.mood();
    }

    public Car getCar() {
        return data.car();
    }

    @Override
    public int compareTo(HumanBeing other) {
        return Long.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "HumanBeing{" +
                "id=" + id +
                ", name='" + data.name() + '\'' +
                ", coordinates=" + data.coordinates() +
                ", creationDate=" + data.creationDate() +
                ", realHero=" + data.realHero() +
                ", hasToothpick=" + data.hasToothpick() +
                ", impactSpeed=" + data.impactSpeed() +
                ", weaponType=" + data.weaponType() +
                ", mood=" + data.mood() +
                ", car=" + data.car() +
                '}';
    }
}