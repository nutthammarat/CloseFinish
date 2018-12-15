
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Choppy
 */
public class RegisterService {
   
    public static boolean addUser(String username, String password){
        User user = new User(username,password);
        if(UserDao.addUser(user))
            return true;
        else
            return false;
        //return true;
    }
    public static boolean checkLengthUser(String username){
           if(username.length()>4){
               return true;
           }else{
               return false;
           }
    }
    
    public static boolean checkLengthPass(String password){
            if(password.length()>5){
                return true;
            }else{
                return false;
            }
    }
    
    public static boolean checkPasswordComfirm(String password,String passcomfirm){
            if(password.equals(passcomfirm)){
                return true;
            }else{
                return false;
            }
    }
    
    public static void goTOLogin(){
        LoginForm login = new LoginForm();
        login.setVisible(true);
    }
    public static void addDataUser(String username,String password,String passcomfirm){
        if(RegisterService.addUser(username, password)==true &&
           RegisterService.checkLengthPass(password)==true &&
           RegisterService.checkLengthUser(username)==true &&
           RegisterService.checkPasswordComfirm(password, passcomfirm)==true){
             JOptionPane.showMessageDialog(null,
                                            "สมัครสมาชิคเรียบร้อย",
                                            "Register message",
                                            JOptionPane.PLAIN_MESSAGE);
             RegisterService.goTOLogin();
        }else{
            JOptionPane.showMessageDialog(null,"กรุณากรอก username , password ,passwordcomfirm ให้ถูกต้อง และpassกรอกห้ามน้อยกว่า5ตัว ","แจ้งเตือน",0);
            RegisterForm register = new RegisterForm();
            register.setVisible(true);
        }
    }
}