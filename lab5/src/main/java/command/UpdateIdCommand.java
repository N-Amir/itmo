package command;

import client.ClientManager;
import collection.CollectionManager;
import data.City;

/**
 * update id {element} : обновить значение элемента коллекции, id которого равен заданному.
 */
public class UpdateIdCommand extends BaseCommand {
    private final CollectionManager collection;

    public UpdateIdCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
            return;
        }
        try {
            Integer id = Integer.parseInt(args[1]);
            City city = ExecuteScriptCommand.getFlag()
                    ? ClientManager.createCityFromScript(ExecuteScriptCommand.getCityList())
                    : clientManager.getCity();
            collection.updateId(id, city);
        } catch (NumberFormatException e) {
            System.out.println("Введён некорректный ID");
        }
    }

    @Override
    public void getDescription() {
        System.out.println("update id {element} : обновить значение элемента коллекции по id");
    }
}
