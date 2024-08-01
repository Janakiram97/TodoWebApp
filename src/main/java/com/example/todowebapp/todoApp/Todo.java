package com.example.todowebapp.todoApp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
@Entity
public class Todo {
    @Id
    @GeneratedValue
    private int id;
    @Size(min=10,message = "Please enter atleast 10 characters")
    private String description;
    private LocalDate targetedDate;
    private boolean isCompleted;

    public Todo() {
    }
    public Todo(int id, String description, LocalDate targetedDate, boolean isCompleted) {
        this.id = id;
        this.description = description;
        this.targetedDate = targetedDate;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetedDate() {
        return targetedDate;
    }

    public void setTargetedDate(LocalDate targetedDate) {
        this.targetedDate = targetedDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", targetedDate=" + targetedDate +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
