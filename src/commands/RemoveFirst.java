package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда для удаления первого элемента из коллекции
 */
public class RemoveFirst extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveFirst(Console console, CollectionManager collectionManager) {
        super("remove_first", "удалить первый элемент из коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length > 0) return new ExecutionResponse(false, "Команда 'remove_first' не принимает аргументы");
        if (collectionManager.getCollection().isEmpty()) return new ExecutionResponse(false, "Коллекция пуста");
        collectionManager.getCollection().remove(0);
        return new ExecutionResponse(true, "Первый элемент успешно удален");
    }
}
