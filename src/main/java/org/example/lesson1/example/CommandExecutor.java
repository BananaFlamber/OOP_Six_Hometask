package org.example.lesson1.example;

public interface CommandExecutor {
    String execute(String[] args) throws CommandExecutionException, RobotMoveException;
}
