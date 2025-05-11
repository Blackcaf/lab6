package commands;

import managers.CollectionManager;
import models.HumanBeing;
import utility.Console;
import utility.ExecutionResponse;
import utility.HumanBeingAsker;

/**
 * Команда для обновления элемента коллекции по id.
 */
public class Update extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Update(Console console, CollectionManager collectionManager, HumanBeingAsker asker) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length != 1) return new ExecutionResponse(false, "Команда требует один аргумент: id");
        try {
            long id = Long.parseLong(arguments[0]);
            HumanBeing oldHuman = collectionManager.getById(id);
            if (oldHuman == null)
                return new ExecutionResponse(false, "Элемент с id " + id + " не найден");

            HumanBeing newHuman = collectionManager.getTempHuman();
            if (newHuman == null)
                return new ExecutionResponse(false, "Новый элемент не передан от клиента");

            newHuman.setId(id);
            collectionManager.removeById(id);
            collectionManager.addWithId(newHuman);

            return new ExecutionResponse(true, "Элемент с id " + id + " успешно обновлён");
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "id должен быть числом");
        }
    }
}
