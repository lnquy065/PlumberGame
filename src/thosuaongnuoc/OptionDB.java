package thosuaongnuoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class OptionDB {
    private static int volE;
    private static int volS;
    private static boolean tip;
    private static boolean console;
    
    public static void setInfo(int vE,int vS,boolean t, boolean c) {
        volE=vE;
        volS=vS;
        tip=t;
        console=c;
    }

    public static int getVolE() {
        return volE;
    }

    public static int getVolS() {
        return volS;
    }

    public static boolean getTip() {
        return tip;
    }

    public static boolean getsConsole() {
        return console;
    }
    
    public static void setCon(boolean t) {
        console=t;
    }
    
    public static void writeToFile() {
        String path = "src\\map\\"+"option.txt";
        try (FileWriter fw = new FileWriter(path) ) {
            fw.write(volE+"\r\n");
            fw.write(volS+"\r\n");
            fw.write(tip+"\r\n");
            fw.write(console+"");
        } catch (IOException e) {};
    }
    public static void loadFromFile() {
        String path = "src\\map\\"+"option.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            volE=Integer.parseInt(br.readLine());
            volS=Integer.parseInt(br.readLine());
            tip=Boolean.valueOf(br.readLine());
            console=Boolean.valueOf(br.readLine());
        } catch (IOException e) {}
    }
}
