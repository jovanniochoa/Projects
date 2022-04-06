public class LectureTest {
	//double static radius = 10.0;
	//double static area;
	//double radius2 = 5.0;
	public static void main(String[] args) {
		double radius = 0.0;
		double area = 0.0;
		if (radius < 0) {
			System.out.println("Incorrect input");
		}
		else {
			area = radius * radius * 3.14159;
			System.out.println("Area is " + area);
		}
	}
	public LectureTest() {
		
	}
}
