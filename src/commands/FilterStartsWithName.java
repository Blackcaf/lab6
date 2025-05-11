package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда для вывода элементов, имя которых начинается с заданной подстроки
 */
public class FilterStartsWithName extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public FilterStartsWithName(Console console, CollectionManager collectionManager) {
        super("filter_starts_with_name", "вывести элементы, значение поля name которых начинается с заданной подстроки");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length != 1) return new ExecutionResponse(false, "Команда требует один аргумент: подстрока имени");
        String prefix = arguments[0];
        String result = collectionManager.getCollection().stream()
                .filter(h -> h.getName().startsWith(prefix))
                .map(Object::toString)
                .collect(java.util.stream.Collectors.joining("\n"));
        return new ExecutionResponse(true, result.isEmpty() ? "Нет элементов с таким именем" : result);
    }
}