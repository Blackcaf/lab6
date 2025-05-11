package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда для вывода всех элементов коллекции
 */
public class Show extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Show(Console console, CollectionManager collectionManager) {
        super("show", "вывести все элементы коллекции в строковом представлении");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length > 0) return new ExecutionResponse(false, "Команда 'show' не принимает аргументы");
        if (collectionManager.getCollection().isEmpty()) return new ExecutionResponse(true, "Коллекция пуста");
        String result = collectionManager.getCollection().stream()
                .map(Object::toString)
                .collect(java.util.stream.Collectors.joining("\n"));
        return new ExecutionResponse(true, result);
    }
}