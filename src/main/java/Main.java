import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Task> list = new ArrayList<>();
    static Task task = null;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String command = reader.readLine();
                if (command.matches("add .+")) {
                    add(command);
                } else if (command.matches("print .+")) {
                    print(command);
                } else if (command.matches("toggle .+")) {
                    toggle(command);
                } else if (command.equals("quit")) {
                    break;
                } else {
                    System.err.println("Команда указана некорректно. Введите команду еще раз");
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при вводе данных. Введите команду еще раз");
        }
    }

    private static void add(String command) {
        String description = command.replace("add ", "").trim();
        task = new Task(list.size(), description);
        list.add(task);
    }

    private static void print(String command) {
        String description = command.replace("print ", "").trim();
        if (!description.equals("all")) {
            System.err.println("Команда указана некорректно. Формат команды: print [all]");
            return;
        }
        if (task == null) {
            System.err.println("Задача для печати отсутствует");
            return;
        }
        System.out.printf("%d. [%s] %s%n", task.getIndex() + 1, task.isComplete() ? "x" : " ", task.getDescription());
    }

    private static void toggle(String command) {
        try {
            int index = Integer.parseInt(command.replace("toggle ", "").trim()) - 1;
            if (index < 0 || index > list.size() - 1) {
                System.err.println("Команда не найдена");
                return;
            }
            Task changedTask = list.get(index);
            changedTask.setComplete(!changedTask.isComplete());
        } catch (NumberFormatException ex) {
            System.err.println("Команда указана некорректно. Формат команды: toggle <идентификатор задачи>");
        }
    }

    static class Task {
        private final int index;
        private boolean isComplete;
        private final String description;

        public Task(int index, String description) {
            this.isComplete = false;
            this.description = description;
            this.index = index;
        }

        public boolean isComplete() {
            return isComplete;
        }

        public void setComplete(boolean complete) {
            isComplete = complete;
        }

        public String getDescription() {
            return description;
        }

        public int getIndex() {
            return index;
        }
    }
}
