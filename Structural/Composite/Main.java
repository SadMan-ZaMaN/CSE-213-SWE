package Structural.Composite;

public class Main {

    public static void main(String[] args) {

        MenuComponent breakfastMenu =
                new Menu("Breakfast Menu", "Morning meals");

        MenuComponent lunchMenu =
                new Menu("Lunch Menu", "Afternoon meals");

        MenuComponent dessertMenu =
                new Menu("Dessert Menu", "Sweet dishes");

        MenuComponent allMenus =
                new Menu("ALL MENUS", "Complete menu");

        allMenus.add(breakfastMenu);
        allMenus.add(lunchMenu);

        breakfastMenu.add(new MenuItem(
                "Pancakes",
                "Pancakes with syrup",
                true,
                2.99));

        breakfastMenu.add(new MenuItem(
                "Omelette",
                "Egg and cheese",
                false,
                4.99));

        lunchMenu.add(new MenuItem(
                "Burger",
                "Beef burger",
                false,
                7.99));

        lunchMenu.add(dessertMenu);

        dessertMenu.add(new MenuItem(
                "Ice Cream",
                "Vanilla",
                true,
                1.99));

        Waitress waitress = new Waitress(allMenus);
        waitress.printMenu();
    }
}