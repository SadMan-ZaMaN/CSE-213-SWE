public class TomatoSauce extends ToppingDecorator{

    public TomatoSauce(pizza newPizza) {
        super(newPizza);
        
        System.out.println("Adding Tomato Sauce");
    }

    @Override
    public String getDescription(){
        return tempPizza.getDescription() + ", Tomato Sauce";
    }

    @Override
    public double getCost(){
        return tempPizza.getCost() + 1.00 ;
    }
    
}
