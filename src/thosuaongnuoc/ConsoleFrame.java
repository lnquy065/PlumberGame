package thosuaongnuoc;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConsoleFrame extends JFrame implements ActionListener {
    
    private JFrame fcha;
    private JTextField cmdLine;
    private JTextArea text;
    private JButton btnSub;
    
    public ConsoleFrame(JFrame fcha) {
        this.fcha = fcha;
        setTitle("Console");
        setLayout(null);
        setSize(400, 300);
        setResizable(false);
        
        text = new JTextArea();
        text.setBorder(BorderFactory.createLineBorder(Color.black));
        text.setLineWrap(true);
        text.setFont(text.getFont().deriveFont(18f));
        text.setEditable(false);
        JScrollPane scroll = new JScrollPane(text);
        scroll.setBounds(5, 5, 385, 220);
        
        add(scroll);
        
        cmdLine = new JTextField();
        cmdLine.setBounds(5, 230, 300, 30);
        cmdLine.setFont(cmdLine.getFont().deriveFont(18f));
        add(cmdLine);
        
        btnSub = new JButton("Submit");
        btnSub.setBounds(310, 230, 80, 30);
        btnSub.setMnemonic(KeyEvent.VK_ENTER);
        btnSub.addActionListener(this);
        add(btnSub);
        this.getRootPane().setDefaultButton(btnSub);
        
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                OptionDB.setCon(false);
                OptionDB.writeToFile();
                System.out.print(2);
            }
        });
        hideConsole();
    }
    
    public void showConsole() {
        setLocation(fcha.getLocationOnScreen().x + 800, fcha.getLocationOnScreen().y);
        setVisible(true);
    }

    public void hideConsole() {
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String cmd = "‣"+cmdLine.getText();
        if (cmdLine.getText()!="" )
        text.setText(text.getText()+cmd+"\r\n");
        String item[] = cmdLine.getText().split(" ");
        cmdLine.setText("");
        switch (item[0]) {
            case "hello":
                text.setText(text.getText()+"  ‣Chào cái gì mà chào!\r\n");
                break;
            case "unlock":
                try {
                if (item[1].equals("all")) {
                    LevelDB.unlockAllLevel();
                    text.setText(text.getText()+"  ‣Đã mở khóa tất cả Level!\r\n"); 
                    break;
                }
                int level = Integer.parseInt(item[1]);
                if (level<0 || level>=LevelDB.getcSys()) {
                    text.setText(text.getText()+"  ‣Lỗi: Level không tồn tại!\r\n"); 
                    break;
                }
                LevelDB.unlockLevel(level);
                text.setText(text.getText()+"  ‣Đã mở khóa Level "+level+"!\r\n"); 
                break;
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    text.setText(text.getText()+"  ‣Lỗi: Sai cú pháp! Gõ 'help' để trợ giúp!\r\n"); 
                    break;
                }
            case "resetgame":
                LevelDB.resetLevelFile();
                LevelDB.loadLevelFromFile(0);
                LevelDB.loadLevelFromFile(1);
                text.setText(text.getText()+"  ‣Đã Reset game!\r\n"); 
                    break;
            case "resetlevel":
                try {
                    int type = Integer.parseInt(item[1]);
                    int index = Integer.parseInt(item[2]);
                    if (type!=0 && type!=1) throw new NumberFormatException();
                    if (index>= (type==0? LevelDB.getcSys():LevelDB.getcUser())) {
                       text.setText(text.getText()+"  ‣Lỗi: Level không tồn tại!\r\n"); 
                        break; 
                    }
                    GLevel newL = LevelDB.getLevel(type, index);
                    newL.setLocked(false);
                    newL.setScores(0);
                    newL.setStar(0);
                    LevelDB.updateInLevelFile(newL);
                    text.setText(text.getText()+"  ‣Đã Reset level ("+type+")("+index+")!\r\n"); 
                    break;
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    text.setText(text.getText()+"  ‣Lỗi: Sai cú pháp!\r\n"); 
                    break;
                }
            case "help":
                text.setText(text.getText()+"==== HELP ====\r\n"); 
                text.setText(text.getText()+"  ‣unlock [var]\r\n"); 
                text.setText(text.getText()+"    -> [var]\r\n"); 
                text.setText(text.getText()+"       all: Mở tất cả level\r\n"); 
                text.setText(text.getText()+"       i: Mở level i\r\n"); 
                text.setText(text.getText()+"  ‣resetgame\r\n"); 
                text.setText(text.getText()+"    -> Khôi phục game\r\n"); 
                text.setText(text.getText()+"  ‣resetlevel [type] [index]\r\n"); 
                text.setText(text.getText()+"    -> [type]\r\n"); 
                text.setText(text.getText()+"       0: Reset level sys\r\n"); 
                text.setText(text.getText()+"       1: Reset level user\r\n"); 
                text.setText(text.getText()+"    -> [index]\r\n"); 
                text.setText(text.getText()+"       index: Reset level index\r\n"); 
                text.setText(text.getText()+"===============\r\n"); 
                break;
            default: 
               text.setText(text.getText()+"Lỗi: Sai mã lệnh!\r\n"); 
               break;
        }
    }
}
