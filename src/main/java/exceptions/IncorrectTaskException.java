package exceptions;

public class IncorrectTaskException extends Exception {

    public IncorrectTaskException() {
        super("Команда указана некорректно. Введите команду еще раз.\n" +
                "   Доступные команды:\n" +
                "       add <описание задачи>\n" +
                "       print [all]\n" +
                "       search <substring>\n" +
                "       toggle <идентификатор задачи>\n" +
                "       delete <идентификатор задачи>\n" +
                "       edit <идентификатор задачи> <новое значение>\n" +
                "       quit");
    }
}
