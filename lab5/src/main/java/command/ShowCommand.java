package command;

import collection.CollectionManager;

/**
 * show : вывести все элементы коллекции.
 */
public class ShowCommand extends BaseCommand {
    private final CollectionManager collection;

    public ShowCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            collection.show();
        }
    }

    @Override
    public void getDescription() {
        System.out.println("show : вывести все элементы коллекции");
    }
}
