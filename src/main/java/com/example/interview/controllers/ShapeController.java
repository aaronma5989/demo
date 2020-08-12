package com.example.interview.controllers;

import com.example.interview.entity.Shape;
import com.example.interview.models.ShapeModel;
import com.example.interview.services.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shape")
public class ShapeController {

    @Autowired
    private ShapeService shapeService;

    /*
       Input: ShapeModel with type and size
       Return: The created shape
    */
    @PostMapping("")
    public ResponseEntity<?> createSignalShape(@RequestBody ShapeModel shapeModel) {

        shapeModel.validation();
        Shape newShape = shapeService.convertShapeModelToShape(shapeModel);

        if(newShape == null){
            return new ResponseEntity<>(shapeModel.getType() + " is the wrong type", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(shapeService.createNewShape(newShape), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Shape> getAllShapes(){
        return shapeService.findAll();
    }

    /*
       Random generate 50 circles and 50 square and save to DB
       Return: A list of created shapes
     */
    @GetMapping("/random")
    public List<Shape> randomCreateShapes(){
        return shapeService.generateAndSaveShapes();
    }

    /*
      Return: get all data in DB and sorted by area, in descending order
    */
    @GetMapping("/sorted")
    public List<Shape> sortShapesInDB(){
        return shapeService.sortShapesInDB();
    }

    /*
        Return: a shape or shapes with the area closest to the mean from the
        persisted Shape objects
     */
    @GetMapping("/closestShape")
    public List<Shape> getClosestFromDB(){
        return shapeService.getClosestFromDB();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getShapeById(@PathVariable UUID uuid){
        Shape shape = shapeService.findById(uuid);

        if(shape != null){
            return new ResponseEntity<>(shape, HttpStatus.OK);

        }
        return new ResponseEntity<>(uuid + " is not in DB", HttpStatus.NOT_FOUND);
    }


    /*
      Return: total number of the shapes in the DB
    */
    @GetMapping("/count")
    public int getShapesCount(){
        return shapeService.findAll().size();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteShapeById(@PathVariable UUID uuid){

        Shape shape = shapeService.findById(uuid);

        if(shape != null){
            shapeService.delete(shape);
            return new ResponseEntity<>(uuid + " is deleted", HttpStatus.OK);

        }
        return new ResponseEntity<>(uuid + " is not in DB", HttpStatus.NOT_FOUND);
    }
}
