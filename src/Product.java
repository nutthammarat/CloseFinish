public class Product {
    private String id;
    private String picture;
    private String name;
    private int price;
    private String size;
    private String color;
    private String brand;
    private byte image;
    //private int num;

    Product(String id,String name, int price, String size, String color, String brand){
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.brand = brand;
        //this.picture = picture;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String toString(){
        return getId() + " " +getName() + " " + getPrice() + " " + getBrand() + " " + getColor() + " " + getSize();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}