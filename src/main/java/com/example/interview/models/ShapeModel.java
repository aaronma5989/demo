package com.example.interview.models;


public class ShapeModel {

    private String type;

    private double size;

    public ShapeModel(String type, double size) {
        this.type = type;
        this.size = size;

    }

    public double getSize() {
        return this.size;
    }

    public String getType() {
        return this.type;
    }

    public void validation() {

        if(this.type == null){
            throw new RuntimeException("Shape Type is missed");
        }
    }

}
