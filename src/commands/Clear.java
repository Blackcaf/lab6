package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда для очистки коллекции
 */
public class Clear extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Clear(Console console, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length > 0) return new ExecutionResponse(false, "Команда 'clear' не принимает аргументы");
        collectionManager.clear();
        return new ExecutionResponse(true, "Коллекция успешно очищена");
    }
}