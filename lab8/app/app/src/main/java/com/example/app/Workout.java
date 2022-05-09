package com.example.app;

public class Workout {
    private String name;
    private String description;

    public static Workout[] workouts = new Workout[]{
        new Workout("Workout#1","10 Pull-ups\n10 Push-ups\n10 Squats"),
        new Workout("Workout#2","20 Pull-ups\n20 Push-ups\n20 Squats"),
        new Workout("Workout#3","30 Pull-ups\n30 Push-ups\n30 Squats"),
        new Workout("Workout#4","40 Pull-ups\n40 Push-ups\n40 Squats"),
        new Workout("Workout#5","50 Pull-ups\n50 Push-ups\n50 Squats")
    };;

    public Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

