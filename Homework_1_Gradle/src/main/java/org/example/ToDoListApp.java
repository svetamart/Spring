package org.example;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthorizationManager authorizationManager = new AuthorizationManager();
        User currentUser = null;

        while (true) {
            System.out.println("1. Войти");
            System.out.println("2. Зарегистрироваться");
            System.out.println("0. Выйти");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    currentUser = authorizationManager.loginUser(scanner);
                    break;
                case 2:
                    currentUser = authorizationManager.registerUser(scanner);
                    break;
                case 0:
                    System.out.println("До свидания!");
                    System.exit(0);
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
                    break;
            }

            if (currentUser != null) {
                break;
            }
        }

        String tasksFileName = currentUser.getTasksFileName();
        List<ToDo> tasks = loadTasksFromFile(tasksFileName);

        while (true) {
            System.out.println("1. Добавить новую задачу");
            System.out.println("2. Отметить задачу как выполненную");
            System.out.println("3. Показать список задач");
            System.out.println("0. Выйти");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addNewTask(scanner, tasks, tasksFileName);
                    break;
                case 2:
                    markTaskAsDone(scanner, tasks, tasksFileName);
                    break;
                case 3:
                    displayTasks(tasks);
                    break;
                case 0:
                    saveTasksToFile(tasksFileName, tasks);
                    System.out.println("До свидания!");
                    System.exit(0);
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
                    break;
            }
        }
    }

    public static void addNewTask(Scanner scanner, List<ToDo> tasks, String tasksFileName) {
        System.out.println("Введите название новой задачи:");
        String newTaskTitle = scanner.nextLine();
        tasks.add(new ToDo(newTaskTitle));
        saveTasksToFile(tasksFileName, tasks);
        System.out.println("Новая задача добавлена.");
    }

    public static void saveTasksToFile(String fileName, List<ToDo> tasks) {
        try {
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            objectMapper.writeValue(new File(fileName), tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<ToDo> loadTasksFromFile(String fileName) {
        List<ToDo> tasks = new ArrayList<>();

        File file = new File(fileName);
        if (file.exists()) {
            try {
                tasks = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, ToDo.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tasks;
    }

    public static void markTaskAsDone(Scanner scanner, List<ToDo> tasks, String tasksFileName) {
        System.out.println("Введите порядковый номер задачи:");
        String input = scanner.nextLine();

        try {
            int taskNumber = Integer.parseInt(input) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                tasks.get(taskNumber).setDone(true);
                saveTasksToFile(tasksFileName, tasks);
                System.out.println("Задача отмечена как выполненная.");
            } else {
                System.out.println("Некорректный номер задачи. Попробуйте снова.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Попробуйте снова.");
        }
    }

    public static void displayTasks(List<ToDo> tasks) {
        System.out.println("Список задач:");
        for (int i = 0; i < tasks.size(); i++) {
            ToDo task = tasks.get(i);
            String status = task.isDone() ? "[x]" : "[ ]";
            System.out.println((i + 1) + ". " + status + " " + task.getTitle());
        }
    }
}
