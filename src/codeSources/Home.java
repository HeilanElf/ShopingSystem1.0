package codeSources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Home {
    public void clear(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showHome(){
        clear();
        String show1="showHome.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(show1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showUser(){
        clear();
        String show1="user.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(show1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMaster(){
        clear();
        String show1="master.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(show1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showpassMaster(){
        clear();
        String show1="passMaster.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(show1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showuserMaster(){
        clear();
        String show1="userMaster.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(show1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDuctionMaster(){
        clear();
        String show1="ductionMaster.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(show1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showsignorresign(){
        clear();
        String show1="sign.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(show1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showaddStyle(){
        clear();
        String show1="addStle.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(show1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showContent(){
        clear();
        String show1="Content.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(show1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showpassUser(){
        clear();
        String show1="passUser.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(show1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showShop(){
        clear();
        String show1="Shop.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(show1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showuserCommand(){
        clear();
        String show1="userCommand.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(show1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showUserSearchStyle(){
        clear();
        String show1="userSearchStyle.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(show1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}