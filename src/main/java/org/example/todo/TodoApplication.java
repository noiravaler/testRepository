package org.example.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.core.ConsoleTaskManager;
import org.example.todo.core.exceptions.IncorrectTaskException;
import org.example.todo.core.exceptions.TaskNotFoundException;
import org.example.todo.logic.command.Command;
import org.example.todo.view.ITaskPrinter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class TodoApplication implements CommandLineRunner {

	private final ITaskPrinter printer;
	private final List<Command> commands;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		ConsoleTaskManager taskManager = new ConsoleTaskManager(commands, printer);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			while (true) {
				System.out.println("Введите команду:");
				String commandLine = reader.readLine();
				try {
					taskManager.executeCommand(commandLine);
				} catch (IncorrectTaskException | TaskNotFoundException ex) {
					log.error("Error in processing", ex);
				}
			}
		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
			System.err.println("Произошла ошибка при вводе данных");
		}
	}
}
