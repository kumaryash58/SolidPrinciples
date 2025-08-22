package com.solid.principles;

public class BOpenClose {

//	The Open/Closed Principle (OCP) is another SOLID principle of object-oriented design. 
//	It states that software entities (classes, modules, functions, etc.) should be open for extension 
//	but closed for modification. This means you should be able to add new functionality 
//	without changing existing code.

}

//======================Without OCP (Violates Open/Closed Principle)==================
//In this version, every time we add a new shape, we have to modify existing code, which breaks the OCP.
class Circle {
	public double radius;

	public Circle(double radius) {
		this.radius = radius;
	}
}

class Square {
	public double side;

	public Square(double side) {
		this.side = side;
	}
}

class AreaCalculator {
	public double calculateArea(Object shape) {
		if (shape instanceof Circle) {
			Circle circle = (Circle) shape;
			return Math.PI * circle.radius * circle.radius;
		} else if (shape instanceof Square) {
			Square square = (Square) shape;
			return square.side * square.side;
		}
		return 0;
	}
}

//Problem
//Adding a new shape like Rectangle means modifying AreaCalculator.
//This violates the Open/Closed Principle.

//=============================================================================

//==================With OCP (Follows Open/Closed Principle)==================
//In this version, we use polymorphism to make the system open for extension but closed for modification.
interface ShapeOCP {
	double area();
}

class CircleOCP implements ShapeOCP {
	private double radius;

	public CircleOCP(double radius) {
		this.radius = radius;
	}

	@Override
	public double area() {
		return Math.PI * radius * radius;
	}
}

class SquareOCP implements ShapeOCP {
	private double side;

	public SquareOCP(double side) {
		this.side = side;
	}

	@Override
	public double area() {
		return side * side;
	}
}

class AreaCalculatorOCP {
	public double calculateArea(ShapeOCP shape) {
		return shape.area();
	}
}

//Benefits
//You can add new shapes like Rectangle, Triangle, etc. without changing AreaCalculator.
//This follows Open/Closed Principle perfectly.
