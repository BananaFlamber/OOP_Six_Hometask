package org.example.lesson1.example;

public interface InterfaceRobotMap {
    RobotMap.Robot getRobotByid(Long id);
    RobotMap.Robot createRobot(Point point) throws RobotCreationException;
}
