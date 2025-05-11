package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда для вывода информации о коллекции
 */
public class Info extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Info(Console console, CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length > 0) return new ExecutionResponse(false, "Команда 'info' не принимает аргументы");
        return new ExecutionResponse(true, collectionManager.getInfo());
    }
}