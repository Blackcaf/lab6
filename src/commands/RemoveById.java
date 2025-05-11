package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда для удаления элемента по его id
 */
public class RemoveById extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveById(Console console, CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length != 1) return new ExecutionResponse(false, "Команда требует один аргумент: id");
        try {
            long id = Long.parseLong(arguments[0]);
            if (collectionManager.getById(id) == null) return new ExecutionResponse(false, "Элемент с id " + id + " не найден");
            collectionManager.removeById(id);
            return new ExecutionResponse(true, "Элемент с id " + id + " успешно удален");
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "id должен быть числом");
        }
    }
}
