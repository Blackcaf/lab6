package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда для вывода уникальных значений поля impactSpeed
 */
public class PrintUniqueImpactSpeed extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public PrintUniqueImpactSpeed(Console console, CollectionManager collectionManager) {
        super("print_unique_impact_speed", "вывести уникальные значения поля impactSpeed всех элементов в коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length > 0) return new ExecutionResponse(false, "Команда 'print_unique_impact_speed' не принимает аргументы");
        String result = collectionManager.getCollection().stream()
                .map(h -> h.getImpactSpeed() == null ? "null" : h.getImpactSpeed().toString())
                .distinct()
                .collect(java.util.stream.Collectors.joining("\n"));
        return new ExecutionResponse(true, result.isEmpty() ? "Коллекция пуста" : result);
    }
}