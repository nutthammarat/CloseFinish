public class Cart {
    private User user;
    private Product product;
    private int num;

    Cart(User user, Product product, int num) {
        this.user = user;
        this.product = product;
        this.num = num;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    public String toString(){
        return product.getName() + "       " + product.getBrand() + "        "+ product.getColor()+
                "      " + product.getPrice() ;
    }
}