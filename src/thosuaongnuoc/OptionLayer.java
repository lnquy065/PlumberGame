package thosuaongnuoc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class OptionLayer extends JLayeredPane implements ActionListener {

    private Font mFont = new Font("MercuriusScript", Font.BOLD, 25);
    private QSlider volE;
    private QSlider volS;
    private QCheckbox cbTip;
    private QCheckbox cbConsole;
    private QButton btnYes;
    private QButton btnNo;
    private QButton btnReset;
    private QuestPanel quest;

    public OptionLayer() {
        setBounds(0, 0, 800, 600);
        setLayout(null);

        quest = new QuestPanel();
        quest.getBtnYes().addActionListener(this);
        quest.getBtnNo().addActionListener(this);
        add(quest);

        JPanel form = new JPanel();
        form.setBounds(400 - 200, 300 - 200, 400, 350);
        form.setLayout(null);
        form.setOpaque(false);
        add(form);

        JLabel form_title = new JLabel("Cài Đặt!!");
        form_title.setIcon(ImgDB.get("notice.png"));
        form_title.setBounds(400 / 2 - 90, 10, 180, 40);
        form_title.setFont(mFont);
        form_title.setForeground(Color.white);
        form_title.setVerticalTextPosition(JLabel.CENTER);
        form_title.setHorizontalTextPosition(JLabel.CENTER);
        form.add(form_title);

        //Nhac nen
        JLabel volETag = new JLabel("Nhạc nền");
        volETag.setBounds(25, 80, 150, 30);
        volETag.setFont(mFont.deriveFont(Font.PLAIN, 25));
        form.add(volETag);

        volE = new QSlider();
        volE.setLocation(160, 70);
        form.add(volE);

        //Nhac sfx
        JLabel volSTag = new JLabel("Hiệu ứng");
        volSTag.setBounds(25, 130, 150, 35);
        volSTag.setFont(mFont.deriveFont(Font.PLAIN, 25));
        form.add(volSTag);

        volS = new QSlider();
        volS.setLocation(160, 120);
        form.add(volS);
        //Hien tips
        JLabel tipTag = new JLabel("Hiện trợ giúp");
        tipTag.setBounds(25, 180, 200, 35);
        tipTag.setFont(mFont.deriveFont(Font.PLAIN, 25));
        form.add(tipTag);

        cbTip = new QCheckbox();
        cbTip.setLocation(190, 175);
        form.add(cbTip);
        //Dev mode
        JLabel consoleTag = new JLabel("Bật Console");
        consoleTag.setBounds(25, 230, 200, 35);
        consoleTag.setFont(mFont.deriveFont(Font.PLAIN, 25));
        form.add(consoleTag);

        cbConsole = new QCheckbox();
        cbConsole.setLocation(190, 225);
        form.add(cbConsole);
        //Button
        btnYes = new QButton("btn_yes");
        btnYes.setLocation(200 - 60, 350 - 60);
        btnYes.addActionListener(this);
        form.add(btnYes);

        btnNo = new QButton("btn_no");
        btnNo.setLocation(210, 350 - 60);
        form.add(btnNo);

        btnReset = new QButton("btnlb_reset");
        btnReset.setBounds(260, 210, 100, 33);
        btnReset.addActionListener(this);
        form.add(btnReset);

        //back
        JLabel form_back = new JLabel(ImgDB.get("option_form.png"));
        form_back.setBounds(0, 0, 400, 350);
        form.add(form_back);

        JLabel back = new JLabel(ImgDB.get("option_back.png"));
        back.setBounds(0, 0, 800, 600);
        add(back);
    }
    
    public void loadInfoFromDB() {
        OptionDB.loadFromFile();
        volE.setPos(OptionDB.getVolE());
        volS.setPos(OptionDB.getVolS());
        boolean a = OptionDB.getTip();
        cbTip.setStt(OptionDB.getTip());
        cbConsole.setStt(OptionDB.getsConsole());
    }

    public QButton getBtnNo() {
        return btnNo;
    }

    public QButton getBtnYes() {
        return btnYes;
    }

    public int getVolE() {
        return volE.getPos();
    }

    public int getVolS() {
        return volS.getPos();
    }
    
    public boolean getTip() {
        return cbTip.getStt();
    } 
    
    public boolean getConsole() {
        return cbConsole.getStt();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnReset) {
            quest.showQuest("Bạn có muốn khôi phục dữ liệu gốc?<br>Tất cả dữ liệu hiện tại sẽ bị mất", 20);
        }
        if (ae.getSource() == quest.getBtnYes()) {
            LevelDB.resetLevelFile();
            LevelDB.loadLevelFromFile(0);
            LevelDB.loadLevelFromFile(1);
            quest.hideQuest();
        }
        if (ae.getSource() == quest.getBtnNo()) {
            quest.hideQuest();
        }
    }

}
