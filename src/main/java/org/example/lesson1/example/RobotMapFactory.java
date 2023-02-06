package org.example.lesson1.example;

public class RobotMapFactory {
    public static InterfaceRobotMap create(int n, int m) throws RobotCreationException {
        return new RobotMap(n, m);
    }
}
