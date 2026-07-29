public class Mozzorella extends ToppingDecorator{

    public Mozzorella(pizza newPizza) {
        super(newPizza);
        
        System.out.println("Adding Dough");
        System.out.println("Adding Mozzorella");
    }

    @Override
    public String getDescription(){
        return tempPizza.getDescription() + ", Mozorella";
    }

    @Override
    public double getCost(){
        return tempPizza.getCost() + 2.00 ;
    }
    
}
