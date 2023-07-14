package codeSources;

import java.io.*;

public class InitalFile {
    String ductData="DuctionData.txt";
    String shiyongTag="shiningTag.txt";
    String Manager="Manager.txt";
    String Shophistory="Shophistory.txt";
    String Shoptrack="Shoptrack.txt";
    String UserData="UserData.txt";
    public void init(){
        delete(ductData);
//        delete(Manager);
        delete(Shophistory);
        delete(Shoptrack);
        delete(UserData);
    }
    public void delete(String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("清除文件内容失败：" + e.getMessage());
        }
    }

    public boolean getTag(){
        boolean tag=false;
        try{

           BufferedReader reader=new BufferedReader(new FileReader(shiyongTag));
           String line;
           while((line=reader.readLine())!=null){
               String[] tagInfo=line.split(",");
               if(tagInfo[0].equals("1")){
                   tag=true;
               }
           }
           reader.close();
       }catch (IOException e){
           System.out.print("操作文件时，发生错误："+e.getMessage());
       }
       return tag;
    }
}
