package pt.upt.restaurantmenu;



public class MenuDay {
    int dayOfWeek;
    String soup;
    String meatDish;
    String fishDish;
    String dessert;

    public MenuDay(int dayOfWeek, String soup, String meatDish, String fishDish, String dessert) {
        this.dayOfWeek = dayOfWeek;
        this.soup = soup;
        this.meatDish = meatDish;
        this.fishDish = fishDish;
        this.dessert = dessert;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public String getSoup() {
        return soup;
    }

    public String getMeatDish() {
        return meatDish;
    }

    public String getFishDish() {
        return fishDish;
    }

    public String getDessert() {
        return dessert;
    }
}
