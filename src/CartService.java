
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class CartService {

    static String usercurrentdao;

    public static ArrayList<Cart> getAllProduct(User users) {
        return CartDao.getAllProductCart(users);
    }

    public static boolean addToCart(User users, Product product, int num) {
        return CartDao.addCart(users, product, num);
    }

    public static void addCartToOrder(User users) {
        try{
        OrderDao.addOrder(getAllProduct(users));
        deleteCart(users);
        }catch (Exception e){
            
        }
    }

    public static void showList() {
        usercurrentdao = LoginForm.userCurrent;
        User user = UserService.getUser(usercurrentdao);
//        User user = UserService.getUser("user1");
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Cart> list = CartService.getAllProduct(user);
        
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ไม่มีสินค้าอยู่ในตะกร้า");

            CartPage.list1.setVisible(false);
        }else {
            
            for (Cart e : list) {
                listModel.addElement(e.toString() + "     จำนวน   " + e.getNum() + "  ชิ้น");

            }

            CartPage.list1.setModel(listModel);
        }
        CartPage.sumtxt.setText("" + calculatePrice());
    }

    public static boolean deleteProduct(String id) {
        User user = UserService.getUser(LoginForm.userCurrent);
        CartDao.deleteProduct(user, ProductService.getProduct(id));
        showList();
        return true;
    }

    public static void deleteAllProduct(User user) {
        CartDao.deleteAllProduct(user);
        showList();

    }

    public static void showCartPage() {
        CartPage cart = new CartPage();
        cart.setVisible(true);
    }

    public static int calculatePrice() {
        User user = UserService.getUser(LoginForm.userCurrent);
        ArrayList<Cart> cart = null;
        cart = CartService.getAllProduct(user);
        ArrayList<Integer> total = new ArrayList<>();
        int sum = 0;
        for (Cart e : cart) {
            sum = (e.getProduct().getPrice()) * (e.getNum());
            total.add(sum);
            sum = 0;
        }
        for (int i = 0; i < total.size(); i++) {
            sum += total.get(i);
        }
        return sum;
    }

    public static void deleteCart(User user) {
        CartDao.deleteAllProduct(user);
        showList();
    }

}
