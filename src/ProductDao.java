import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.*;
import java.util.*;

public class ProductDao {
    static MongoClientURI uri = new MongoClientURI("mongodb://admin:password1@ds249503.mlab.com:49503/finalproject");
    static MongoClient client = new MongoClient(uri);
    static MongoDatabase db = client.getDatabase(uri.getDatabase());
    static MongoCollection<Document> col = db.getCollection("product");

    static public ArrayList<Product> getAllProducts() {
        MongoCursor<Document> cursor = col.find().iterator();
        ArrayList<Product> products = new ArrayList<>();
        while (cursor.hasNext()) {
            Document product = cursor.next();
            products.add(new Product(product.get("_id").toString(),product.get("name").toString(),(int)product.get("price")
                    ,product.get("size").toString(),product.get("color").toString(),product.get("brand").toString()));
        }
        return products;
    }

    static public Product getProduct(String pId) {
        Document findProduct = new Document("_id",pId);
        MongoCursor<Document> cursor = col.find(findProduct).iterator();
        Product thisProduct = null;
        if(cursor.hasNext()){
            Document product = cursor.next();
            thisProduct = new Product(product.get("_id").toString(),product.get("name").toString(),(int)product.get("price")
                    ,product.get("size").toString(),product.get("color").toString(),product.get("brand").toString());
        }
        return thisProduct;
    }

    static public boolean addProduct(Product product){
        if(getProduct(product.getId()) == null){
            col.insertOne(new Document("_id",product.getId()).append("picture", product.getPicture()).append("name", product.getName())
                    .append("price", product.getPrice()).append("size", product.getSize()).append("color", product.getColor()).append("brand", product.getBrand()));
            return true;
        }else
            return false;
    }
}