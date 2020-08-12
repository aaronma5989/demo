package com.example.interview.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public abstract class Shape {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    private String type;

    private double size;

    public Shape(){}

    public Shape(String type, UUID uuid, double size) {
        this.type = type;
        this.uuid = uuid;
        this.size = size;
    }

    public String getType() {
        return this.type;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSize() {
        return this.size;
    }


    public abstract double getArea();

    public abstract String toString();
}