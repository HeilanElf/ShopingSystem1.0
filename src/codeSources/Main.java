package codeSources;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        InitalFile init=new InitalFile();
        IsHeFa isHeFa=new IsHeFa();
        Home home=new Home();
        SignIn signIn=new SignIn();
        Master master=new Master();
        User user=new User();
        boolean tag=init.getTag();
        if(tag){
            System.out.println("请在为您初始化系统，请等待！");
            init.init();
            tag=false;
            System.out.println("初始化完成，欢迎使用商品管理员系统");
        }
        home.showHome();
        System.out.print("请输入您的选择：");
        int command=isHeFa.inthefa(2);
        while(true){
            if(command==0){
                break;
            }
            else{
                switch(command){
                    case 1:
                        int count1=signIn.userSign(command);
                        if(count1<3){
                            home.showMaster();
                            System.out.print("请输入您的选择：");
                            command=isHeFa.inthefa(3);
                            master.master(command);
                        }
                        break;
                    case 2:
                        home.showsignorresign();
                        System.out.print("请输入您的选择：");
                        int sign= isHeFa.inthefa(2);
                        if(sign==2){
                            signIn.sign(sign);
                            System.out.print("是否登录？(Yes/No):");
                            while(true){
                                String action=scanner.nextLine();
                                if(action.equals("No")){
                                    break;
                                }else if(action.equals("Yes")){
                                    int count=signIn.sign(1);
                                    if(count<3){
                                        home.showuserCommand();
                                        System.out.print("请输入您的选择：");
                                        command=isHeFa.inthefa(3);
                                        user.user(command);
                                    }
                                    break;
                                }
                            }
                        }else{
                            int count=signIn.sign(1);
                            if(count<3){
                                home.showuserCommand();
                                System.out.print("请输入您的选择：");
                                command=isHeFa.inthefa(3);;
                                user.user(command);
                            }
                            break;
                        }
                        break;
                }
            }
            home.showHome();
            System.out.print("请输入您的选择：");
            command=isHeFa.inthefa(2);;
        }

    }
}