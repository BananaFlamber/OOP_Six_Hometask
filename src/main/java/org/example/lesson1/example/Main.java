package org.example.lesson1.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("Добро пожаловать в игру!");
        System.out.println("...описание...");

        RobotMap map;
        while (true) {
            System.out.println("Для создания карты введите 2 положительных числа через пробел");
            try {
                int n = userInput.nextInt();
                int m = userInput.nextInt();
                userInput.nextLine();

                map = new RobotMap(n, m);
                break;
            } catch (InputMismatchException e) {
                System.err.println("Возникла ошибка при создании карты: " + e.getMessage());
                userInput.nextLine();
            } catch (Throwable e) {
                System.err.println("Возникла ошибка на стороне сервера: " + e.getMessage());
                System.exit(1);
            }
        }

        System.out.println("Карта успешно создана!");

        CommandManager commandManager = new CommandManager(map);

        System.out.println("Для просмотра списка допустимых команд введите h");

        while (true) {
            System.out.println("Введите команду");
            String command = userInput.nextLine();
            try {
                String commandExecutionResult = commandManager.handleCommand(command);
                if (Objects.nonNull(commandExecutionResult) && !commandExecutionResult.isBlank()) {
                    System.out.println(commandExecutionResult);
                }
            } catch (CommandNotFoundException e) {
                System.err.println("Команда [" + e.getMessage() + "] не найдена");
            } catch (CommandExecutionException | RobotMoveException e) {
                System.err.println("Во время исполнения команды произошла ошибка: " + e.getMessage());
            }
        }
    }
}