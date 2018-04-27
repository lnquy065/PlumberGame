package thosuaongnuoc;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author LN Quy
 */
public class ThoSuaOngNuoc extends JFrame implements ActionListener{
    private static ConsoleFrame consoleF;
    private static CreateLevelLayer createL;
    private static StageLayer stageL;
    private static HomeLayer homeL;
    private static LevelListLayer levelL;
    private static OptionLayer optionL;
    private static TutLayer tutL;

    private static QuestPanel quest;

    public ThoSuaOngNuoc() {
        this.loadAllFont();
        ImgDB.loadFromFile();
        OptionDB.loadFromFile();
        LevelDB.loadLevelFromFile(0);
        LevelDB.loadLevelFromFile(1);  
        initForm();
    }


    public void initForm() {
        setTitle("Tho sua ong nuoc");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        
        tutL = new TutLayer();
        tutL.getBtnClose().addActionListener(this);
        add(tutL);

        quest = new QuestPanel();
        quest.getBtnNo().setVisible(false);
        quest.getBtnYes().setLocation(380 / 2 - 25, 160);
        quest.getBtnYes().addActionListener(this);
        add(quest);

        homeL = new HomeLayer();
        homeL.setBounds(0, 0, 800, 600);
        homeL.getBtnStart().addActionListener(this);
        homeL.getBtnCreate().addActionListener(this);
        homeL.getBtnOption().addActionListener(this);
        homeL.getBtnQuit().addActionListener(this);
        homeL.getBtnHelp().addActionListener(this);
        add(homeL);

        stageL = new StageLayer();
        stageL.setBounds(0, 0, 800, 600);
        stageL.getBtnHome().addActionListener(this);
        stageL.getBtnHomeE().addActionListener(this);
        stageL.getBtnReplayE().addActionListener(this);
        stageL.getBtnNextE().addActionListener(this);
        stageL.getBtnLevel().addActionListener(this);
        add(stageL);

        createL = new CreateLevelLayer();
        createL.setBounds(0, 0, 800, 600);
        createL.getBtnHome().addActionListener(this);
        add(createL);

        levelL = new LevelListLayer();
        levelL.setBounds(0, 0, 800, 600);
        levelL.getBtnHome().addActionListener(this);
        levelL.getBtnPlay().addActionListener(this);
        levelL.getBtnComRemove().addActionListener(this);
        levelL.getBtnEdit().addActionListener(this);
        add(levelL);

        optionL = new OptionLayer();
        optionL.setBounds(0, 0, 800, 600);
        optionL.getBtnNo().addActionListener(this);
        optionL.getBtnYes().addActionListener(this);
        add(optionL);

        setVisible(true);
        consoleF = new ConsoleFrame(this);
        if (OptionDB.getsConsole()) {
            consoleF.showConsole();
        } else {
            consoleF.hideConsole();
        }
    }

    public static void main(String[] args) {
        //Dua Form vao Thread EDT 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ThoSuaOngNuoc game = new ThoSuaOngNuoc();
                game.setLocationRelativeTo(null);
                setCurLayer(homeL);
            }
        });
    }

    //Bat su kien
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == stageL.getBtnHome()
                || e.getSource() == stageL.getBtnHomeE()
                || e.getSource() == createL.getBtnHome()
                || e.getSource() == levelL.getBtnHome()) {
            stageL.stopStage();
            setCurLayer(homeL);
        }
        if (e.getSource() == homeL.getBtnStart() || e.getSource() == stageL.getBtnLevel()) {
            levelL.init(0);
            levelL.showPage(1);
            setCurLayer(levelL);
            //setCurrentPanel("stage");
            //stageP.stageLoad(stage);
            //stageP.stageStart();
        }
        if (e.getSource() == homeL.getBtnCreate()) {
            if (LevelDB.getcUser() >= 45) {
                quest.showQuest("Đầy bộ nhớ!<br>Vui lòng xóa level cũ để tạo mới!", 20);
            } else {
                createL.newLevel();
                setCurLayer(createL);
            }
        }
        if (e.getSource() == levelL.getBtnEdit()) {
            createL.editLevel(LevelDB.getCurLevel());
            setCurLayer(createL);
        }
        if (e.getSource() == homeL.getBtnQuit()) {
            System.exit(0);
        }
        if (e.getSource() == levelL.getBtnPlay() || e.getSource() == stageL.getBtnReplayE()) {
            stageL.loadLevel(LevelDB.getCurLevel());
            stageL.startStage();
            setCurLayer(stageL);
        }
        if (e.getSource() == stageL.getBtnNextE()) {
            LevelDB.setCurLevel(0, LevelDB.getIndexOfCurLevel() + 1);
            stageL.loadLevel(LevelDB.getCurLevel());
            stageL.startStage();
            setCurLayer(stageL);
        }
        if (e.getSource() == levelL.getBtnComRemove()) {
            int tmpT = levelL.getType();
            int tmpP = levelL.getCurPage();
            LevelDB.removeInLevelFile(LevelDB.getCurLevel());
            levelL.init(tmpT);
            levelL.showPage(tmpP > levelL.getMaxPage() ? levelL.getMaxPage() : tmpP);
            levelL.getQuest().hideQuest();
            setCurLayer(levelL);
        }
        if (e.getSource() == homeL.getBtnOption()) {
            optionL.loadInfoFromDB();
            setCurLayer(optionL);
        }
        if (e.getSource() == optionL.getBtnNo()) {
            setCurLayer(homeL);
            
        }
        if (e.getSource() == optionL.getBtnYes()) {
            OptionDB.setInfo(optionL.getVolE(), optionL.getVolS(), optionL.getTip(), optionL.getConsole());
            OptionDB.writeToFile();
            if (optionL.getConsole()) {
                consoleF.showConsole();
            } else {
                consoleF.hideConsole();
            }
            setCurLayer(homeL);
            SoundDB.synVol();
        }
        if (e.getSource() == quest.getBtnYes()) {
            quest.hideQuest();
        }
        if (e.getSource() == tutL.getBtnClose()) {
            setCurLayer(homeL);
        }
        if (e.getSource() == homeL.getBtnHelp()) {
            setCurLayer(tutL);
        }
    }

    public static void setCurLayer(JLayeredPane pane) {
        tutL.setVisible(false);
        optionL.setVisible(false);
        stageL.setVisible(false);
        createL.setVisible(false);
        homeL.setVisible(false);
        levelL.setVisible(false);
        pane.setVisible(true);
        if (pane==homeL)  SoundDB.playEnSound("ev_home.wav");
        if (pane==optionL) SoundDB.playEnSound("ev_option.wav");
        if (pane==createL) SoundDB.playEnSound("ev_create.wav");
        if (pane==levelL) SoundDB.playEnSound("ev_level.wav");
        if (pane==stageL) {
            Random r = new Random();
            SoundDB.playEnSound("ev_stage"+r.nextInt(4)+".wav");
        }
    }
    public static void loadAllFont() {
                createFontFormFile("src\\font\\" + "circle.ttf");
        createFontFormFile("src\\font\\" + "digits.ttf");
        createFontFormFile("src\\font\\" + "merscrb.ttf");
        createFontFormFile("src\\font\\" + "endp.ttf");
    }

    public static void createFontFormFile(String path) {
       
        
        File file = new File(path);
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, file);
        } catch (FontFormatException | IOException ex) {
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
    }

}
