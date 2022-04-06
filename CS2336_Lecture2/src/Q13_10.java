//This program makes two rectangles can compares the area of them
// it then displays if they are the same by having them
//compare the area if the rectangles
public class Q13_10 {

	public static void main(String[] args) {
		//makes two rectangles
		Rectangle rect1 = new Rectangle(10, 2, "red", true);
		Rectangle rect2 = new Rectangle(5, 4, "blue", false);

		System.out.println("Rectangle1 has an area of " + rect1.getArea() + " and rectangle2 has an area of " + rect2.getArea());

		if (rect1.equals(rect2)) {
			System.out.println("They are the same");
		}
		else {
			System.out.println("They are not the same");

		}
	}
}

abstract class GeometricObject {
	private String color = "while";
	private boolean filled;
	private java.util.Date dateCreated;

	//makes default 
	protected GeometricObject() {
		dateCreated = new java.util.Date();
	}

	//makes geometric object 
	protected GeometricObject(String tempColor, boolean tempFilled) {
		dateCreated = new java.util.Date();
		color = tempColor;
		filled = tempFilled;
	}

	//gets color
	public String getColor() {
		return color;
	}

	//sets color
	public void setColor(String color) {
		this.color = color;
	}

	//returns filled
	public boolean isFilled() {
		return filled;
	}

	//sets filled
	public void setFilled(boolean tempFilled) {
		filled = tempFilled;
	}

	//gets date made
	public java.util.Date getDateCreated() {
		return dateCreated;
	}

	@Override //converts to string
	public String toString() {
		return "made on " + dateCreated + " with color " + color + " and is filled " + filled;
	}

	//abstract get area
	public abstract double getArea();

	//abstract get perimeter
	public abstract double getPerimeter();

}

//makes rectange class and implements comparable
class Rectangle extends GeometricObject implements Comparable<Rectangle> {
	private double width;
	private double height;
	
	//makes recrangle no args
	public Rectangle() {
	}
	
	//makes rectangle with length and height
	public Rectangle(
	double tempWidth, double tempHeight) {
	width = tempWidth;
	height = tempHeight;
	}
	
	//makes rectangle with parameters
	public Rectangle(
	double width, double height, String color, boolean filled) {
	this.width = width;
	this.height = height;
	setColor(color);
	setFilled(filled);
	}
	
	//gets width
	public double getWidth() {
	return width;
	}
	
	//sets width
	public void setWidth(double tempWidth) {
	width = tempWidth;
	}
	
	//gets height
	public double getheight() {
	return height;
	}
	
	//sets height
	public void setheight(double tempHeight) {
	height = tempHeight;
	}
	
	@Override //gets area
	public double getArea() {
	return width * height;
	}
	
	@Override //gets perimeter
	public double getPerimeter() {
	return 2 * (width * height);
	}
	
	@Override //makes compare to 
	public int compareTo(Rectangle o) {
	if (getArea() > o.getArea())
		return 1;
	else if (getArea() < o.getArea())
		return -1;
	else
		return 0;
	}
	
	@Override //returns if both are equal
	public boolean equals(Object o) {
		return this.compareTo((Rectangle)o) == 0;
	}
	
	@Override //returns to string
	public String toString() {
	return super.toString() + "Width of " + width + ", height of, " + height + "area of, " + getArea() + " and perimeter or" + getPerimeter();
	}
}