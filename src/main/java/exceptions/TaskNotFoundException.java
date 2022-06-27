package exceptions;

public class TaskNotFoundException extends Exception{

    public TaskNotFoundException(int index) {
        super(String.format("Задача с индексом %d не найдена", index));
    }
}
