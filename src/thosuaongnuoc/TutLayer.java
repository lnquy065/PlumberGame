package thosuaongnuoc;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;


public class TutLayer extends JLayeredPane {
    private QButton btnClose;
     private Font mFont = new Font("MercuriusScript", Font.BOLD, 25);
    public TutLayer() {
        setBounds(0, 0, 800, 600);
        setLayout(null);
         JLabel form_title = new JLabel("Hướng Dẫn!!");
        form_title.setIcon(ImgDB.get("notice.png"));
        form_title.setBounds(400 - 90, 10+20, 180, 40);
        form_title.setFont(mFont);
        form_title.setForeground(Color.white);
        form_title.setVerticalTextPosition(JLabel.CENTER);
        form_title.setHorizontalTextPosition(JLabel.CENTER);
        add(form_title);
        
        
        btnClose = new QButton("btn_yes");
        btnClose.setBounds(400-25, 480, 50, 50);
        add(btnClose);
        
        JLabel form = new JLabel(ImgDB.get("tut_back.png"));
        form.setBounds(20, 20, 760, 530);
        add(form);
        
        JLabel back = new JLabel(ImgDB.get("tut_backgnd.png"));
        back.setBounds(0, 0, 800, 600);
        add(back);
        setVisible(false);
    }

    public QButton getBtnClose() {
        return btnClose;
    }
}
