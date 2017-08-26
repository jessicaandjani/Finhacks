package com.example.finhacks;

/**
 * Created by asus on 8/26/2017.
 */
public class Event {
    private String name, description, targetBalance, currentBalance, deadline;

    public Event() {

    }

    public Event(String name, String description, String targetBalance, String currentBalance, String deadline) {
        this.name = name;
        this.description = description;
        this.targetBalance = targetBalance;
        this.currentBalance = currentBalance;
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTargetBalance() {
        return targetBalance;
    }

    public void setTargetBalance(String targetBalance) {
        this.targetBalance = targetBalance;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

}
