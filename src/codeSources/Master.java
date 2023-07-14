package codeSources;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Master {
    IsHeFa isHeFa=new IsHeFa();
    Home home=new Home();
    PassWordMaster passWordMaster=new PassWordMaster();
    Scanner scanner=new Scanner(System.in);
    public void passwordMaster(int command1){
        String signfilePath;
        if(command1==1){
            signfilePath = "D:\\JavaProject\\ShopSystem\\src\\TxtUtils\\Manager.txt";
        }else{
            signfilePath = "D:\\JavaProject\\ShopSystem\\src\\TxtUtils\\UserData.txt";
        }
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
           String username=scanner.nextLine();
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
               System.out.print("该管理员不存在！请联系主管添加。\n");
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
    public void userMaster(int command){
        UserMaster userMaster=new UserMaster();
        String filePath="D:\\JavaProject\\ShopSystem\\src\\TxtUtils\\UserData.txt";
        if(command==1){
            userMaster.showUserInformation(filePath);
        }else if(command==2){
            System.out.print("请输入删除用户名：");
            String username=scanner.next();
            userMaster.deleteUser(filePath,username);
        }else{
            home.showUserSearchStyle();
            System.out.print("请选择查询方式：");
            int fangshi=isHeFa.inthefa(3);
            while(true){
                if(fangshi==0){
                    break;
                }else{
                    switch(fangshi){
                        case 1:
                            System.out.print("请输入用户ID：");
                            String userID=scanner.next();
                            userMaster.searchUser(filePath,userID);
                            break;
                        case 2:
                            System.out.print("请输入用户名：");
                            String username=scanner.next();
                            userMaster.searchUser(filePath,username);
                            break;
                        case 3:
                            userMaster.searchUser(filePath,"*");
                            break;
                    }
                    home.showUserSearchStyle();
                    System.out.print("请选择查询方式：");
                    fangshi=isHeFa.inthefa(3);
                }
            }

        }
    }
    public void ductionMaster(int command){
        DuctionMaster ductionMaster=new DuctionMaster();
        home.showDuctionMaster();
        String filePath="D:\\JavaProject\\ShopSystem\\src\\TxtUtils\\DuctionData.txt";
        switch(command){
            case 1:
                ductionMaster.showDuction(filePath);
                break;
            case 2:
                ductionMaster.addDuction(filePath);
                break;
            case 3:
                System.out.print("请输入修改商品名：");
                String fixname=scanner.next();
                ductionMaster.fixDuction(filePath,fixname);
                break;
            case 4:
                System.out.print("请输入删除商品名：");
                String deletename=scanner.next();
                ductionMaster.deleteDuction(filePath,deletename);
                break;
            case 5:
                System.out.print("请输入查询商品名：");
                String searchname=scanner.next();
                ductionMaster.searchDuction(filePath,searchname);
                break;
        }
    }

    public void master(int command){
        Scanner scanner=new Scanner(System.in);
        while(true){
            if(command==0){
                break;
            }else{
                switch(command) {
                    case 1:
                        while(true){
                            home.showpassMaster();
                            System.out.print("请输入您的选择：");
                            int command1=isHeFa.inthefa(2);
                            if(command1==0){
                                break;
                            }else{
                                passwordMaster(command1);
                            }
                        }
                        break;
                    case 2:
                        while(true){
                            home.showuserMaster();
                            System.out.print("请输入您的选择：");
                            int command1=isHeFa.inthefa(3);
                            if(command1==0){
                                break;
                            }else{
                                userMaster(command1);
                            }
                        }
                        break;
                    case 3:
                        while(true){
                            home.showDuctionMaster();
                            System.out.print("请输入您的选择：");
                            int command1=isHeFa.inthefa(5);
                            if(command1==0){
                                break;
                            }else{
                                ductionMaster(command1);;
                            }
                        }
                        break;
                }
            }
            home.showMaster();
            System.out.print("请输入您的选择：");
            command= isHeFa.inthefa(3);
        }
    }

}

class PassWordMaster{
    public  void modifyUserInfo(String filePath, String username, String newPassword) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder fileContentBuilder = new StringBuilder();
            String line;
            // 读取文件并修改对应的用户信息
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                String fileUsername = userInfo[0];
                if (fileUsername.equals(username)) {
                    StringBuilder modifiedUserInfoBuilder = new StringBuilder();
                    modifiedUserInfoBuilder.append(username).append(",").append(newPassword);
                    line = modifiedUserInfoBuilder.toString();
                }
                fileContentBuilder.append(line).append(System.lineSeparator());
            }
            reader.close();
            // 将修改后的内容保存回文本文件
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(fileContentBuilder.toString());
            writer.close();
            System.out.println("修改成功！");
        } catch (IOException e) {
            System.out.println("修改失败: " + e.getMessage());
        }
    }

    // 清空用户信息，将对应字段清空并保存到文本文件中
    public  void resetUserInfo(String filePath, String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder fileContentBuilder = new StringBuilder();
            String line;

            // 读取文件并清空对应的用户字段
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                String fileUsername = userInfo[0];

                if (fileUsername.equals(username)) {
                    line = fileUsername + ",,";
                }

                fileContentBuilder.append(line).append(System.lineSeparator());
            }

            reader.close();

            // 将清空后的内容保存回文本文件
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(fileContentBuilder.toString());
            writer.close();

            System.out.println("重置成功！");
        } catch (IOException e) {
            System.out.println("重置失败: " + e.getMessage());
        }
    }
}
class UserMaster{
    public void showUserInformation(String filePath){
//            显示客户信息
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(","); // 假设字段之间使用逗号分隔
                for (String field : userInfo) {
                    System.out.printf("%-10s", field); // 左对齐显示，并设置字段宽度为 20
                }
                System.out.println();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("读取文件时发生错误：" + e.getMessage());
        }
    }
    public void deleteUser(String filePath,String username){
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
                if (!userInfo[0].equals(username)) {
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
                        System.out.println("该用户不存在，删除失败！");
                    }else{
                        System.out.println("用户信息删除成功！");
                    }
                }
            } else {
                System.out.println("删除原文件失败！");
            }
        } catch (IOException e) {
            System.out.println("操作文件时发生错误：" + e.getMessage());
        }
    }
    public void searchUser(String filePath,String searchType){
        int type=0;
        if(searchType.equals("userID")){
            type=0;
        }else if(searchType.equals("username")){
            type=1;
        }
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("文件不存在！");
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            boolean find=false;
            while ((line = reader.readLine()) != null) {
                String[] userInfo=line.split(",");
                if(userInfo[type].equals(searchType)){
                    System.out.println("用户信息如下：");
                    System.out.println(line);
                    find=true;
                }
                if(searchType.equals("*")){
                    System.out.println("用户信息如下：");
                    System.out.println(line);
                    find=true;
                }
            }
            if(!find){
                System.out.println("该用户不存在！");
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("读取文件时发生错误：" + e.getMessage());
        }
    }
}
class DuctionMaster{
    IsHeFa isHeFa=new IsHeFa();
    public void showDuction(String filePath){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            if ((line = reader.readLine()) != null) {
                String[] productFields = line.split(",");
                String formatString = "| %-5s | %-8s | %-7s | %-5s | %-20s | %-10s | %-5s|%n";
                System.out.format(formatString, productFields[0], productFields[1], productFields[2],productFields[3]
                ,productFields[4],productFields[5],productFields[6]);
            }
            while ((line = reader.readLine()) != null) {
                String[] productInfo = line.split(",");
                String name = productInfo[0];
                String description = productInfo[1];
                String price = productInfo[2];
                String brand = productInfo[3];
                String discription = productInfo[4];
                String data = productInfo[5];
                String stock = productInfo[6];
                String formatString = "| %-5s | %-8s | %-7s | %-5s | %-20s | %-10s | %-5s|%n";
                System.out.format(formatString, name, description, price, brand, discription, data, stock);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("读取文件时发生错误：" + e.getMessage());
        }
    }
    public void addDuction(String filePath) {
        Home home=new Home();
        try {
            File file = new File(filePath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            Scanner scanner = new Scanner(System.in);
            boolean continueAdding = true;

            // 判断是否是第一次添加商品
            boolean isFirstProduct = !file.exists() || file.length() == 0;

            if (isFirstProduct) {
                String productFields ="id,name,price,brand,discription,data,stock";
                writer.write(productFields);
                writer.newLine();
            }

            while (continueAdding) {
                home.showaddStyle();
                System.out.print("请输入您的选择：");
                int choice = isHeFa.inthefa(2);
                switch (choice) {
                    case 1:
                        addSingleProduct(writer, scanner);
                        break;
                    case 2:
                        addBatchProducts(writer, scanner);
                        break;
                    case 0:
                        continueAdding = false;
                        break;
                    default:
                        System.out.println("无效的选择，请重新输入！");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("操作文件时发生错误：" + e.getMessage());
        }
    }
    public String productInfo(Scanner scanner){
        System.out.println("请输入商品信息（逐行输入）：");
        System.out.print("编号：");
        String id = scanner.next();
        System.out.print("名称：");
        String name = scanner.next();
        System.out.print("价格：");
        String price = scanner.next();
        System.out.print("品牌：");
        String brand = scanner.next();
        System.out.print("描述：");
        String description = scanner.next();
        System.out.print("上架日期：");
        String data = scanner.next();
        System.out.print("库存：");
        String stock = scanner.next();
        String productInfo = id+","+name+ "," + price + "," + brand+ "," + description+ "," + data+","+stock ;
        return productInfo;
    }
    private void addSingleProduct(BufferedWriter writer, Scanner scanner) throws IOException {
        String productInfo=productInfo(scanner);
        writer.write(productInfo);
        writer.newLine();
        System.out.println("商品添加成功！");
    }

    private void addBatchProducts(BufferedWriter writer, Scanner scanner) throws IOException {
        System.out.print("请输入要批量添加的商品数量：");
        int count = isHeFa.inthefa(10);;

        if (count <= 0 || count > 10) {
            System.out.println("非法的数量！每次批量添加的商品数量应在1到10之间。");
            return;
        }

        System.out.println("请依次输入每个商品的信息（编号，名称，价格，品牌，描述，上架日期，库存）：");
        for (int i = 0; i < count; i++) {
            System.out.print("商品 " + (i + 1) + ":");
            String id = scanner.next();
            String name = scanner.next();
            String price = scanner.next();
            String brand = scanner.next();
            String description = scanner.next();
            String data = scanner.next();
            String stock = scanner.next();
            String productInfo = id+","+name+ "," + price + "," + brand+ "," + description+ "," + data+","+stock ;
            writer.write(productInfo);
            writer.newLine();
        }
        System.out.println("批量商品添加成功！");

    }

    public void fixDuction(String filePath, String productName) {
        Home home=new Home();
        Scanner scanner = new Scanner(System.in);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder fileContentBuilder = new StringBuilder();
            String line;
            // 读取文件并修改对应的商品信息
            while ((line = reader.readLine()) != null) {
                String[] productInfo = line.split(",");
                if (productInfo[1].equals(productName)) {
                    StringBuilder modifiedProductInfoBuilder = new StringBuilder();

                    System.out.println("当前可修改商品信息如下：");
                    String formatString = "| %-5s | %-9s | %-8s | %-7s | %-23s | %-10s | %-5s|%n";
                    System.out.format(formatString, productInfo[0], productInfo[1], productInfo[2],productInfo[3]
                            ,productInfo[4],productInfo[5],productInfo[6]);

                    home.showContent();
                    System.out.print("请输入您的选择：");
                    int choice = isHeFa.inthefa(7);;
                    scanner.nextLine(); // 读取换行符
                    switch (choice) {
                        case 1:
                            System.out.print("请输入新的编号：");
                            String id = scanner.nextLine();
                            modifiedProductInfoBuilder.append(id).append(",").append(productInfo[1]).append(",").append(productInfo[2])
                                    .append(",").append(productInfo[3]).append(",").append(productInfo[4]).append(",").append(productInfo[5]).append(",").append(productInfo[6]);
                            break;
                        case 2:
                            System.out.print("请输入新的名称：");
                            String name = scanner.nextLine();
                            modifiedProductInfoBuilder.append(productInfo[0]).append(",").append(name).append(",").append(productInfo[2])
                                    .append(",").append(productInfo[3]).append(",").append(productInfo[4]).append(",").append(productInfo[5]).append(",").append(productInfo[6]);
                            break;
                        case 3:
                            System.out.print("请输入新的价格：");
                            String price = scanner.nextLine();
                            modifiedProductInfoBuilder.append(productInfo[0]).append(",").append(productInfo[1]).append(",").append(price)
                                    .append(",").append(productInfo[3]).append(",").append(productInfo[4]).append(",").append(productInfo[5]).append(",").append(productInfo[6]);
                            break;
                        case 4:
                            System.out.print("请输入新的品牌：");
                            String brand = scanner.nextLine();
                            modifiedProductInfoBuilder.append(productInfo[0]).append(",").append(productInfo[1]).append(",").append(productInfo[2])
                                    .append(",").append(brand).append(",").append(productInfo[4]).append(",").append(productInfo[5]).append(",").append(productInfo[6]);
                            break;
                        case 5:
                            System.out.print("请输入新的描述：");
                            String description = scanner.nextLine();
                            modifiedProductInfoBuilder.append(productInfo[0]).append(",").append(productInfo[1]).append(",").append(productInfo[2])
                                    .append(",").append(productInfo[3]).append(",").append(description).append(",").append(productInfo[5]).append(",").append(productInfo[6]);
                            break;
                        case 6:
                            System.out.print("请输入新的上架日期：");
                            String date = scanner.nextLine();
                            modifiedProductInfoBuilder.append(productInfo[0]).append(",").append(productInfo[1]).append(",").append(productInfo[2])
                                    .append(",").append(productInfo[3]).append(",").append(productInfo[4]).append(",").append(date).append(",").append(productInfo[6]);
                            break;
                        case 7:
                            System.out.print("请输入新的库存量：");
                            String stock = scanner.nextLine();
                            modifiedProductInfoBuilder.append(productInfo[0]).append(",").append(productInfo[1]).append(",").append(productInfo[2])
                                    .append(",").append(productInfo[3]).append(",").append(productInfo[4]).append(",").append(productInfo[5]).append(",").append(stock);
                            break;
                        default:
                            System.out.println("无效的选择！");
                    }
                    line = modifiedProductInfoBuilder.toString();
                }
                fileContentBuilder.append(line).append(System.lineSeparator());
            }
            reader.close();

            // 将修改后的内容保存回文本文件
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(fileContentBuilder.toString());
            writer.close();
            System.out.println("修改成功！");
        } catch (IOException e) {
            System.out.println("修改失败: " + e.getMessage());
        }
    }
    public void deleteDuction(String filePath,String ductionname){
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
                if (!userInfo[1].equals(ductionname)) {
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
    public void searchDuction(String filePath,String ductionname){
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("文件不存在！");
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            boolean find=false;
            while ((line = reader.readLine()) != null) {
                String[] userInfo=line.split(",");
                if(userInfo[1].equals(ductionname)){
                    System.out.println("商品信息如下：");
                    String formatString = "| %-5s | %-9s | %-8s | %-7s | %-23s | %-10s | %-5s|%n";
                    System.out.format(formatString, userInfo[0], userInfo[1], userInfo[2],userInfo[3]
                            ,userInfo[4],userInfo[5],userInfo[6]);
                    find=true;
                }
            }
            if(!find){
                System.out.println("该商品不存在！");
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("读取文件时发生错误：" + e.getMessage());
        }
    }
}
