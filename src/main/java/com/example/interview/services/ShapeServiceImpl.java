package com.example.interview.services;

import com.example.interview.entity.Circle;
import com.example.interview.entity.Shape;
import com.example.interview.entity.Square;
import com.example.interview.models.ShapeModel;
import com.example.interview.repository.ShapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShapeServiceImpl implements ShapeService{

    Random r = new Random();

    @Autowired
    private ShapeRepository shapeRepository;

    @Override
    public List<Shape> sortShapes(List<Shape> list) {
        return list.stream().sorted((s1, s2)-> Double.compare(s2.getArea(), s1.getArea())).collect(Collectors.toList());
    }

    public List<Shape> sortShapesInDB() {

        List<Shape> allList = findAll();
        return sortShapes(allList);
    }

    @Override
    public List<Shape> generateShapes() {

        ArrayList<Shape> shapeList = new ArrayList<>();
        double randomInt;

        for(int i = 0; i < 50; i++){
            randomInt = r.nextDouble()*100;
            UUID uuid_c = UUID.randomUUID();
            UUID uuid_s = UUID.randomUUID();

            shapeList.add(new Square(randomInt, uuid_s));
            shapeList.add(new Circle(randomInt/2, uuid_c));

        }

        return shapeList;
    }

    public List<Shape> generateAndSaveShapes() {

        List<Shape> generatedList = generateShapes();
        return shapeRepository.saveAll(generatedList);
    }

    @Override
    public Shape convertShapeModelToShape(ShapeModel shapeModel) {
        if(shapeModel.getType().equals("Circle")){
            UUID uuid_c = UUID.randomUUID();
            return new Circle(shapeModel.getSize(), uuid_c);
        }
        else if(shapeModel.getType().equals("Square")){
            UUID uuid_s = UUID.randomUUID();
            return new Square(shapeModel.getSize(), uuid_s);

        }

        return null;
    }

    @Override
    public void deleteAll() {
        shapeRepository.deleteAll();
    }

    @Override
    public List<Shape> searchShapes(List<Shape> list) {

        double avg =  Math.ceil(list.stream().mapToDouble(Shape::getArea).average().getAsDouble());

        double min = Double.MAX_VALUE;
        List<Shape> result = new ArrayList<>();
        for(Shape shape : list){
            double dist = Math.abs(shape.getArea() - avg);
            if(dist < min){
                result.clear();
                result.add(shape);
                min = dist;
            }
            else if(dist == min){
                result.add(shape);
            }
        }

        return result;
    }

    public List<Shape> getClosestFromDB() {

        List<Shape> allList = findAll();
        return searchShapes(allList);
    }

    public Shape createNewShape(Shape shape){return shapeRepository.save(shape);}

    public List<Shape> findAll(){
        return shapeRepository.findAll();
    }

    public Shape findById(UUID id){
        return shapeRepository.getByUuid(id);
    }

    public void delete(Shape shape){
        shapeRepository.delete(shape);
    }
}
