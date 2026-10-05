package command;

import collection.CollectionManager;

/**
 * info : вывести информацию о коллекции.
 */
public class InfoCommand extends BaseCommand {
    private final CollectionManager collection;

    public InfoCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            collection.info();
        }
    }

    @Override
    public void getDescription() {
        System.out.println("info : вывести информацию о коллекции");
    }
}
