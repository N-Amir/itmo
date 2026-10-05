package command;

import collection.CollectionManager;

/**
 * clear : очистить коллекцию.
 */
public class ClearCommand extends BaseCommand {
    private final CollectionManager collection;

    public ClearCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            collection.clear();
        }
    }

    @Override
    public void getDescription() {
        System.out.println("clear : очистить коллекцию");
    }
}
