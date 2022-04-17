import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class Main {
    static int currentNumber = 1;
    static Map<Integer, Task> tasks = new LinkedHashMap<>();
    static Task task = null;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String command = reader.readLine();
                if (command.matches("add .+")) {
                    add(command);
                } else if (command.matches("print.*")) {
                    print(command);
                } else if (command.matches("toggle [1-9]\\d*")) {
                    toggle(command);
                } else if (command.matches("delete [1-9]\\d*")) {
                    delete(command);
                } else if (command.matches("edit [1-9]\\d* .+")) {
                    edit(command);
                } else if (command.matches("search .+")) {
                    search(command);
                } else if (command.equals("quit")) {
                    break;
                } else {
                    log.error("Команда указана некорректно. Введите команду еще раз");
                    System.out.println("Доступные команды: " +
                            "add <описание задачи>\n" +
                            "print [all]\n" +
                            "search <substring>\n" +
                            "toggle <идентификатор задачи>\n" +
                            "delete <идентификатор задачи>\n" +
                            "edit <идентификатор задачи> <новое значение>\n" +
                            "quit");
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при вводе данных. Введите команду еще раз");
        }
    }

    private static void add(String command) {
        log.debug("Выполняется команда " + command);
        String description = command.replace("add", "").trim();
        task = new Task(description);
        tasks.put(currentNumber, task);
        currentNumber++;
    }

    private static void print(String command) {
        log.debug("Выполняется команда " + command);
        String description = command.replace("print", "").trim();
        if (task == null) {
            log.error("Задача для печати отсутствует");
            return;
        }
        if (!description.isEmpty() && !description.equals("all")) {
            log.error("Команда указана некорректно. Формат команды: print [all]");
            return;
        }
        tasks.entrySet().stream().filter(t -> description.equals("all") || !t.getValue().isComplete()).forEach(t -> printTask(t.getKey(), t.getValue()));
    }

    private static void search(String command) {
        log.debug("Выполняется команда " + command);
        String description = command.replace("search", "").trim();
        if (task == null) {
            log.error("Не добавлено ни одной задачи");
            return;
        }
        Map<Integer, Task> sortedList = tasks.entrySet().stream()
                .filter(t -> t.getValue().getDescription().contains(description))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (sortedList.isEmpty()) {
            log.error("Задачи для печати не найдены");
            return;
        }
        sortedList.forEach(Main::printTask);
    }

    private static void toggle(String command) {
        log.debug("Выполняется команда " + command);
        int index = Integer.parseInt(command.replaceAll("[^0-9]", ""));
        if (checkTaskExists(index)) {
            tasks.get(index).setComplete(!tasks.get(index).isComplete());
        }
    }

    private static void delete(String command) {
        log.debug("Выполняется команда " + command);
        int index = Integer.parseInt(command.replaceAll("[^0-9]", ""));
        if (checkTaskExists(index))
            tasks.remove(index);
    }

    private static void edit(String command) {
        log.debug("Выполняется команда " + command);
        Pattern p = Pattern.compile("(edit) ([1-9]\\d*) (.+)");
        Matcher m = p.matcher(command);
        if (m.find()) {
            int index = Integer.parseInt(m.group(2));
            String description = m.group(3);
            if (checkTaskExists(index))
                tasks.get(index).setDescription(description);
        }
    }

    private static boolean checkTaskExists(int index) {
        try {
            if (!tasks.containsKey(index))
                throw new TaskNotFoundException(index);
        } catch (TaskNotFoundException exception){
            log.error(exception.getMessage());
            return false;
        }
        return true;
    }

    private static void printTask(int index, Task task) {
        System.out.printf("%d. [%s] %s%n", index, task.isComplete() ? "x" : " ", task.getDescription());
    }
}
