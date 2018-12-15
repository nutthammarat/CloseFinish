
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ProductDetailService {

    static String usercurrent;
    static String blue = "น้ำเงิน";
    static String gray = "เทา";
    static String red = "แดง";
    static String brown = "น้ำตาล";
    static String pink = "ชมพู";
    static String black = "ดำ";
    static String S01 = "E:\\ProJectFinal-master\\src\\org\\me\\myimageapp\\resources\\chelsea-shirt.png";
    static String S02 = "E:\\ProJectFinal-master\\src\\org\\me\\myimageapp\\resources\\sweatershirt.png";
    static String S03 = "E:\\ProJectFinal-master\\src\\org\\me\\myimageapp\\resources\\pajamas.png";
    static String S04 = "E:\\ProJectFinal-master\\src\\org\\me\\myimageapp\\resources\\hawaii-shirt.png";
    static String S05 = "E:\\ProJectFinal-master\\src\\org\\me\\myimageapp\\resources\\supernova.png";
    static String S06 = "E:\\ProJectFinal-master\\src\\org\\me\\myimageapp\\resources\\adidas_zne.png";

    public static void showProductDetail() {
        ProductDetailFrame productdetail = new ProductDetailFrame();
        productdetail.setVisible(true);
    }

    public static Product getProduct(String pId) {
        return ProductDao.getProduct(pId);
    }

    public static String changeEngtoThaiColor(String color) {
        if (color.equals("blue")) {
            return blue;
        } else if (color.equals("gray")) {
            return gray;
        } else if (color.equals("red")) {
            return red;
        } else if (color.equals("brown")) {
            return brown;
        } else if (color.equals("pink")) {
            return pink;
        }
        return black;
    }

    public static void getDetailProduct(Product pid) {
       
       String setcolor = changeEngtoThaiColor(pid.getColor());
       getPicture(pid);
       ProductDetailFrame.nameproduct.setText(pid.getName().toUpperCase());
       ProductDetailFrame.bandtxt.setText(pid.getBrand());
       ProductDetailFrame.colortxt.setText(setcolor);
       ProductDetailFrame.sizetxt.setText(pid.getColor());
       ProductDetailFrame.pricetxt.setText(""+pid.getPrice());
        

    }

   

    public static void getPicture(Product pid) {
        if (pid.getId().equals("S01")) {
            ImageIcon icon = new ImageIcon(S01);
            ProductDetailFrame.pic1.setIcon(icon);
            ProductDetailFrame.pIdtxt.setText("S01");
        } else if (pid.getId().equals("S02")) {
            ImageIcon icon = new ImageIcon(S02);
            ProductDetailFrame.pic1.setIcon(icon);
            ProductDetailFrame.pIdtxt.setText("S02");
        } else if (pid.getId().equals("S03")) {
            ImageIcon icon = new ImageIcon(S03);
            ProductDetailFrame.pic1.setIcon(icon);
            ProductDetailFrame.pIdtxt.setText("S03");
        } else if (pid.getId().equals("S04")) {
            ImageIcon icon = new ImageIcon(S04);
            ProductDetailFrame.pic1.setIcon(icon);
            ProductDetailFrame.pIdtxt.setText("S04");
        } else if (pid.getId().equals("S05")) {
            ImageIcon icon = new ImageIcon(S05);
            ProductDetailFrame.pic1.setIcon(icon);
            ProductDetailFrame.pIdtxt.setText("S05");
        } else if (pid.getId().equals("S06")) {
            ImageIcon icon = new ImageIcon(S06);
            ProductDetailFrame.pic1.setIcon(icon);
            ProductDetailFrame.pIdtxt.setText("S06");
        }
    }

    public static void increaseNumofProduct() {
        int index = Integer.parseInt(ProductDetailFrame.numtxt.getText());
        index = index + 1;
        ProductDetailFrame.numtxt.setText("" + index);
    }

    public static void decreaseNumofProduct() {
        int index = Integer.parseInt(ProductDetailFrame.numtxt.getText());
        index = index - 1;
        if (index > 0) {
            ProductDetailFrame.numtxt.setText("" + index);
        }

    }
    
    public static void showComfirmProductToCart() {
        JOptionPane.showMessageDialog(null, "คุณได้ทำการเพิ่มสินค้าไปยังตะกร้าเรียบร้อยแล้ว");
        int index = JOptionPane.showConfirmDialog(null,
                "คุณต้องการไปยังหน้าตะกร้าสินค้าเลยหรือไม่ ??", "Go to Cart or Not?",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        showCartPage(index);
    }

    public static void showCartPage(int index) {
        if (index == 0) {
            CartService.showCart();
            CartService.showList();
        }

    }

//    public static String addCartToDB() {
//        System.out.println(ProductDetailFrame.nameproduct.getText() + " "
//                + ProductDetailFrame.bandtxt.getText() + " "
//                + ProductDetailFrame.colortxt.getText() + " "
//                + ProductDetailFrame.sizetxt.getText() + " "
//                + ProductDetailFrame.pricetxt.getText() + " "
//                + ProductDetailFrame.numtxt.getText() + " ");
//        return "";
//    }

    public static void addProductToCart() {
        usercurrent = LoginForm.userCurrent;
        User user = UserService.getUser(usercurrent );
        Product product = ProductService.getProduct(ProductDetailFrame.pIdtxt.getText());
        int num = Integer.parseInt(ProductDetailFrame.numtxt.getText());
//        System.out.println(user + " " + product + " " + num);
        CartService.addToCart(user, product, num);

    }

}
