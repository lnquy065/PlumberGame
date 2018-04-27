package thosuaongnuoc;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class QCheckbox extends JPanel implements MouseListener{
    private boolean checked=false;
    private JLabel check;
    public QCheckbox() {
        setLayout(null);
        setSize(40,40);
        setOpaque(false);
        
        check = new JLabel(ImgDB.get("uncheckedbox.png"));
        check.setBounds(0, 0, 40, 40);
        check.addMouseListener(this);
        add(check);
    }
    
    public boolean getStt() {
        return checked;
    }
    
    public void setStt(boolean stt) {
        checked=stt;
        if (checked==false) check.setIcon(ImgDB.get("uncheckedbox.png"));
        else check.setIcon(ImgDB.get("checkedbox.png"));
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        setStt(!checked);
        SoundDB.playSfxSound("btnHover.wav");
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if (checked==false) check.setIcon(ImgDB.get("uncheckedbox_en.png"));
        else check.setIcon(ImgDB.get("checkedbox_en.png"));
        SoundDB.playSfxSound("click.wav");
    }

    @Override
    public void mouseExited(MouseEvent me) {
         if (checked==false) check.setIcon(ImgDB.get("uncheckedbox.png"));
        else check.setIcon(ImgDB.get("checkedbox.png"));
         
    }
}
