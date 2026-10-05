package command;

import client.ClientManager;
import collection.CollectionManager;
import data.City;

/**
 * remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный.
 */
public class RemoveLowerCommand extends BaseCommand {
    private final CollectionManager collection;

    public RemoveLowerCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
            return;
        }
        City city = ExecuteScriptCommand.getFlag()
                ? ClientManager.createCityFromScript(ExecuteScriptCommand.getCityList())
                : clientManager.getCity();
        collection.removeLower(city);
    }

    @Override
    public void getDescription() {
        System.out.println("remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
    }
}
