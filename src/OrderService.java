
import java.awt.TextArea;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author WATCHARAPOL PLOYJAN
 */
public class OrderService {

    public static void addOrder(ArrayList<Cart> list) {
        OrderDao.addOrder(list);
    }

    public static void getOrder(User user) {
        ArrayList<Order> order = OrderDao.getOrder(user);
        ArrayList<String> arr = new ArrayList<>();
        DefaultListModel listModel = new DefaultListModel();
       // ArrayList<Cart> list = CartService.getAllProduct(user);
        for (Order e : order) {
            for (Cart ee : e.getOrder()) {
                //System.out.println(ee.getProduct().getName() + " " + ee.getProduct().getPrice()+ " " + ee.getNum());
                listModel.addElement(ee.getProduct().getName() + " " + ee.getProduct().getPrice()+ " " + ee.getNum());
            }
           // System.out.println("==============================");
            OrderPage.jList1.setModel(listModel);
        }
        //return arr;
    }

    public static void showOrderPage() {
        OrderPage orderpage = new OrderPage();
        orderpage.setVisible(true);
    }

    public static void getOrderDetail() {

    }

}
