package com.example.interview.services;

import com.example.interview.entity.Shape;
import com.example.interview.models.ShapeModel;

import java.util.List;
import java.util.UUID;

public interface ShapeService {

    List<Shape> sortShapes(List<Shape> list);

    List<Shape> generateShapes();

    List<Shape> searchShapes(List<Shape> list);

    Shape createNewShape(Shape shape);

    List<Shape> findAll();

    Shape findById(UUID id);

    void delete(Shape shape);

    List<Shape> sortShapesInDB();

    List<Shape> getClosestFromDB();

    List<Shape> generateAndSaveShapes();

    Shape convertShapeModelToShape(ShapeModel shapeModel);

    void deleteAll();


}
