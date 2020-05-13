import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class AutoMod extends JDialog {
	private AutoTable autoTable;
	private JTable table;
	private Checker check = new Checker(); 
	private AutoMethods autoMethods = new AutoMethods(); 
	private final JPanel contentPanel = new JPanel(); 
	private JTextField ID;
	private JTextField Rendszam;
	private JTextField Tipus;
	private JTextField Forgalmi;
	private JTextField Fogyasztas;
	
	public AutoMod(JFrame jFrame, AutoTable autoTableData){
		super (jFrame, "Autók módosítása", true);
		autoTable = autoTableData;
			
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JButton buttonClose = new JButton("Bez\u00E1r");
		buttonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		buttonClose.setBounds(300, 227, 89, 23);
		getContentPane().add(buttonClose);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 11, 387, 155);
		getContentPane().add(scrollPane);
		
		table = new JTable(autoTable);
		scrollPane.setViewportView(table);
		
		JButton buttonModify = new JButton("M\u00F3dos\u00EDt\u00E1s");
		buttonModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
		int checkBox=0, index=0, i=0;
		for (i=0; i<autoTable.getRowCount(); i++)
			if ((Boolean)autoTable.getValueAt(i,0)) {checkBox++; index=i;}
		if (checkBox==0) check.showMessage("Nincs kijelölve a módosító rekord!",0);
		if (checkBox>1) check.showMessage("Több rekord van kijelölve!\nEgyszerre csak egy"+ " rekord módosítható!",0);
		if (checkBox==1){
			if (modDataCounter()>0){
				boolean ok= true;
				if (check.filled(ID)) ok = check.goodInt(ID, "ID");
				if (ok && check.filled(Fogyasztas)) ok = check.goodInt(Fogyasztas, "Fogyasztas");
				if (ok){
					
					String fieldID = autoTable.getValueAt(index, 1).toString();
					autoMethods.connect();
					if (check.filled(ID)) autoMethods.updateData(fieldID, "ID", check.RTextField(ID));
					if (check.filled(Rendszam)) autoMethods.updateData(fieldID, "Rendszam", check.RTextField(Rendszam));
					if (check.filled(Tipus)) autoMethods.updateData(fieldID, "Tipus", check.RTextField(Tipus));
					if (check.filled(Forgalmi)) autoMethods.updateData(fieldID, "Forgalmi", check.RTextField(Forgalmi));
					if (check.filled(Fogyasztas)) autoMethods.updateData(fieldID, "Fogyasztas", check.RTextField(Fogyasztas));
					autoMethods.disConnect();
					
					if (check.filled(ID)) autoTable.setValueAt(check.stringToInt(check.RTextField(ID)), index, 1);
					if (check.filled(Rendszam)) autoTable.setValueAt(check.RTextField(Rendszam), index, 2);
					if (check.filled(Tipus)) autoTable.setValueAt(check.RTextField(Tipus), index, 3);
					if (check.filled(Forgalmi)) autoTable.setValueAt(check.RTextField(Forgalmi), index, 4);
					if (check.filled(Fogyasztas)) autoTable.setValueAt(check.stringToInt(check.RTextField(Fogyasztas)), index, 5);
					check.showMessage("A rekord módosítva!",1);
				}
				else {
					check.showMessage("Nincs kitöltve egyetlen módosító adatmezõ sem!", 0);
				}
			}
		}
		}
		});
		buttonModify.setBounds(44, 227, 89, 23);
		getContentPane().add(buttonModify);
		
		ID = new JTextField();
		ID.setBounds(10, 194, 41, 20);
		getContentPane().add(ID);
		ID.setColumns(10);
		
		Rendszam = new JTextField();
		Rendszam.setBounds(61, 194, 72, 20);
		getContentPane().add(Rendszam);
		Rendszam.setColumns(10);
		
		Tipus = new JTextField();
		Tipus.setBounds(143, 194, 96, 20);
		getContentPane().add(Tipus);
		Tipus.setColumns(10);
		
		Forgalmi = new JTextField();
		Forgalmi.setBounds(249, 194, 86, 20);
		getContentPane().add(Forgalmi);
		Forgalmi.setColumns(10);
		
		Fogyasztas = new JTextField();
		Fogyasztas.setBounds(345, 196, 62, 20);
		getContentPane().add(Fogyasztas);
		Fogyasztas.setColumns(10);
		
		JLabel labelID = new JLabel("ID");
		labelID.setBounds(25, 177, 24, 14);
		getContentPane().add(labelID);
		
		JLabel labelRendszam = new JLabel("Rendsz\u00E1m");
		labelRendszam.setBounds(71, 177, 60, 14);
		getContentPane().add(labelRendszam);
		
		JLabel lblTpus = new JLabel("T\u00EDpus");
		lblTpus.setBounds(178, 177, 46, 14);
		getContentPane().add(lblTpus);
		
		JLabel labelForgalmi = new JLabel("Forgalmi");
		labelForgalmi.setBounds(271, 177, 62, 14);
		getContentPane().add(labelForgalmi);
		
		JLabel labelFogyasztas = new JLabel("Fogyaszt\u00E1s");
		labelFogyasztas.setBounds(348, 177, 74, 14);
		getContentPane().add(labelFogyasztas);
	}
	
	
		public int modDataCounter(){
			int counter =  0; 
			if (check.filled(ID)) counter++;
			if (check.filled(Rendszam)) counter++;
			if (check.filled(Tipus)) counter++;
			if (check.filled(Forgalmi)) counter++;
			if (check.filled(Fogyasztas)) counter++;
			return counter;
		}
		{
			
		}
		
	public void reset (int i){
		ID.setText("");
		Rendszam.setText("");
		Tipus.setText("");
		Forgalmi.setText("");
		Fogyasztas.setText("");
		autoTable.setValueAt(false, i, 0);
	}
}

