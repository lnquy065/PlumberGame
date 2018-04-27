package thosuaongnuoc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class TipPanel extends JPanel {
    private String content;
    private JLabel textf;
    private boolean show=false;
    private Font mFont = new Font("MercuriusScript", Font.BOLD, 20);
        private Timer time = new Timer(10000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (show==true) {
                setVisible(false);
                show=false; 
            }
        }
    });
        
    public TipPanel() {
        setLayout(null);
        setOpaque(false);
        this.setBounds(0, 450, 250, 100);
        //text
        textf = new JLabel();
        textf.setBounds(50, 10, 190, 55);
        textf.setFont(mFont);
        textf.setHorizontalAlignment(JLabel.CENTER);
        textf.setVerticalAlignment(JLabel.CENTER);
        add(textf);
        //close
        QButton btnClose = new QButton("btn_nos");
        btnClose.setBounds(230, 0, 20, 20);
        add(btnClose);
        btnClose.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                setVisible(false);
            }
            public void mouseEntered(MouseEvent me) {
                btnClose.entered();
            }
            public void mouseExited(MouseEvent me) {
                btnClose.exited();
            }
        });
        //back
        JLabel back = new JLabel(ImgDB.get("tips.png"));
        back.setBounds(0, 0, 250, 100);
        add(back);
        setVisible(false);
    }
    
    public void show(String content, int size) {
        if (OptionDB.getTip()==false) return;
        this.content = content;
        show=true;
        textf.setText(String.format("<html><div WIDTH=180>%s</div><html>", content));
        textf.setFont(mFont.deriveFont(Font.PLAIN,size));
        setVisible(true);
        this.time.restart();
    }
            
}
