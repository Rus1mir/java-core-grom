package gromcode.main.lesson10.polyexample;

public class Child extends Human {
    public Child(String name) {
        super(name);
    }

    void run (Human human) {
        System.out.println("Child class is called");
        super.run();
    }
}
