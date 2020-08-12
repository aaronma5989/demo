package com.example.interview.entity;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Square extends Shape{

    private double area;

    public Square(){}

    public Square(double size, UUID uuid) {
        super("Square", uuid, size);
        this.area = size * size;
    }

    public double getArea() {
        return this.area;
    }

    @Override
    public String toString() {
        return String.format("%1$s : Size = %2$.2f, Area = %3$.2f", this.getType(), getSize(), this.getArea());

    }
}
