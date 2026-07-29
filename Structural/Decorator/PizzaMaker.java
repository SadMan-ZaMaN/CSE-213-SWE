public class PizzaMaker {
    public static void main(String[] args) {
        pizza basicPizza = new TomatoSauce(new Mozzorella(new PlainPizza()));

        System.out.println("Ingredients: "+ basicPizza.getDescription());

        System.out.println("Total Cost: "+basicPizza.getCost());


        pizza basicPizza2 = new TomatoSauce(new PlainPizza());

        System.out.println("Ingredients: "+ basicPizza2.getDescription());

        System.out.println("Total Cost: "+basicPizza2.getCost());
    }
}
