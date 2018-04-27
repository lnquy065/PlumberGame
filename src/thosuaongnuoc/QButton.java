package thosuaongnuoc;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class QButton extends JButton implements MouseListener{
    private ImageIcon entered;
    private ImageIcon pressed;
    private ImageIcon exited;
    
    public QButton(){
    defaultS();};
    
    public QButton(String name) {
        entered = ImgDB.get(name+"_en.png");
        pressed = ImgDB.get(name+"_pr.png");
        exited = ImgDB.get(name+".png");
        setIcon(exited);
        defaultS();
    }
    
    public QButton(ImageIcon en) {
        super(en);
        defaultS();
    }
    
    public QButton(ImageIcon en, ImageIcon ex, ImageIcon pr) {
        super(en);
        entered = en;
        pressed = pr;
        exited = ex;
        defaultS();
    }
     public QButton(ImageIcon en, ImageIcon ex) {
         super(en);
        entered = en;
        exited = ex;
        defaultS();
    }   
     
    public void setAIcon(String name) {
        entered = ImgDB.get(name+"_en.png");
        pressed = ImgDB.get(name+"_pr.png");
        exited = ImgDB.get(name+".png");
        setIcon(exited);
    }
    
    public void defaultS() {
        this.setSize(50, 50);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.addMouseListener(this);
    }
     
    public void entered() {
        setIcon(entered);
    }
    public void pressed() {
        setIcon(pressed);
    }
    public void exited() {
        setIcon(exited);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(pressed!=null)
        pressed();
        SoundDB.playSfxSound("btnHover.wav");
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        SoundDB.playSfxSound("click.wav");
        entered();
    }

    @Override
    public void mouseExited(MouseEvent me) {
        exited();
    }
    
    
}
