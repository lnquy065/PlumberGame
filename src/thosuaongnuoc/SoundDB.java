package thosuaongnuoc;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;


public class SoundDB {
    private static String path = "src\\sound\\";
    private static Map<String, Clip> envir = new HashMap<String, Clip>();
    private static Clip envirC=null;
    private static String envirS="";
    
public static void playSfxSound(String name) {
    File sample = new File(path+name);
    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
             try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sample)) {
        Clip tmp = AudioSystem.getClip();
        tmp.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent le) {
             if (le.getType() == LineEvent.Type.STOP)
                tmp.close();   
                Thread.currentThread().stop();
            }
        });
        
        tmp.open(audioInputStream);
        setVol(tmp,OptionDB.getVolS());
        tmp.start();
    } catch(Exception ex) {}   
        }
        
    });
    t.start();
}  

public static void playEnSound(String name) {
      File sample = new File(path+name);
      if (name.equals(envirS)) return;
    if (name.substring(0, 2).equals("ev") && envirC!=null && envirC.isRunning()) {
        envirC.close();
    }
    Thread t = new Thread( new Runnable(){
          @Override
          public void run() {
                  try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sample)) {
        envirC = AudioSystem.getClip();
        
        envirC.open(audioInputStream);
        
        envirC.loop(0);
        synVol();
        envirS=name;
    } catch(Exception ex) {}
          }
        
    });
    t.start();
}
public static void setVol(Clip tmp, int level) { 
    double gain = (double) level/100;
    float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
    FloatControl gainControl = (FloatControl) tmp.getControl(FloatControl.Type.MASTER_GAIN);
    gainControl.setValue(dB);
}
public static void synVol() {
    setVol(envirC, OptionDB.getVolE());
}
}
