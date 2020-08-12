package com.example.interview;

import com.example.interview.entity.Shape;
import com.example.interview.models.ShapeModel;
import com.example.interview.services.ShapeService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class InterviewApplicationTests {

	private final double size_1 = 2.0;

	private final double size_2 = 3.0;


	@Autowired
	private ShapeService shapeService;

	@Test
	public void randomGenerateAndSaveShapesTest() {

		shapeService.generateAndSaveShapes();

		Assert.assertEquals(100, shapeService.findAll().size());

		shapeService.deleteAll();

	}



	@Test
	public void deleteShapeByIdTest() {

		Shape shapeA = shapeService.convertShapeModelToShape(new ShapeModel("Square", size_1));
		shapeService.createNewShape(shapeA);

		List<Shape> list = shapeService.findAll();
		Assert.assertEquals(1, list.size());

		UUID uuid = list.get(0).getUuid();
		Shape shape = shapeService.findById(uuid);

		shapeService.delete(shape);

		Assert.assertEquals(0, shapeService.findAll().size());

		shapeService.deleteAll();

	}

	@Test
	public void searchShapesTwoEqualTest() {

		Shape shapeA = shapeService.convertShapeModelToShape(new ShapeModel("Square", size_1));
		Shape shapeB = shapeService.convertShapeModelToShape(new ShapeModel("Square", size_1));

		shapeService.createNewShape(shapeA);
		shapeService.createNewShape(shapeB);

		Assert.assertEquals(2, shapeService.findAll().size());

		List<Shape> closest = shapeService.getClosestFromDB();

		assertEquals(2, closest.size());

		Shape shape = closest.get(0);

		assertEquals(4.00, shape.getArea());
		assertEquals("Square", shape.getType());
		assertEquals(2.00, shape.getSize());

		shapeService.deleteAll();


	}



}
