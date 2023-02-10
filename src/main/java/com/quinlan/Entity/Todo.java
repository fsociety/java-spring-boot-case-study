package com.quinlan.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private Boolean done;

    public Todo(){}

    public Todo(Integer id, String title, String description, Boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title != null){this.title = title;}
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description != null){this.description = description;}
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        if(done != null){this.done = done;}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) && Objects.equals(title, todo.title) && Objects.equals(description, todo.description) && Objects.equals(done, todo.done);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, done);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", done=" + done +
                '}';
    }

    public String toJson() {
        return "{" +
                "\"id\": " + id +
                ", \"title\": \""+ title +"\" " +
                ", \"description\": \""+ description +"\" " +
                ", \"done\": " + done +
                '}';
    }
}
