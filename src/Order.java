import java.util.ArrayList;

public class Order {
    private ArrayList<Cart> order;

    public Order(ArrayList<Cart> list){
        this.order = list;
    }
    public ArrayList<Cart> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Cart> order) {
        this.order = order;
    }
    public String toString(){
        return order.toString();
    }
}