package command;

/**
 * exit : завершить программу (без сохранения в файл).
 */
public class ExitCommand extends BaseCommand {

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            System.out.println("Работа завершена, до связи!");
            System.exit(0);
        }
    }

    @Override
    public void getDescription() {
        System.out.println("exit : завершить программу (без сохранения в файл)");
    }
}
