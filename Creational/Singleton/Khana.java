package Creational.Singleton;
// Singleton Design Pattern:  Eager Initialization

public class Khana {

    // Eager initialization: The instance is created at the time of class loading.
    private static Khana khana = new Khana(); 

    private Khana() { // private constructor to prevent instantiation

    }

    public static Khana getKhana(){     // """  public method to provide access to the single instance of Khana """
        if(khana == null){     // """  if the instance is not created yet, create it """
            khana = new Khana();      // create the single instance of Khana (sudhu ekbar e call hobe)
        }
        return khana;     // """  return the single instance of Khana """
    }
}



