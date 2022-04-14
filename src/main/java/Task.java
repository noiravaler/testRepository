import lombok.Data;

@Data
public class Task {
    private boolean isComplete;
    private String description;

    public Task(String description) {
        this.isComplete = false;
        this.description = description;
    }
}
