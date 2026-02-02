package org.example.app.ui;

import org.example.app.entities.Task;
import org.example.app.entities.TaskStatus;
import org.example.app.exceptions.*;
import org.example.app.services.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final CommentService commentService;

    public ConsoleUI(UserService userService, ProjectService projectService,
                     TaskService taskService, CommentService commentService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
        this.commentService = commentService;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
                ---- MENU ----
                1) Create user
                2) Create project
                3) Add task to project
                4) Change task status
                5) Add comment to task
                6) List tasks of project
                7) List tasks by status
                0) Exit
                """);

            String cmd = sc.nextLine().trim();
            try {
                switch (cmd) {
                    case "1" -> createUser(sc);
                    case "2" -> createProject(sc);
                    case "3" -> addTask(sc);
                    case "4" -> changeStatus(sc);
                    case "5" -> addComment(sc);
                    case "6" -> listTasks(sc);
                    case "7" -> listTasksByStatus(sc);
                    case "0" -> { return; }
                    default -> System.out.println("unknown command");
                }
            } catch (NotFoundException | DeadlineInPastException | TaskWithoutProjectException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("UNEXPECTED ERROR: " + e.getMessage());
            }

            System.out.println();
        }
    }

    private void createUser(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        long id = userService.createUser(name, email);
        System.out.println("Created user id=" + id);
    }


    private void createProject(Scanner sc) {
        System.out.print("Owner userId: ");
        long ownerId = Long.parseLong(sc.nextLine());
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Description: ");
        String desc = sc.nextLine();
        long id = projectService.createProject(ownerId, title, desc);
        System.out.println("Created project id=" + id);
    }

    private void addTask(Scanner sc) {
        System.out.print("ProjectId: ");
        long projectId = Long.parseLong(sc.nextLine());
        System.out.print("Task title: ");
        String title = sc.nextLine();
        System.out.print("Task description: ");
        String desc = sc.nextLine();
        System.out.print("Deadline (YYYY-MM-DD) or empty: ");
        String d = sc.nextLine().trim();
        LocalDate deadline = d.isEmpty() ? null : LocalDate.parse(d);

        long id = taskService.addTask(projectId, title, desc, deadline);
        System.out.println("Created task id=" + id);
    }

    private void changeStatus(Scanner sc) {
        System.out.print("TaskId: ");
        long taskId = Long.parseLong(sc.nextLine());
        System.out.print("New status (TODO/IN_PROGRESS/DONE): ");
        TaskStatus st = TaskStatus.valueOf(sc.nextLine().trim());
        taskService.changeStatus(taskId, st);
        System.out.println("Status updated");
    }

    private void addComment(Scanner sc) {
        System.out.print("TaskId: ");
        long taskId = Long.parseLong(sc.nextLine());
        System.out.print("Author userId: ");
        long authorId = Long.parseLong(sc.nextLine());
        System.out.print("Text: ");
        String text = sc.nextLine();
        long id = commentService.addComment(taskId, authorId, text);
        System.out.println("Created comment id=" + id);
    }

    private void listTasks(Scanner sc) {
        System.out.print("ProjectId: ");
        long projectId = Long.parseLong(sc.nextLine());
        List<Task> list = taskService.listTasks(projectId);
        if (list.isEmpty()) {
            System.out.println("No tasks");
            return;
        }
        for (Task t : list) {
            System.out.println("#" + t.getId() + " [" + t.getStatus() + "] " + t.getTitle() + " deadline=" + t.getDeadline());
        }
    }
    private void listTasksByStatus(Scanner sc) {
        System.out.print("ProjectId: ");
        long projectId = Long.parseLong(sc.nextLine());

        System.out.print("Status (TODO / IN_PROGRESS / DONE): ");
        TaskStatus status = TaskStatus.valueOf(sc.nextLine().trim());

        List<Task> list = taskService.listTasksByStatus(projectId, status);

        if (list.isEmpty()) {
            System.out.println("No tasks with status " + status);
            return;
        }

        for (Task t : list) {
            System.out.println(
                    "#" + t.getId() +
                            " [" + t.getStatus() + "] " +
                            t.getTitle() +
                            " deadline=" + t.getDeadline()
            );
        }
    }
}

