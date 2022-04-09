import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();
        Task task = null;
        String commandName = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!commandName.equalsIgnoreCase("quit")) {
                System.out.println("Введите команду");
                String[] command = reader.readLine().split(" ");
                commandName = command[0];
                try {
                    switch (commandName) {
                        case "add":
                            String description = command[1];
                            task = new Task(list.size(), description);
                            list.add(task);
                            System.out.println("Задача '" + description + "' добавлена");
                            break;
                        case "print":
                            System.out.printf("%d. [%s] %s\n", task.getIndex() + 1, task.isComplete() ? "x" : " ", task.getDescription());
                            break;
                        case "toggle":
                            int index = Integer.parseInt(command[1]) - 1;
                            Task changedTask = list.get(index);
                            changedTask.setComplete(!changedTask.isComplete());
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
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
