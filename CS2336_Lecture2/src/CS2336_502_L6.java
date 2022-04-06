public class CS2336_502_L6 {
  public static void main(String [] args) {
    Apple a1 = new Apple(); // "Red Delicious"
    Apple a2 = new Apple(); //"Red Delicious"

    Apple a3 = new Apple("Gala", false);
    Apple a4 = new Apple("Gala", true);

    if (a1.equals(a1)) { // equals method from Object class true ONLY when the same object
      System.out.printf("0. These two apples are the same!\n");
    }

    if (a1.equals(a2)) { // equals method from Object class true ONLY when the same object
      System.out.printf("1. These two apples are the same!\n");
    }

    if (a1.getType() == a2.getType()) { // == comparing addresses of two Strings
      System.out.printf("2. These two apples are the same!\n");
    }

    if (a1.getType().equals(a2.getType())) { // compares the characters of the type
      System.out.printf("3. These two apples are the same!\n");
    }

    if (a3.equals(a4)) {
      System.out.printf("4. These two apples are the same!\n");
    }
    if (a3.getType() == a4.getType()) {
      System.out.printf("5. These two apples are the same!\n");
    }

    if (a3.equals(a4)) {
      System.out.printf("6. These two apples are the same!\n");
    }
  }

}

class Apple extends Object {
  String type;
  boolean organicInd;
  public Apple() {
    setType("Red Delicious");
    setOrganic(false);
  }
  public Apple(String t, boolean o) {
    setType(t);
    setOrganic(o);
  }
  // mutator function / method
  public void setType(String t) {
    type = new String(t);
  }

  public void setOrganic(boolean o) {
    organicInd = o;
  }

  // exists in the Object class
  // and it is being overloaded here
  public String toString() {
    // return type + " Apple!";
    return type.concat(" Apple!");
  }

  public String getType() {
    return type;
  }
  public boolean getOrganicInd() {return organicInd;}

  public boolean equals(Apple o) {
    return (o.getType().equals(type) && o.getOrganicInd() == organicInd);
  }

}