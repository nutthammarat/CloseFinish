/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Choppy
 */
import java.util.*;
import javax.swing.JOptionPane;
public class LoginService {
   
   
    public static boolean checkUsername(User user){
        User thisUser = null;
        thisUser = UserService.getUser(user.getUsername());
        if(thisUser == null)
            return false;
        else
            return  true;
    }
    
    public static boolean checkPassword(User user){
        User thisUser = null;
        thisUser = UserService.getUser(user.getUsername());
        if(thisUser.getPassword().equals(user.getPassword()))
            return true;
        else
            return false;
    }
    
    public static boolean goToMain(User user){
        MainPage main = new MainPage();
        if(checkUsername(user)){
             if(checkPassword(user)){
                main.setVisible(true); 
                //ProfileService.getDetailUser(user);
                JOptionPane.showMessageDialog(null,
                                            "ยินดีต้อนรับ อย่าลืมไปกรอกข้อมูลส่วนตัวก่อนสั่งสินค้า",
                                            "Message",
                                            JOptionPane.PLAIN_MESSAGE);
                //MainPage.userIdtxt.setText(""+user.getUsername());
                return true;
                
             }else{
                 JOptionPane.showMessageDialog(null,"กรุณากรอก passwordให้ถูกต้อง","แจ้งเตือน",0);
                 LoginForm login = new LoginForm();
                 login.setVisible(true);
                 return false;
             }
        }else{
            JOptionPane.showMessageDialog(null,"กรุณากรอก usernameให้ถูกต้อง","แจ้งเตือน",0);
            LoginForm login = new LoginForm();
            login.setVisible(true);
            return false;
        }
            
    }
    
    public static void goTORegister(){
        RegisterForm register = new RegisterForm();
        register.setVisible(true);
    }
}