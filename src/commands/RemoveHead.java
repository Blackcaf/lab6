package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда для вывода и удаления первого элемента коллекции
 */
public class RemoveHead extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveHead(Console console, CollectionManager collectionManager) {
        super("remove_head", "вывести первый элемент коллекции и удалить его");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length > 0) return new ExecutionResponse(false, "Команда 'remove_head' не принимает аргументы");
        if (collectionManager.getCollection().isEmpty()) return new ExecutionResponse(false, "Коллекция пуста");
        String head = collectionManager.getCollection().firstElement().toString();
        collectionManager.getCollection().remove(0);
        return new ExecutionResponse(true, "Удален элемент: " + head);
    }
}
