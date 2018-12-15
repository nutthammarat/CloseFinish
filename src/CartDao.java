
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class CartDao {

    static MongoClientURI uri = new MongoClientURI("mongodb://admin:password1@ds249503.mlab.com:49503/finalproject");
    static MongoClient client = new MongoClient(uri);
    static MongoDatabase db = client.getDatabase(uri.getDatabase());
    static MongoCollection<Document> col = db.getCollection("cart");
    static DBCursor cursor = null;

    static public boolean addCart(User user, Product product, int num) {
        System.out.println(user.getUsername());
        Product thisProduct = getProductCart(user, product);
        if (thisProduct == null) {
            col.insertOne(new Document("username", user.getUsername()).append("pId", product.getId()).append("num", num));
            return true;
        } else {
            
            return false;
        }
    }

    
//    public  static DefaultTableModel setRank() {
//        DBCollection coll = (DBCollection) db.getCollection("cart");
//        cursor = coll.find();
//        String[] columnName = {"username", "pid", "num"};
//        DefaultTableModel tableRank = new DefaultTableModel(columnName, 0);
//       
//        cursor.sort(new BasicDBObject("pid", -1));
//        
//        while(cursor.hasNext()) {
//            
//            DBObject obj = cursor.next();
//            String username = (String)obj.get("username");
//            int win = (int)obj.get("pId");
//           // int lose = (int)obj.get("brand");
//            int draw = (int)obj.get("num");
//           
//            tableRank.addRow(new Object[] {username, win, draw});
//        }
//        return tableRank;
//    }
    static public ArrayList<Cart> getAllProductCart(User user) {
        ArrayList<Cart> cart = new ArrayList<>();
        Document findUser = new Document("username", user.getUsername());
        MongoCursor<Document> cursor = col.find(findUser).sort(new Document("pId",1)).iterator();
        
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            Product thisProduct = ProductDao.getProduct(doc.get("pId").toString());
            cart.add(new Cart(user, thisProduct, (int) doc.get("num")));
        }
        
        return cart;
    }

    static public Product getProductCart(User user, Product product) {
        Product thisProduct = null;
        Document findProduct = new Document("username", user.getUsername()).append("pId", product.getId());
        MongoCursor<Document> cursor = col.find(findProduct).iterator();
        if (cursor.hasNext()) {
            Document doc = cursor.next();
            thisProduct = ProductDao.getProduct(doc.get("pId").toString());
        }
        return thisProduct;
    }

//    public static void showAllProductInCart() {
//        ArrayList<String> arr = new ArrayList<>();
//        User user = UserService.getUser(LoginForm.usercurent);
//        ArrayList<Cart> cart = null;
//        cart = CartService.getAllProduct(user);
//        if (cart.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "ไม่มีสินค้าอยู่ในตระกร้า!!!!");
//            
//        } else {
//            int sum = 0;
//            for (Cart e : cart) {
//                arr.add(e.getProduct().toString() + " " + e.getNum());
//            }
//            DefaultListModel listModel = new DefaultListModel();
//            for (int i = 0; i < arr.size(); i++) {
//                listModel.addElement(arr.get(i));
//            }
//            CartPage.list1.setModel(listModel);
            //CartPage.list1 = new JList(arr.toArray());
//            DefaultListModel listModel = new DefaultListModel();
//            ArrayList<Cart> list = CartService.getAllProduct(user);
//            for (int i = 0; i < list.size(); i++) {
//                listModel.addElement(list.get(i)+ " ");
//            }
//            CartPage.list1.setModel(listModel);

//        }
//    }
    public static void deleteProduct(User user,Product product){
        Product thisProduct = null;
        Document findProduct = new Document("username", user.getUsername()).append("pId", product.getId());
        MongoCursor<Document> cursor = col.find(findProduct).iterator();
        if (cursor.hasNext()) {
            col.deleteOne(new Document(findProduct));
        }
    }
    public static void deleteAllProduct(User user,Product product){
        
    }

}
