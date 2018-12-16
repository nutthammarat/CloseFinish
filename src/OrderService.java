
import java.awt.TextArea;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
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
        int index = 1;
        for (Order e : order) {
            for (Cart ee : e.getOrder()) {
                System.out.println(ee.getProduct().getName() + " " + ee.getProduct().getPrice() + " " + ee.getNum());
                listModel.addElement(" Order No. " + index + " "+ ee.getProduct().getName() + " " + ee.getProduct().getPrice() + " " + ee.getNum());
            }

            index++;
            System.out.println("==============================");
            System.out.println(listModel);
            OrderPage.jList1.setModel(listModel);
        }
//        return arr;
    }

    public static void showOrderPage() {
        OrderPage orderpage = new OrderPage();
        orderpage.setVisible(true);
    }

    public static void checkCartNull() {
        try {
            CartService.addCartToOrder(UserService.getUser(LoginForm.userCurrent));
       OrderService.showOrderPage();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "ไม่มีสินค้าอยู่ในตะกร้า กรุณาเพิ่มสินค้าในตระกร้าก่อนกดยืนยันสั่งซื้อสินค้า");
        }
    }

}
