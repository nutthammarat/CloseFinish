import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.*;

public class UserDao {
    static MongoClientURI uri = new MongoClientURI("mongodb://admin:password1@ds249503.mlab.com:49503/finalproject");
    static MongoClient client = new MongoClient(uri);
    static MongoDatabase db = client.getDatabase(uri.getDatabase());
    static MongoCollection<Document> col = db.getCollection("user");
    
    static public User getUser(String username) {
        Document findUser = new Document("_id",username);
        MongoCursor<Document> cursor = col.find(findUser).iterator();
        User thisUser = null;
        if(cursor.hasNext()){
            Document user = cursor.next();
            thisUser = new User(user.get("_id").toString(),user.get("password").toString(),user.get("phone").toString(),user.get("address").toString());
        }
        return thisUser;
    }

    static public boolean addUser(User user) {
        if(getUser(user.getUsername()) == null) {
            col.insertOne(new Document("_id",user.getUsername()).append("password",user.getPassword()).append("phone",user.getPhone()).append("address",user.getAddress()));
            return true;
        }else {
            return false;
        }
    }

    static public boolean updatePhone(User user){
        Document findUser = new Document("_id",user.getUsername());
        MongoCursor<Document> cursor = col.find(findUser).iterator();
        if(cursor.hasNext()){
            col.updateOne(findUser,new Document("$set",new Document("phone",user.getPhone())));
            return true;
        }
        return false;
    }

    static public boolean updateAddress(User user){
        Document findUser = new Document("_id",user.getUsername());
        MongoCursor<Document> cursor = col.find(findUser).iterator();
        if(cursor.hasNext()){
            col.updateOne(findUser,new Document("$set",new Document("address",user.getAddress())));
            return true;
        }
        return false;
    }
}