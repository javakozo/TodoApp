package model;

public class Task {
    private int id;
    private String task;
    private boolean completed;

    public Task(int id, String task, boolean completed) {
        this.id = id;
        this.task = task;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public boolean isCompleted() {
        return completed;
    }
}