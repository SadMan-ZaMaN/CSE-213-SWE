abstract class ToppingDecorator implements pizza {

    protected pizza tempPizza;

    public ToppingDecorator(pizza newPizza) {
        tempPizza = newPizza;
    }

    @Override
    public double getCost() {
        // TODO Auto-generated method stub
        return tempPizza.getCost();
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return tempPizza.getDescription();
    }
    
}
