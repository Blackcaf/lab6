package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда для подсчета элементов с impactSpeed меньше заданного
 */
public class CountLessThanImpactSpeed extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public CountLessThanImpactSpeed(Console console, CollectionManager collectionManager) {
        super("count_less_than_impact_speed", "вывести количество элементов, значение поля impactSpeed которых меньше заданного");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length != 1) return new ExecutionResponse(false, "Команда требует один аргумент: impactSpeed");
        try {
            long impactSpeed = Long.parseLong(arguments[0]);
            long count = collectionManager.getCollection().stream()
                    .filter(h -> h.getImpactSpeed() != null && h.getImpactSpeed() < impactSpeed)
                    .count();
            return new ExecutionResponse(true, "Количество элементов: " + count);
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "impactSpeed должен быть числом");
        }
    }
}
