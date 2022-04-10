import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();
        Task task = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                try {
                    System.out.println("Введите команду");
                    String command = reader.readLine();
                    if (command.matches("add .*")) {
                        String description = command.replace("add ", "");
                        task = new Task(list.size(), description);
                        list.add(task);
                        System.out.println("Задача '" + description + "' добавлена");
                    } else if (command.equals("print")) {
                        System.out.printf("%d. [%s] %s%n", task.getIndex() + 1, task.isComplete() ? "x" : " ", task.getDescription());
                    } else if (command.matches("toggle \\d+")) {
                        int index = Integer.parseInt(command.replace("toggle ", "")) - 1;
                        Task changedTask = list.get(index);
                        changedTask.setComplete(!changedTask.isComplete());
                        System.out.printf("Статус задачи '%s' изменен на %s%n", changedTask.getDescription(), changedTask.isComplete());
                    } else if (command.equals("quit")) {
                        break;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.println("Ошибка при вводе данных. Введите команду еще раз");
                }
            }
            System.out.println("Конец программы");
        } catch (IOException e) {
            System.out.println("Ошибка при вводе данных. Введите команду еще раз");
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
