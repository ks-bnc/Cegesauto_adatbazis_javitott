import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;


public class Program extends JFrame {

	private JPanel contentPane;
	private AutoMethods autoMethods = new AutoMethods();
	private AutoTable autoTable;
	private Checker check = new Checker();
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception except) {
					except.printStackTrace();
				}
			}
		});
	
	}
	
	public Program() {
		autoMethods.Reg();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton buttonList = new JButton("Aut\u00F3k list\u00E1ja");
		buttonList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				autoMethods.connect();
				autoTable = autoMethods.readData();
				autoMethods.disConnect();
				AutoList list = new AutoList(Program.this, autoTable);
				list.setVisible(true);
			}
		});
		buttonList.setBounds(24, 29, 195, 23);
		contentPane.add(buttonList);
		
		Object datas[] = {"Jel", "ID", "Rendszam", "Tipus", "Forgalmi", "Fogyasztas" };
		autoTable = new AutoTable(datas, 0);
		
		JButton buttonNewData = new JButton("\u00DAj adatsor");
		buttonNewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewAuto newData = new NewAuto();
				newData.setVisible(true);
			}
		});
		buttonNewData.setBounds(24, 76, 195, 23);
		contentPane.add(buttonNewData);
		
		JButton buttonDelete = new JButton("Aut\u00F3 t\u00F6rl\u00E9se");
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				autoMethods.connect();
				autoTable = autoMethods.readData();
				autoMethods.disConnect();
				AutoDel delete = new AutoDel(Program.this, autoTable);
				delete.setVisible(true);
				
			}
		});
		buttonDelete.setBounds(24, 123, 195, 23);
		contentPane.add(buttonDelete);
		
		JButton buttonModify = new JButton("Aut\u00F3 m\u00F3dos\u00EDt\u00E1sa");
		buttonModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				autoMethods.connect();
				autoTable = autoMethods.readData();
				autoMethods.disConnect();
				AutoMod modify = new AutoMod(Program.this, autoTable);
				modify.setVisible(true);
			}
		});
		buttonModify.setBounds(24, 171, 195, 23);
		contentPane.add(buttonModify);
		
		JTextPane corpDescription = new JTextPane();
		corpDescription.setBackground(UIManager.getColor("Panel.background"));
		corpDescription.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 13));
		corpDescription.setText("Az al\u00E1bbi program a K\u00DCber Kft. c\u00E9gesaut\u00F3 adatb\u00E1zis\u00E1nak kezel\u00E9s\u00E9re k\u00E9sz\u00FClt. \rK\u00E9rem, v\u00E1lasszon az al\u00E1bbi opci\u00F3k k\u00F6z\u00FCl!");
		corpDescription.setBounds(229, 65, 195, 149);
		contentPane.add(corpDescription);
		
	


}
}


