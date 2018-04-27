package thosuaongnuoc;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QSlider extends JPanel implements MouseMotionListener {
    private int value=0;
    private JLabel bar;
    private QButton btnThumb;
    private int panelX;
    private int btnX;
    private boolean enter=false;

    public QSlider() {
        this.setOpaque(false);
        this.setSize(220, 50);
        this.setLayout(null);
        
        btnThumb = new QButton("btn_qslider");
        btnThumb.addMouseMotionListener(this);
        btnThumb.setBounds(175,6,40,40);
        add(btnThumb);

        bar = new JLabel(ImgDB.get("slider_bar.png"));
        bar.setBounds(0, 0, 220, 50);
        bar.setHorizontalAlignment(JLabel.LEFT);
        add(bar);
        
        JLabel stock = new JLabel(ImgDB.get("slider_stock.png"));
        stock.setBounds(0, 0, 220, 50);
        add(stock);
    }
    
    public void setPos(int per) {
        bar.setSize(per*2+20, 50);
        btnThumb.setLocation((int) (per*1.8), 6);
        value=per;
    }
    public int getPos() {
        return value;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        try {
           if (getMousePosition().x-20>=0 && getMousePosition().x-20<=180) {
           btnThumb.setLocation(getMousePosition().x-20,5);
           int per=btnThumb.getLocation().x*100/180;
           bar.setSize(per*2+20, 50);
           value=per;
           }
           
        } catch (NullPointerException e) {};
    }

    @Override
    public void mouseMoved(MouseEvent me) {
       //  panelX = this.getMousePosition().x;
     //   btnX = btnThumb.getMousePosition().x;
    }
}
