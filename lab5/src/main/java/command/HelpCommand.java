package command;

import java.util.Map;

/**
 * help : вывести справку по доступным командам.
 */
public class HelpCommand extends BaseCommand {

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            System.out.println("Доступные команды:");
            for (Map.Entry<String, BaseCommand> e : new java.util.TreeMap<>(CommandManager.getCommandMap()).entrySet()) {
                e.getValue().getDescription();
            }
        }
    }

    @Override
    public void getDescription() {
        System.out.println("help : вывести справку по доступным командам");
    }
}
