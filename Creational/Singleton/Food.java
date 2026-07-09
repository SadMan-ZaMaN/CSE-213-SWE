package Creational.Singleton;
// Singleton Design Pattern:  Lazy Initialization

public class Food implements java.io.Serializable{     // """  implements Serializable interface to allow serialization of the singleton instance """
    private static Food food = null;       // static variable to hold the single instance of Food

    private Food(){     // """  private constructor to prevent instantiation """
        if(food != null){     
            throw new RuntimeException("You are trying to break the Singleton pattern.");
        }
    }

// Lazy initialization: The instance is created only when it is needed for the first time.
   
                                        // static method to provide access to the single instance of Food
    public static Food getFood(){     // """  public method to provide access to the single instance of Food """
        if(food == null){     // """  if the instance is not created yet, create it """
            food = new Food();      // create the single instance of Food (sudhu ekbar e call hobe)
        }
        return food;     // """  return the single instance of Food """
    }
    
}




/*

1. Constructor private rakha hoyeche, jate onno class theke Food class er object create kora na jai.

2. Object create korar jonno getFood() method use kora hoyeche, jeta static method, tai direct class name diye call kora jai.

3. Create field to store the single instance of Food class, jeta static variable hisebe declare kora hoyeche.

*/




