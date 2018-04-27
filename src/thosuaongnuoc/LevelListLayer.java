package thosuaongnuoc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class LevelListLayer extends JLayeredPane implements ActionListener {

    private JPanel mainP;
    private JPanel msgP;
    private QuestPanel quest;

    private Font mFont = new Font("MercuriusScript", Font.BOLD, 20);
    private QButton btnLevel[] = new QButton[15];
    

    private int type = 0;

    private JLabel lbpage;
    private JLabel lbTypeTags;
    
    private QButton btnNext;
    private QButton btnPrev;
    private QButton btnHome;
    private QButton btnMyLevel;
    private QButton btnSysLevel;
    private QButton btnPlay;
    private QButton btnRemove;
    private QButton btnEdit;
    private QButton btnClose;

    private int maxPage = 0;
    private int curPage = 0;
    private JLabel msgP_star;
    private final JLabel msgP_thumb;
    private final JLabel msgP_lbTen;
    private final JLabel msgP_lbTime;
    private final JLabel msgP_lbStep;
    private final JLabel msgP_lbScores;
    private final JLabel lbTypeTag;


    public LevelListLayer() {
        setLayout(null);
//========Quest
        quest = new QuestPanel();
        quest.getBtnYes().addActionListener(this);
        quest.getBtnNo().addActionListener(this);
        add(quest, new Integer(2));
        
//========Main
        mainP = new JPanel();
        mainP.setLayout(null);
        mainP.setBounds(0, 0, 800, 600);
        add(mainP, new Integer(1));

        JPanel form = new JPanel();
        form.setBounds(20, 20, 760, 530);
        form.setLayout(null);
        form.setOpaque(false);
        mainP.add(form);

        JLabel title_lb = new JLabel("Chọn màn chơi");
        title_lb.setIcon(ImgDB.get("notice.png"));
        title_lb.setVerticalTextPosition(JLabel.CENTER);
        title_lb.setHorizontalTextPosition(JLabel.CENTER);
        title_lb.setBounds(290, 20, 180, 40);
        title_lb.setFont(mFont.deriveFont(Font.BOLD, 25));
        title_lb.setForeground(Color.white);
        form.add(title_lb);

        JPanel level_p = new JPanel();
        level_p.setLayout(null);
        level_p.setBounds(75, 80, 620, 370);
        level_p.setOpaque(false);
        form.add(level_p);

        for (int i = 0; i < 15; i++) {
            btnLevel[i] = new QButton();
            btnLevel[i].setBounds(30 + (i > 4 ? i > 9 ? i - 10 : i - 5 : i) * 120, i > 4 ? i > 9 ? 230 : 130 : 30, 85, 85);
            btnLevel[i].setHorizontalTextPosition(JButton.CENTER);
            btnLevel[i].setVerticalTextPosition(JButton.CENTER);
            btnLevel[i].setForeground(Color.white);
            btnLevel[i].addActionListener(this);
            level_p.add(btnLevel[i]);
        }

        lbpage = new JLabel();
        lbpage.setBounds(280, 330, 60, 20);
        lbpage.setFont(mFont.deriveFont(Font.BOLD, 25));
        level_p.add(lbpage);

        JLabel level_p_back = new JLabel(ImgDB.get("levellist_grid.png"));
        level_p_back.setBounds(0, 0, 620, 370);
        level_p.add(level_p_back);

        btnPrev = new QButton("btn_prev");
        btnPrev.setBounds(100, 460, 50, 50);
        btnPrev.addActionListener(this);
        form.add(btnPrev);

        btnNext = new QButton("btn_next");
        btnNext.setBounds(160, 460, 50, 50);
        btnNext.addActionListener(this);
        form.add(btnNext);

        btnSysLevel = new QButton("btn_slevel");
        btnSysLevel.setBounds(560, 460, 50, 50);
        btnSysLevel.setVisible(false);
        btnSysLevel.addActionListener(this);
        form.add(btnSysLevel);

        btnMyLevel = new QButton("btn_user");
        btnMyLevel.setBounds(560, 460, 50, 50);
        btnMyLevel.addActionListener(this);
        form.add(btnMyLevel);

        btnHome = new QButton("btn_home");
        btnHome.setBounds(620, 460, 50, 50);
        form.add(btnHome);
        
        lbTypeTag = new JLabel("");
        lbTypeTag.setBounds(760/2 - 100, 465, 200, 40);
        lbTypeTag.setFont(mFont.deriveFont(Font.BOLD,30));
        lbTypeTag.setHorizontalAlignment(JLabel.CENTER);
        form.add(lbTypeTag);

        JLabel back_form = new JLabel(ImgDB.get("levellist_form_back.png"));
        back_form.setBounds(0, 0, 760, 530);
        form.add(back_form);
        
        

        JLabel back_gnd = new JLabel(ImgDB.get("levellist_back.png"));
        back_gnd.setBounds(0, 0, 800, 600);
        mainP.add(back_gnd);

//========== MSG panel
        msgP = new JPanel();
        msgP.setLayout(null);
        msgP.setBounds(0, 0, 800, 600);
        msgP.setOpaque(false);
        add(msgP, new Integer(0));

        //form
        JPanel msgP_form = new JPanel();
        msgP_form.setLayout(null);
        msgP_form.setBounds(400 - 380 / 2, 300 - 220 / 2, 380, 220);
        msgP_form.setOpaque(false);
        msgP.add(msgP_form);

        //star
        msgP_star = new JLabel();
        msgP_star.setBounds(230, 110, 120, 40);
        msgP_star.setIcon(ImgDB.get("0star.png"));
        msgP_form.add(msgP_star);

        //thumbnail
        msgP_thumb = new JLabel();
        msgP_thumb.setBounds(225, 20, 130, 130);
        msgP_thumb.setIcon(ImgDB.get("thumbnail.png"));
        msgP_form.add(msgP_thumb);

        //lb Ten man
        msgP_lbTen = new JLabel(ImgDB.get("notice.png"));
        msgP_lbTen.setBounds(10, 20, 180, 40);
        msgP_lbTen.setHorizontalTextPosition(JLabel.CENTER);
        msgP_lbTen.setVerticalTextPosition(JLabel.CENTER);
        msgP_lbTen.setFont(mFont.deriveFont(Font.PLAIN, 25));
        msgP_lbTen.setForeground(Color.white);
        msgP_form.add(msgP_lbTen);

        //lb Thoi gian
        JLabel msgP_lbTimeTag = new JLabel("Thời gian:");
        msgP_lbTimeTag.setBounds(20, 65, 100, 25);
        msgP_lbTimeTag.setFont(mFont.deriveFont(Font.PLAIN, 20));
        msgP_form.add(msgP_lbTimeTag);

        msgP_lbTime = new JLabel("");
        msgP_lbTime.setBounds(140, 65, 40, 25);
        msgP_lbTime.setFont(mFont.deriveFont(Font.PLAIN, 20));
         msgP_lbTime.setHorizontalAlignment(JLabel.RIGHT);
        msgP_form.add(msgP_lbTime);

        //lb Luot di
        JLabel msgP_lbStepTag = new JLabel("Lượt đi:");
        msgP_lbStepTag.setBounds(20, 90, 100, 25);
        msgP_lbStepTag.setFont(mFont.deriveFont(Font.PLAIN, 20));
        msgP_form.add(msgP_lbStepTag);

        msgP_lbStep = new JLabel("");
        msgP_lbStep.setBounds(140, 90, 40, 25);
        msgP_lbStep.setFont(mFont.deriveFont(Font.PLAIN, 20));
         msgP_lbStep.setHorizontalAlignment(JLabel.RIGHT);
        msgP_form.add(msgP_lbStep);

        //lb Diem Cao
        JLabel msgP_lbHSTag = new JLabel("Điểm cao:");
        msgP_lbHSTag.setBounds(20, 115, 100, 25);
        msgP_lbHSTag.setFont(mFont.deriveFont(Font.PLAIN, 20));
        msgP_form.add(msgP_lbHSTag);

        msgP_lbScores = new JLabel("");
        msgP_lbScores.setBounds(130, 115, 50, 25);
        msgP_lbScores.setFont(mFont.deriveFont(Font.PLAIN, 20));
        msgP_lbScores.setHorizontalAlignment(JLabel.RIGHT);
        msgP_form.add(msgP_lbScores);

        //btn Play
        btnPlay = new QButton("btnlb_play");
        btnPlay.setBounds(20, 155, 150, 50);
        msgP_form.add(btnPlay);

        btnRemove = new QButton("btn_delete");
        btnRemove.setBounds(205, 155, 50, 50);
        btnRemove.addActionListener(this);
        msgP_form.add(btnRemove);

        btnEdit = new QButton("btn_edit");
        btnEdit.setBounds(260, 155, 50, 50);
        msgP_form.add(btnEdit);

        btnClose = new QButton("btn_no");
        btnClose.setBounds(315, 155, 50, 50);
        btnClose.addActionListener(this);
        msgP_form.add(btnClose);

        //form backgnd
        JLabel msgP_form_gnd = new JLabel(ImgDB.get("pre_play_back.png"));
        msgP_form_gnd.setBounds(0, 0, 380, 220);
        msgP_form.add(msgP_form_gnd);

        //glass
        JLabel msgP_glass = new JLabel(ImgDB.get("glass.png"));
        msgP_glass.setBounds(0, 0, 800, 600);
        msgP_glass.addMouseListener(new MouseAdapter(){});
        msgP.add(msgP_glass);

        this.setVisible(false);
    }

    public void init(int type) {
        this.type=type;
        if (type==0) {
            btnSysLevel.setVisible(false);
            btnMyLevel.setVisible(true); 
            calMaxPage(type);
        } else {
            btnSysLevel.setVisible(true);
            btnMyLevel.setVisible(false);
            calMaxPage(type);
        }
        this.setLayer(mainP, 1);
        this.setLayer(msgP, 0);
    }
    
    private void calMaxPage(int type) {
        int count = type==0? LevelDB.getcSys():LevelDB.getcUser();
        maxPage = count/15 + (count%15==0? 0:1);
    }

    public QButton getBtnEdit() {
        return btnEdit;
    }

    
    public JButton getBtnHome() {
        return btnHome;
    }
    public JButton getBtnPlay() {
        return btnPlay;
    }

    public QButton getBtnComRemove() {
        return quest.getBtnYes();
    }
    

    public int getType() {
        return type;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getCurPage() {
        return curPage;
    }

    public QuestPanel getQuest() {
        return quest;
    }
    

    
    public void setImagePage() {
        lbpage.setIcon(ImgDB.get("page_"+curPage+maxPage+".png"));
    }

    public void showLevel(QButton btn, GLevel level) {
        btn.setVisible(false);
        if (level==null) return;
        if (!level.getLooked() || type==1) {
        btn.setText(level.getSName());
        btn.setFont(mFont.deriveFont(Font.PLAIN,type==0? 25:20));
        btn.setAIcon(level.getStar()+"star_level");
        btn.setVisible(true);
        } else {
         btn.setText("");
        btn.setAIcon("locked");
        btn.setVisible(true);   
        }
    }
    
    public void showMsg() {
        //xu li thong tin
        String name = LevelDB.getCurLevel().getName().substring(0, LevelDB.getCurLevel().getName().length() - 4);
        msgP_lbTime.setText(LevelDB.getCurLevel().getTime()+"");
        msgP_lbStep.setText(LevelDB.getCurLevel().getStep()+"");
        msgP_lbScores.setText(LevelDB.getCurLevel().getScores()+"");
        if (LevelDB.getCurLevel().getLooked()) btnPlay.setEnabled(false);
        else btnPlay.setEnabled(true);
        //xu li form
        this.setLayer(msgP, 1);
        this.setLayer(mainP, 0);
        if (type == 0) {
            msgP_star.setIcon(ImgDB.get(LevelDB.getCurLevel().getStar()+"star.png"));
            msgP_lbTen.setText("Màn "+name.substring(2));
            btnRemove.setVisible(false);
            btnEdit.setVisible(false);
        } else {
            msgP_thumb.setIcon(ImgDB.get("custom_level.png"));
            msgP_lbTen.setText(name.substring(2));
            btnRemove.setVisible(true);
            btnEdit.setVisible(true);
        }

    }
    public void showPage(int page) {
        GLevel tmp = null;
        curPage = page;
        setImagePage();
        if (type==0) lbTypeTag.setText("Mùa "+curPage);
        else lbTypeTag.setText("Tự xây dựng");
        for (int i = 0; i < 15; i++) {
            switch (page) {
                case 1:
                    tmp = LevelDB.getLevel(type, i);
                    break;
                case 2:
                    tmp = LevelDB.getLevel(type, i+15);
                    break;
                case 3:
                    tmp = LevelDB.getLevel(type, i+30);
                    break;
            }
            showLevel(btnLevel[i],tmp);
        }
        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        if (curPage == 1) {
            btnPrev.setEnabled(false);
        }
        if (curPage == maxPage) {
            btnNext.setEnabled(false);
        }
    }


    @Override
    public void actionPerformed(ActionEvent me) {
        if (me.getSource() == btnMyLevel) {
            type = 1;
            calMaxPage(type);
            showPage(1);
            btnMyLevel.setVisible(false);
            btnSysLevel.setVisible(true);
        }
        if (me.getSource() == btnSysLevel) {
            type = 0;
            calMaxPage(type);
            showPage(1);
            btnSysLevel.setVisible(false);
            btnMyLevel.setVisible(true);
        }
        if (me.getSource() == btnPrev) {
            if (curPage > 1) {
                showPage(curPage - 1);
            }
            btnPrev.setEnabled(true);
            btnNext.setEnabled(true);
            if (curPage == 1) {
                btnPrev.setEnabled(false);
            }
            if (curPage == maxPage) {
                btnNext.setEnabled(false);
            }
        }
        if (me.getSource() == btnNext) {
            if (curPage < maxPage) {
                showPage(curPage + 1);
            }
            btnPrev.setEnabled(true);
            btnNext.setEnabled(true);

            if (curPage == 1) {
                btnPrev.setEnabled(false);
            }
            if (curPage == maxPage) {
                btnNext.setEnabled(false);
            }
        }
        if (me.getSource() == btnClose) {
            this.setLayer(msgP, 0);
            this.setLayer(mainP, 1);
        }
        if (me.getSource() == btnPlay) {
            btnPlay.pressed();
        }
        if (me.getSource()==btnRemove) {
            quest.showQuest("Bạn có muốn xóa màn chơi này?", 25);
        }
        if (me.getSource()==quest.getBtnNo()) {
            quest.hideQuest();
        }
        
        for (int i = 0; i < 15; i++) {
            if (me.getSource() == btnLevel[i]) {
                LevelDB.setCurLevel(type,i+(curPage-1)*15);
                showMsg();
            }

        }
    }
}
