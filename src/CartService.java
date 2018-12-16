 
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
   public static void addCartToOrder(User users){
       OrderDao.addOrder(getAllProduct(users));
    }
    public static void showList() {
        usercurrentdao = LoginForm.userCurrent;
        User user = UserService.getUser(usercurrentdao);
//        User user = UserService.getUser("user1");
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Cart> list = CartService.getAllProduct(user);
        if (list.isEmpty()) {
            refresh();
            JOptionPane.showMessageDialog(null, "ไม่มีสินค้าอยู่ในตะกร้า");
            
            
        } else {
            for (Cart e : list) {
                listModel.addElement(e.toString() + "     จำนวน   " + e.getNum() + "  ชิ้น");
         
            }
            
            CartPage.list1.setModel(listModel);
        }
        CartPage.sumtxt.setText("" + calculatePrice());
    }

    public static void deleteProduct(String id) {
        User user = UserService.getUser(LoginForm.userCurrent);
        CartDao.deleteProduct(user, ProductService.getProduct(id));
        showList();

    }

    public static void deleteAllProduct(String id) {
        User user = UserService.getUser(LoginForm.userCurrent);
        CartDao.deleteAllProduct(user, ProductService.getProduct(id));
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
    public static void refresh(){
        CartPage c = new  CartPage();
        c.setVisible(false);
        //c.setVisible(true);
    }

}
