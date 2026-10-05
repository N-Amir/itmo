package command;

import client.ClientManager;
import console.ConsoleManager;
import console.ReaderWriter;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Абстрактный базовый класс для всех команд.
 */
public abstract class BaseCommand {
    protected ClientManager clientManager = new ClientManager();
    protected ReaderWriter readerWriter = new ConsoleManager();
    private Object argument;

    public Object getArgument() {
        return argument;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }

    public abstract void execute(String[] args) throws JAXBException, IOException;

    public abstract void getDescription();
}
