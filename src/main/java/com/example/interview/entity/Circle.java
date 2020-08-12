package com.example.interview.entity;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Circle extends Shape {

    private double area;

    public Circle(){}

    public Circle(double size, UUID uuid) {
        super("Circle", uuid, size);
        this.area = Math.PI * size * size;
    }

    public double getArea() {
        return this.area;
    }


    @Override
    public String toString() {
        return String.format("%1$s : Radius = %2$.2f, Area = %3$.2f", this.getType(), getSize(), this.getArea());
    }

}