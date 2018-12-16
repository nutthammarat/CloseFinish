import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;

public class OrderDao {
    static MongoClientURI uri = new MongoClientURI("mongodb://admin:password1@ds249503.mlab.com:49503/finalproject");
    static MongoClient client = new MongoClient(uri);
    static MongoDatabase db = client.getDatabase(uri.getDatabase());
    static MongoCollection<Document> col = db.getCollection("order");

    public static void addOrder(ArrayList<Cart> list){
        ArrayList<Document> arr = new ArrayList<>();
        String user = list.get(0).getUser().getUsername();
        for(Cart e : list) {
            Document doc = new Document();
            doc.put("pId",e.getProduct().getId());
            doc.put("name",e.getProduct().getName());
            doc.put("price",e.getProduct().getPrice());
            doc.put("num",e.getNum());
            arr.add(doc);
        }
        col.insertOne(new Document("username",user).append("order",arr));
        System.out.println("Success");
    }

    public static ArrayList<Order> getOrder(User user){
        Document findUser = new Document("username",user.getUsername());
        ArrayList<Cart> thisCart = new ArrayList<>();
        ArrayList<Order> thisOrder = new ArrayList<>();
        MongoCursor<Document> cursor = col.find(findUser).iterator();
        while (cursor.hasNext()){
            thisCart.clear();
            Document doc = cursor.next();
            ArrayList<Document> cart = (ArrayList<Document>) doc.get("order");
            for(int i = 0 ; i < cart.size() ; i++) {
                String pId = cart.get(i).getString("pId");
                int num = cart.get(i).getInteger("num");
                thisCart.add(new Cart(user,ProductDao.getProduct(pId),num));
            }
            thisOrder.add(new Order(thisCart));
        }
        return thisOrder;
    }

    public static void main(String[] args) {
        User user = UserService.getUser("user1");
//        ArrayList<Cart> cart = CartService.getAllProduct(user);
//        addOrder(cart);

//        Order order = getOrder(user);
//        for(Cart e : order.getOrder())
//            System.out.println(e);
    }
}