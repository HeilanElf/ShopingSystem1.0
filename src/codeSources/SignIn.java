package codeSources;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import  java.util.Scanner;
import java.io.*;
public class SignIn {
    String filePath;

    Scanner scanner=new Scanner(System.in);
    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder encryptedPassword = new StringBuilder();
            for (byte b : hash) {
                encryptedPassword.append(String.format("%02x", b));
            }
            return encryptedPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密算法不可用", e);
        }
    }

    public static void registerUser(String filePath, String username, String password) {
        // 对密码进行加密
        String encryptedPassword = encryptPassword(password);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            StringBuilder userInfoBuilder = new StringBuilder();
            userInfoBuilder.append(username).append(",").append(encryptedPassword);
            String userInfo = userInfoBuilder.toString();

            writer.write(userInfo);
            writer.newLine();
            writer.close();
            System.out.println("注册成功！");
        } catch (IOException e) {
            System.out.println("注册失败: " + e.getMessage());
        }
    }
    public boolean isMatch(String filePath,String name,String password){
        String encryptedPassword = encryptPassword(password);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                if(userInfo.length==1){
                        System.out.println("账户密码被管理员(主管）重置过！");
                        System.out.print("请确认密码：");
                        String newpass=scanner.nextLine();
                        registerUser(filePath,name,newpass);
                        return true;
                }else{
                    if(name.equals(userInfo[0])&&encryptedPassword.equals(userInfo[1])){
                        return true;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("系统错误: " + e.getMessage());
        }
        return false;
    }
    public boolean userExit(String filePath,String userName){
        try{
            BufferedReader reader=new BufferedReader(new FileReader(filePath));
            String line;
            while((line=reader.readLine())!=null){
                String[] userInfo=line.split(",");
                if(userInfo[0].equals(userName)){
                    return true;
                }
            }
            reader.close();
        }catch(IOException e){
            System.out.println("系统错误: " + e.getMessage());
        }
        return false;
    }
    public int userSign(int commond){
        int count = 0;
        System.out.print("用户名：");
        String userName=scanner.nextLine();
        System.out.print("密码：");
        String password=scanner.nextLine();
        if(commond==1){
            filePath = "D:\\JavaProject\\ShopSystem\\src\\TxtUtils\\Manager.txt";
        }else if(commond==2){
            filePath= "D:\\JavaProject\\ShopSystem\\src\\TxtUtils\\UserData.txt";
        }
        boolean find=userExit(filePath,userName);
        if(!find){
            System.out.println("用户不存在！是否自动注册？Yes/No");
            String xuanze=scanner.nextLine();
            if(xuanze.equals("Yes")){
                registerUser(filePath,userName,password);
            }
            else{
                System.out.println("登录失败！");
                return 10;
            }
        }else{
            while(!isMatch(filePath,userName,password)&&count<3){
                count++;
                System.out.println("用户名或密码错误！");
                System.out.print("用户名：");
                userName=scanner.nextLine();
                System.out.print("密码：");
                password=scanner.nextLine();
            }
            if(count>=3){
                System.out.println("由于您多次失败，请稍后再试！否则您的账户将被冻结");
            }else{
                System.out.println("登录成功！");
            }
        }
        return count;
    }

    public int sign(int commond){
        int count=0;
        if(commond==1){
            filePath = "D:\\JavaProject\\ShopSystem\\src\\TxtUtils\\Manager.txt";
        }else if(commond==2){
            filePath= "D:\\JavaProject\\ShopSystem\\src\\TxtUtils\\UserData.txt";
        }
        if(commond==2){
                System.out.println("欢迎成为商品管理系统新用户！");
                System.out.print("请输入您用户名：");
                String userName=scanner.nextLine();
                System.out.print("请输入您的密码：");
                String password=scanner.nextLine();
                registerUser(filePath,userName,password);
        } else{
            count=userSign(2);
        }
        return count;

    }
}
