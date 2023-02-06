package org.example.lesson1.example;

public interface InterfaceRobot {
    void move() throws RobotMoveException;

    void move(int moveLength) throws RobotMoveException;

    void changeDirection(Direction direction);

    Long getId();

    RobotMap.MapPoint getPoint();
}
