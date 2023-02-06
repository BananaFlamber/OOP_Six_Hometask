package org.example.lesson1.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private final InterfaceRobotMap map;
    private final Map<String, CommandExecutor> commands;

    protected CommandManager(RobotMap map){
        this.map = map;

        commands = new HashMap<>();
        commands.put("h", this::printHelp);
        commands.put("q", this::quit);
        commands.put("a", this::addRobot);
        commands.put("l", this::listRobots);
        commands.put("m", this::moveRobots);
        commands.put("cd", this::changeDirectionRobots);
    }

    public String handleCommand(String command) throws CommandNotFoundException, CommandExecutionException, RobotMoveException {
        String[] split = command.split(" ");
        String commandCode = split[0];

        CommandExecutor executor = commands.get(commandCode);
        if (executor == null) {
            throw new CommandNotFoundException(command);
        }

        String[] args = Arrays.copyOfRange(split, 1, split.length);
        return executor.execute(args);
    }

    private String addRobot(String[] args) throws CommandExecutionException {
        if (args.length < 2) {
            throw new CommandExecutionException("Недостаточно аргументов");
        }

        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);

        try {
            map.createRobot(new Point(x, y));
        } catch (RobotCreationException e) {
            throw new CommandExecutionException(e.getMessage());
        }

        return null;
    }


    private String listRobots(String[] args) {
        return String.valueOf(RobotMap.getRobots());
    }

    private String moveRobots(String[] id) throws RobotMoveException {
        long l = Long.parseLong(id[0]);
        RobotMap.Robot robot = map.getRobotByid(l);
        robot.move();
        return null;
    }

    private String changeDirectionRobots(String[] args) {
        long l = Long.parseLong(args[0]);
        RobotMap.Robot robot = map.getRobotByid(l);
        switch (args[1]) {
            case "t" -> robot.changeDirection(Direction.TOP);
            case "r" -> robot.changeDirection(Direction.RIGHT);
            case "b" -> robot.changeDirection(Direction.BOTTOM);
            case "l" -> robot.changeDirection(Direction.LEFT);
        }
        return null;
    }

    private String printHelp (String[]args){
        return """
                        h                  -> распечатать список допустимых команд (help)
                        a 1 2              -> создать робота на позиции (1, 2) (add)
                        l                  -> распечатать всех роботов (list)
                        m id [5]           -> перемещаем робота на 1 единицу вперед (move)
                        cd id [t, r, b, l] -> изменить направление робота (change direction)
                        q                  -> завершить программу (quit)
                        """;
    }

    private String quit (String[]args){
        System.exit(0);
        return null;
    }
}
