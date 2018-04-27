package thosuaongnuoc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StageLayer extends JLayeredPane implements ActionListener {

    //Layer
    private JPanel msgP;
    private JPanel mainP;

    //Main Content Panel
    private PipePanel pipeP;
    private JLabel backGnd;
    private QProgressBar timeBar;
    private JLabel lbStage;

    private JLabel lbTime;
    private JLabel lbStep;
    private QButton btnRun;
    private QButton btnHome;

    //Font
    private Font cFont = new Font("Circle", Font.PLAIN, 24);
    private Font dFont = new Font("SF Digital Readout", Font.BOLD, 40);
    private Font eFont = new Font("UVN Giay Trang", Font.PLAIN, 24);

    //Vars
    private GLevel tmpL;
    private boolean win;
    private int timeC;
    private int timeT;
    private int stepsT;
    private int stepsC;
    private int scores;
    private boolean stageRunning = false;
    private JPanel msg_form=null;
    private JLabel msg_glass ;

    private Timer time = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (isRunning()) {
                timeC -= 1;
                refeshInfo();
                if (timeC == 0) {
                    endStage();
                    stopStage();
                }
            }
        }
    });

    private Timer timeE = new Timer(2000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!win) {      //Thua
                msg_lbTitle.setText("Thua Rồi!!");
                msg_sttPic.setIcon(ImgDB.get("lose.gif"));
                SoundDB.playEnSound("ev_over.wav");
            } else {            //Thang
                msg_lbTitle.setText("Thắng Rồi!!");
                msg_sttPic.setIcon(ImgDB.get("win.gif"));
                SoundDB.playEnSound("ev_win.wav");
            }
            msg_form.setVisible(true);
            msg_glass.setVisible(true);
            timeE.stop();
        }
    });

    private int stepTs;
    private TipPanel tip;
    private final JLabel msg_lbTitle;
    private final JLabel msg_lbLevel;
    private final JLabel msg_lbTime;
    private final JLabel msg_scores;
    private final JLabel msg_sttPic;
    private JLabel msg_lbStep;
    private final QButton btnReplayE;
    private final QButton btnHomeE;
    private final QButton btnNextE;
    private JLabel[] msg_star = new JLabel[3];
    private final QButton btnLevel;
    private int slStar;
    private JLabel[] overL = new JLabel[45];
    

    public StageLayer() {
        setLayout(null);
        //========== Tips
        tip = new TipPanel();
        add(tip);
        //=========  Main stage
        mainP = new JPanel();
        mainP.setBounds(0, 0, 800, 600);
        mainP.setLayout(null);
        add(mainP, new Integer(1));

        for (int i=0;i<45;i++) {
            overL[i]=new JLabel();
            overL[i].setBounds(0, 0, 85, 85);
            overL[i].setVisible(false);
            mainP.add(overL[i]);
        }
        
        //Man choi
        lbStage = new JLabel();
        lbStage.setFont(cFont);
        lbStage.setBounds(20, 10, 150, 50);
        lbStage.setHorizontalTextPosition(JLabel.CENTER);
        lbStage.setVerticalTextPosition(JLabel.CENTER);
        lbStage.setIcon(ImgDB.get("stage_stage.png"));
        lbStage.setForeground(Color.WHITE);

        //So buoc
        lbStep = new JLabel();
        lbStep.setFont(cFont);
        lbStep.setBounds(20, 70, 150, 50);
        lbStep.setHorizontalTextPosition(JLabel.CENTER);
        lbStep.setVerticalTextPosition(JLabel.CENTER);
        lbStep.setIcon(ImgDB.get("stage_steps.png"));
        lbStep.setForeground(Color.WHITE);

        //Time
        lbTime = new JLabel();
        lbTime.setFont(dFont);
        lbTime.setBounds(300, 5, 150, 50);
        lbTime.setVerticalTextPosition(JLabel.CENTER);
        lbTime.setHorizontalTextPosition(JLabel.CENTER);
        lbTime.setIcon(ImgDB.get("stage_time.png"));
        lbTime.setForeground(Color.WHITE);

        //Timebar
        timeBar = new QProgressBar();
        timeBar.setBounds(210, 80, 300, 30);

        //Mo nuoc
        btnRun = new QButton("btnlb_run");
        btnRun.setBounds(550, 40, 150, 50);
        btnRun.addActionListener(this);

        //Home
        btnHome = new QButton("btn_home");
        btnHome.setBounds(710, 40, 50, 50);

        JLabel btn_back = new JLabel(ImgDB.get("stage_btn_back.png"));
        btn_back.setBounds(540, 20, 230, 90);
        
        
        //Background
        backGnd = new JLabel(ImgDB.get("stage_back.png"));
        backGnd.setBounds(0, 0, 800, 600);

        //PipePanel
        pipeP = new PipePanel(overL);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                pipeP.getPipeCell(i, j).addActionListener(this);
            }
        }
        pipeP.setBounds((800-(85*9))/2, 125, 85*9 - 5, 85*5 - 5);


        //Add, component nao add sau se chiem xuong
        //add(endP);
        mainP.add(pipeP);
        mainP.add(lbStage);
        mainP.add(lbStep);
        mainP.add(lbTime);
        mainP.add(timeBar);
        mainP.add(btnRun);
        mainP.add(btnHome);
        mainP.add(btn_back);
        mainP.add(backGnd);

        setVisible(false);
//=========Msg
        //glass
        msgP = new JPanel();
        msgP.setOpaque(false);
        msgP.setBounds(0, 0, 800, 600);
        msgP.setOpaque(false);
        msgP.setLayout(null);
        msgP.addMouseListener(new MouseAdapter(){});
        add(msgP, new Integer(0));

        //form
        msg_form = new JPanel();
        msg_form.setOpaque(false);
        msg_form.setLayout(null);
        msg_form.setBounds(110, 100, 575, 385);
        msgP.add(msg_form);

        //Win Or Lose
        msg_lbTitle = new JLabel(ImgDB.get("notice.png"));
        msg_lbTitle.setBounds(60, 40, 180, 40);
        msg_lbTitle.setFont(cFont);
        msg_lbTitle.setVerticalTextPosition(JLabel.CENTER);
        msg_lbTitle.setHorizontalTextPosition(JLabel.CENTER);
        msg_lbTitle.setForeground(Color.white);
        msg_form.add(msg_lbTitle);

        //Level
        JLabel tmp0 = new JLabel("Màn chơi");
        tmp0.setFont(eFont);
        tmp0.setBounds(50, 90, 100, 30);
        msg_form.add(tmp0);
        msg_lbLevel = new JLabel();
        msg_lbLevel.setFont(eFont);
        msg_lbLevel.setBounds(200, 90, 50, 30);
        msg_lbLevel.setHorizontalAlignment(JLabel.RIGHT);
        msg_form.add(msg_lbLevel);
        //Step
        JLabel tmp1 = new JLabel("Số lượt đi");
        tmp1.setFont(eFont);
        tmp1.setBounds(50, 120, 150, 30);
        msg_form.add(tmp1);
        msg_lbStep = new JLabel();
        msg_lbStep.setFont(eFont);
        msg_lbStep.setBounds(200, 120, 50, 30);
        msg_lbStep.setHorizontalAlignment(JLabel.RIGHT);
        msg_form.add(msg_lbStep);

        //Time
        JLabel tmp2 = new JLabel("Thời gian chơi");
        tmp2.setFont(eFont);
        tmp2.setBounds(50, 150, 150, 30);
        msg_form.add(tmp2);
        msg_lbTime = new JLabel();
        msg_lbTime.setFont(eFont);
        msg_lbTime.setBounds(200, 150, 50, 30);
        msg_lbTime.setHorizontalAlignment(JLabel.RIGHT);
        msg_form.add(msg_lbTime);

        //Split bar
        JLabel splitbar = new JLabel();
        splitbar.setOpaque(true);
        splitbar.setBounds(50, 180, 200, 3);
        splitbar.setBackground(Color.black);
        msg_form.add(splitbar);

        //Diem
        JLabel tmp3 = new JLabel("Tổng Điểm");
        tmp3.setFont(eFont);
        tmp3.setBounds(50, 190, 150, 30);
        msg_form.add(tmp3);

        msg_scores = new JLabel();
        msg_scores.setFont(eFont);
        msg_scores.setBounds(200, 190, 50, 30);
        msg_scores.setHorizontalAlignment(JLabel.RIGHT);
        msg_form.add(msg_scores);

        //sttPic
        msg_sttPic = new JLabel();
        msg_sttPic.setBounds(265, 50, 250, 210);
        msg_form.add(msg_sttPic);

        //Replay
        btnReplayE = new QButton("btn_replay");
        btnReplayE.setBounds(195, 290, 50, 50);
        msg_form.add(btnReplayE);
        //Home
        btnHomeE = new QButton("btn_home");
        btnHomeE.setBounds(480, 290, 50, 50);
        msg_form.add(btnHomeE);
        //LevelList
        btnLevel = new QButton("btn_slevel");
        btnLevel.setBounds(255, 290, 50, 50);
        msg_form.add(btnLevel);

        //Next
        btnNextE = new QButton("btn_next");
        btnNextE.setBounds(315, 290, 50, 50);
        msg_form.add(btnNextE);

        //stars
        msg_star[0] = new JLabel(ImgDB.get("star.png"));
        msg_star[1] = new JLabel(ImgDB.get("star.png"));
        msg_star[2] = new JLabel(ImgDB.get("star.png"));
        msg_star[0].setBounds(85, 230, 30, 30);
        msg_star[1].setBounds(125, 230, 30, 30);
        msg_star[2].setBounds(165, 230, 30, 30);
        msg_form.add(msg_star[0]);
        msg_form.add(msg_star[1]);
        msg_form.add(msg_star[2]);

        //form back
        JLabel msg_form_back = new JLabel(ImgDB.get("endpanel_form_back.png"));
        msg_form_back.setBounds(0, 0, 575, 385);
        msg_form.add(msg_form_back);

        //glass
        msg_glass = new JLabel(ImgDB.get("glass.png"));
        msg_glass.addMouseListener(new MouseAdapter() {
        });
        msg_glass.setBounds(0, 0, 800, 600);
        msgP.add(msg_glass);

        setVisible(false);
    }

    public void loadLevel(GLevel level) {
        pipeP.reset();
        tmpL = level;
        timeT=timeC=level.getTime();
        stepsT=stepsC=level.getStep();
        pipeP.loadArray(level.getMap());
    }

    public void startStage() {
        msgP.setVisible(false);
        stageRunning = true;
        tip.setVisible(false);
        
        if (tmpL.getName().substring(0, 2).equals("sm")) {
            lbStage.setText("Màn " + tmpL.getSName());
        } else {
            lbStage.setText(tmpL.getSName());
        }
        refeshInfo();
        time.start();
        setVisible(true);
    }

    public boolean isRunning() {
        return stageRunning;
    }

    public void stopStage() {
        stageRunning = false;
    }


    public void refeshInfo() {
        //step
        lbStep.setText(Integer.toString(stepsC));
        //time
        int m = timeC / 60;
        int s = timeC % 60;
        lbTime.setText(String.format("%02d:%02d", m, s));
        //Probar
        timeBar.setPos(timeC * 100 / timeT);
    }

    public boolean getStatus() {
        return win;
    }

    public void endStage() {

        if (pipeP.run()==true) { //win
            scores = stepsC * 10 + timeC * 10;
            win = true;
             int timeP = 100 - ((timeT - timeC) * 100 / timeT);
                if (timeP <= 50) {
                    slStar = 1;
                } else if (timeP > 50 && timeP <= 80) {
                    slStar = 2;
                } else {
                    slStar = 3;
                }
            if (scores > tmpL.getScores()) {
                tmpL.setScores(scores);
                tip.show("Điểm cao mới!!", 20);
                tmpL.setStar(slStar);
                LevelDB.updateInLevelFile(tmpL);
            }
        } else { //lose
            win = false;
            scores = 0;
            slStar=0;
        }
        showMsg();
        stopStage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRun) {                      //Mo nuoc
            SoundDB.playSfxSound("run.wav");
            endStage();
            pipeP.getPipeCell(0, 0).cellRun();
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (pipeP.getPipeCell(i, j).getType()==0 
                        || pipeP.getPipeCell(i, j).getType()==1
                        || pipeP.getPipeCell(i, j).getType()==-1)
                    continue;
                if (e.getSource() == pipeP.getPipeCell(i, j)) {
                    pipeP.getPipeCell(i, j).rotatePipe(1);
                    SoundDB.playSfxSound("rotate.wav");
                    stepsC--;
                    refeshInfo();
                    if (stepsC == 0) {
                        endStage();
                    }
                }
            }
        }
    }

    //MSG BOX
    public void showMsg() {
        //reset
        msg_form.setVisible(false);
        msg_glass.setVisible(false);
        msgP.setVisible(true);
        //show title, step,time, scores
        this.setLayer(mainP, 0);
        this.setLayer(msgP, 1);

        msg_lbLevel.setText(tmpL.getSName());
        msg_lbStep.setText(String.valueOf(stepsT - stepsC));
        msg_lbTime.setText(String.valueOf(timeT - timeC));
        msg_scores.setText(String.valueOf(scores));
        //show star
        for (int i = 0; i < 3; i++) {
            if (i < slStar) {
                msg_star[i].setVisible(true);
            } else {
                msg_star[i].setVisible(false);
            }
        }
        //show next btn
        if (tmpL.getType()==0 && LevelDB.canNext() && win==true) {
            LevelDB.unlockNextLevel();
            btnNextE.setVisible(true);
        } else btnNextE.setVisible(false);
        
        timeE.restart();
    }

    //GET BUTTON
    public QButton getBtnLevel() {
        return btnLevel;
    }

    public JButton getBtnReplayE() {
        return btnReplayE;
    }

    public JButton getBtnHomeE() {
        return btnHomeE;
    }

    public JButton getBtnNextE() {
        return btnNextE;
    }

    public JButton getBtnHome() {
        return btnHome;
    }

    class QProgressBar extends JPanel {

        private JLabel bar;     //300x30
        private JLabel seek;    //280x16
        private int value = 0;
        private int MAX = 100;

        public QProgressBar() {
            setSize(300, 30);
            setLayout(null);
            setOpaque(false);
            //seek
            seek = new JLabel(ImgDB.get("progress_seek.png"));
            seek.setBounds(10, 7, 0, 16);
            seek.setHorizontalAlignment(JLabel.LEFT);
            add(seek);
            //bar
            bar = new JLabel(ImgDB.get("progress_bar.png"));
            bar.setBounds(0, 0, 300, 30);
            add(bar);

        }

        public void setPos(int i) {
            value = i;
            if (i > 90) {
                seek.setSize(270 + (i - 90), 16);
            } else {
                seek.setSize(i * 3, 16);
            }
        }

        public int getPos() {
            return value;
        }
    }

}
