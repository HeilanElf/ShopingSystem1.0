package codeSources;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    IsHeFa isHeFa=new IsHeFa();
    Home home=new Home();
    Scanner scanner=new Scanner(System.in);
    public void user(int command){

        while(true){
            if(command==0){
                break;
            }else{
                switch(command) {
                    case 1:
                        while(true){
                            home.showpassUser();
                            System.out.print("请输入您的选择：");
                            int command1=isHeFa.inthefa(2);
                            if(command1==0){
                                break;
                            }else{
                                userPassword(command1);
                            }
                        }
                        break;
                    case 2:
                        while(true){
                            home.showShop();
                            System.out.print("请输入您的选择：");
                            int command1=isHeFa.inthefa(5);
                            if(command1==0){
                                break;
                            }else{
                                userShop(command1);
                            }
                        }
                        break;
                }
            }
            home.showuserCommand();
            System.out.print("请输入您的选择：");
            command= isHeFa.inthefa(2);;
        }
    }
    public void userPassword(int command1){
        String signfilePath;
        signfilePath = "D:\\JavaProject\\ShopSystem\\src\\TxtUtils\\UserData.txt";
        ArrayList<String[]> userInfoList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(signfilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                userInfoList.add(userInfo);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("系统错误: " + e.getMessage());
        }
        if(command1==1){
            PassWordMaster passWordMaster=new PassWordMaster();
            System.out.print("请确定用户名:");
            String username=scanner.next();
            boolean find=false;
            for(String[] userInfo:userInfoList){
                if(userInfo[0].equals(username)){
                    System.out.print("请输入新密码：");
                    String newPassword=scanner.next();
                    passWordMaster.modifyUserInfo(signfilePath,userInfo[0],newPassword);
                    find=true;
                }
            }
            if(!find){
                System.out.print("该用户不存在！\n");
            }
        }else{
            PassWordMaster passWordMaster=new PassWordMaster();
            System.out.print("请确定用户名:");
            String username=scanner.next();
            boolean find=false;
            for(String[] userInfo:userInfoList){
                if(userInfo[0].equals(username)) {
                    passWordMaster.resetUserInfo(signfilePath, userInfoList.get(0)[0]);
                    find=true;
                }
            }
            if(!find){
                System.out.print("该用户不存在！\n");
            }
        }
    }
    public void userShop(int command){
            Shop shop=new Shop();
            Master master=new Master();
            home.showShop();
            String shopTrack="D:\\JavaProject\\ShopSystem\\src\\TxtUtils\\Shoptrack.txt";
            String History="D:\\JavaProject\\ShopSystem\\src\\TxtUtils\\Shophistory.txt";
            switch(command){
                case 1:
                    System.out.print("所有商品信息如下：");
                    master.ductionMaster(1);
                    System.out.print("请选择要加入购物车的商品编号：");
                    String id=scanner.next();
                    shop.addProductIn(shopTrack,id);
                    shop.showShopTrack(shopTrack);
                    break;
                case 2:
                    shop.showShopTrack(shopTrack);
                    System.out.print("请输入要删除商品的商品编号：");
                    String iddelete=scanner.next();
                    shop.deleteProductIn(shopTrack,iddelete);
                    shop.showShopTrack(shopTrack);
                    break;
                case 3:
                    shop.showShopTrack(shopTrack);
                    System.out.print("请输入要修改商品的商品编号：");
                    String idfix=scanner.next();
                    shop.fixProductIn(shopTrack,idfix);
                    shop.showShopTrack(shopTrack);
                    break;
                case 4:
                    shop.showShopTrack(shopTrack);
                    shop.buySimulate(shopTrack);
                    break;
                case 5:
                    shop.showBuyHistory(History);
                    break;
            }
    }
}

class Shop{
    Scanner scanner=new Scanner(System.in);
    public void addProductIn(String filePath,String ductName){
        String ductPath="D:\\JavaProject\\ShopSystem\\src\\TxtUtils\\DuctionData.txt";
        try{
            File file = new File(filePath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            BufferedReader reader=new BufferedReader(new FileReader(ductPath));
            String line;
            boolean find=false;
            while((line=reader.readLine())!=null){
                String[] ductInfo=line.split(",");
                if(ductInfo[0].equals(ductName)){
                    find=true;
                    writer.write(line);
                    writer.newLine();
                }
            }
            if(find){
                System.out.println("商品添加购物车成功！");
            }else{
                System.out.print("该商品不存在！");
            }
            reader.close();
            writer.close();
        }catch (IOException e){
            System.out.println("操作文件时发生错误：" + e.getMessage());
        }
    }
    public void showShopTrack(String filePath){
        try{
            BufferedReader reader=new BufferedReader(new FileReader(filePath));
            String line;
            System.out.print("当前购物车信息如下：\n");
            while((line=reader.readLine())!=null){
                String[] productFields=line.split(",");
                String formatString = "| %-5s | %-9s | %-8s | %-7s | %-23s | %-10s | %-5s|%n";
                System.out.format(formatString, productFields[0], productFields[1], productFields[2],productFields[3]
                        ,productFields[4],productFields[5],productFields[6]);
            }
            reader.close();
        }catch (IOException e){
            System.out.print("文件操作时发生错误："+e.getMessage());
        }
    }

    public void deleteProductIn(String filePath,String ductName){
        String tempFilePath="D:\\JavaProject\\ShopSystem\\src\\TxtUtils\\tempdata.txt";
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("文件不存在！");
                return;
            }
            // 创建临时文件以保存更新后的用户信息
            File tempFile = new File(tempFilePath);
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            // 逐行读取用户信息，将不需要删除的行写入临时文件
            boolean find=false;
            while ((line = reader.readLine()) != null) {
                String[] userInfo=line.split(",");
                if (!userInfo[0].equals(ductName)) {
                    writer.println(line);
                }else{
                    find=true;
                }
            }
            reader.close();
            writer.close();
            // 删除原文件并将临时文件重命名为原文件名
            if (file.delete()) {
                if (!tempFile.renameTo(file)) {
                    System.out.println("重命名临时文件失败！");
                } else {
                    if(!find){
                        System.out.println("该商品不存在，删除失败！");
                    }else{
                        System.out.println("商品信息删除成功！");
                    }
                }
            } else {
                System.out.println("删除原文件失败！");
            }
        } catch (IOException e) {
            System.out.println("操作文件时发生错误：" + e.getMessage());
        }
    }
    public void fixProductIn(String filePath,String ductName){
            DuctionMaster ductionMaster=new DuctionMaster();
            ductionMaster.fixDuction(filePath,ductName);
    }

    public double gettotalprice(String filePath){
        double totalprice=0;
        try{

            BufferedReader reader=new BufferedReader(new FileReader(filePath));
            String line;
            while((line=reader.readLine())!=null){
                String[] shoping=line.split(",");
                String numberprice=shoping[2].replaceAll("[^\\d.]+", "");
                double temp=Double.parseDouble(numberprice);
                totalprice+=temp;
            }
            reader.close();

        }catch (IOException e){
            System.out.print("文件操作发生错误："+e.getMessage());
        }
        return totalprice;
    }

    public void dropShopTrack(String filePath){
//  清除filePath的文件内容
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("清除文件内容失败：" + e.getMessage());
        }
    }
    public void saveHistory(String filePath){
//将filePath的文件信息保存在shopHistory中
        String shopHistoryFile = "D:\\JavaProject\\ShopSystem\\src\\TxtUtils\\Shophistory.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(shopHistoryFile, true))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("保存历史记录失败：" + e.getMessage());
        }
    }
    public void buySimulate(String filePath){
            double total=gettotalprice(filePath);
            System.out.println("当前商品你共需支付:" +total+ "￥");
            System.out.println("是否支付？(Yes/No):");
            String xuanze=scanner.next();
            if(xuanze.equals("Yes")){
                saveHistory(filePath);
                dropShopTrack(filePath);
            }else{
                System.out.println("支付失败！");
            }
    }
    public void showBuyHistory(String filePath){
        try{
            BufferedReader reader=new BufferedReader(new FileReader(filePath));
            String line;
            System.out.print("历史购物信息如下：\n");
            while((line=reader.readLine())!=null){
                String[] productFields=line.split(",");
                String formatString = "| %-5s | %-9s | %-8s | %-7s | %-23s | %-10s | %-5s|%n";
                System.out.format(formatString, productFields[0], productFields[1], productFields[2],productFields[3]
                        ,productFields[4],productFields[5],productFields[6]);
            }
            reader.close();
        }catch (IOException e){
            System.out.print("文件操作时发生错误："+e.getMessage());
        }
    }
}
