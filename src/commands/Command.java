package commands;

import utility.ExecutionResponse;

/**
 * Абстрактный базовый класс для всех команд
 */
public abstract class Command {
    private final String name;
    private final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Выполняет команду с переданными аргументами
     * @param arguments аргументы команды
     * @return результат выполнения команды
     */
    public abstract ExecutionResponse apply(String[] arguments);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}