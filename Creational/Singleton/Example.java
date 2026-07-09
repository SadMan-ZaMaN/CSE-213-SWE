package Creational.Singleton;

import java.io.*;

public class Example{
    public static void main(String[] args) throws Exception {

        // Food food11 = new Food();     // This line will cause a compilation error because the constructor is private
        Food food1 = Food.getFood();     // get the single instance of Food
        Food food2 = Food.getFood();     // get the single instance of Food

        if(food1 == food2){     // check if both references point to the same instance
            System.out.println("Both food1 and food2 refer to the same instance of Food.");
        } else {
            System.out.println("food1 and food2 refer to different instances of Food.");
        }


        /* 
                                // Breaking Singleton Pattern using Reflection
        Food f1 = Food.getFood();
        
        Constructor<Food> constructor = Food.class.getDeclaredConstructor();
        constructor.setAccessible(true); // Make the private constructor accessible
        Food f2 = constructor.newInstance(); // Create a new instance using reflection

        if(f1 == f2){     // check if both references point to the same instance
            System.out.println("Both f1 and f2 refer to the same instance of Food.");
        } else {
            System.out.println("f1 and f2 refer to different instances of Food.");
        }
        */


                            // Breaking Singleton Pattern using Deserialization
        Food f3 = Food.getFood();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("abc.ob"));
        oos.writeObject(f3); // Serialize the singleton instance to a file
        System.out.println("Serialization done");

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("abc.ob"));
        Food f4 = (Food) ois.readObject();
        System.out.println("Deserialization done");

        if(f3 == f4){     // check if both references point to the same instance
            System.out.println("Both f3 and f4 refer to the same instance of Food.");
        } else {
            System.out.println("f3 and f4 refer to different instances of Food.");
        }
    }
}