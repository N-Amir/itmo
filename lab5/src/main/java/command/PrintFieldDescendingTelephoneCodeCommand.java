package command;

import collection.CollectionManager;

/**
 * print_field_descending_telephone_code : вывести значения поля telephoneCode всех элементов в порядке убывания.
 */
public class PrintFieldDescendingTelephoneCodeCommand extends BaseCommand {
    private final CollectionManager collection;

    public PrintFieldDescendingTelephoneCodeCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else if (collection.getCollection().isEmpty()) {
            System.out.println("В коллекции нет элементов");
        } else {
            collection.printFieldDescendingTelephoneCode();
        }
    }

    @Override
    public void getDescription() {
        System.out.println("print_field_descending_telephone_code : вывести значения поля telephoneCode всех элементов в порядке убывания");
    }
}
