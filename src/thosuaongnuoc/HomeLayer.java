package thosuaongnuoc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class HomeLayer extends JLayeredPane {
    private JLabel back_gnd;
    private QButton btnStart;
    private QButton btnCreate;
    private QButton btnOption;
    private QButton btnQuit;
    private QButton btnHelp;
    private JLabel lbInfo;
    private JPanel pnInfo;
    
    private Font mFont = new Font("MercuriusScript",Font.BOLD,32);
    
    public HomeLayer() {
        this.setName("Home");
        setLayout(null);
        
        //Play
        btnStart = new QButton("btnlb_mplay");
        btnStart.setBounds(320, 240, 160, 65);
        add(btnStart);
        //help
        btnHelp = new QButton("btn_help");
        btnHelp.setLocation(10, 10);
        add(btnHelp);
        //Tao map
        btnCreate = new QButton("btnlb_mcreate");
        btnCreate.setBounds(320, 310, 160, 65);
        add(btnCreate);        
        //cai dat
        btnOption = new QButton("btnlb_msetting");
        btnOption.setBounds(320, 380, 160, 65);
        add(btnOption);           
        
        //thoat
        btnQuit = new QButton("btnlb_mexit");
        btnQuit.setBounds(320, 470, 160, 65);
        add(btnQuit);  
        
        //Info Label
        lbInfo = new JLabel();
        lbInfo.setIcon(ImgDB.get("info.png"));
        lbInfo.setBounds(750, 530, 30, 30);
        lbInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                pnInfo.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                pnInfo.setVisible(false); 
            }
        });
        add(lbInfo);
        
        JLabel version = new JLabel("v1.0");
        version.setBounds(710, 530, 30, 30);
        version.setForeground(Color.white);
        add(version);
        
        //Info panel
        pnInfo = new JPanel();
        pnInfo.setLayout(null);
        pnInfo.setOpaque(false);
        JLabel title = new JLabel("Báo Cáo Đề Tài");
        title.setBounds(60, 10, 200, 30);
        pnInfo.add(title);
        JLabel monh = new JLabel("Môn học: LT Hướng Đối Tượng");
        monh.setBounds(15, 40, 200, 30);
        pnInfo.add(monh);
        JLabel gvien = new JLabel("G.Viên: Nguyễn Thị Tuyết Hải");
        gvien.setBounds(15, 60, 200, 30);
        pnInfo.add(gvien);
        JLabel svien = new JLabel("S.Viên: Lương Ngọc Quý");
        svien.setBounds(15, 90, 200, 30);
        pnInfo.add(svien);
        JLabel mssv = new JLabel("N14DCCN132 | D14CQCN02");
        mssv.setBounds(15, 110, 200, 30);
        pnInfo.add(mssv);
        JLabel gameI = new JLabel("Thợ Sửa Ống Nước");
        gameI.setBounds(15, 140, 200, 30);
        pnInfo.add(gameI);
        JLabel ver = new JLabel("Phiên bản: 1.0 11/2016");
        ver.setBounds(15, 160, 200, 30);
        pnInfo.add(ver);
        JLabel back_gnd = new JLabel(ImgDB.get("info_panel.png"));
        back_gnd.setBounds(0, 0, 200, 200);
        pnInfo.add(back_gnd);
        pnInfo.setBounds(580, 320, 200, 200);
        pnInfo.setVisible(false);
        add(pnInfo);
        
        back_gnd = new JLabel(ImgDB.get("back_gnd.gif"));
        back_gnd.setBounds(0, 0, 800, 600);
        add(back_gnd);
        
        setVisible(false);
    }

    public QButton getBtnHelp() {
        return btnHelp;
    }
    
    public JButton getBtnStart() {
        return btnStart;
    }
    public JButton getBtnQuit() {
        return btnQuit;
    }
    public JButton getBtnCreate() {
        return btnCreate;
    }
    public JButton getBtnOption() {
        return btnOption;
    }
}
