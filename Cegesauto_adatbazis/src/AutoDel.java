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


public class AutoDel extends JDialog {
	private AutoTable autoTable;
	private JTable table;
	private Checker check = new Checker(); 
	private AutoMethods autoMethods = new AutoMethods(); 
	private final JPanel contentPanel = new JPanel(); 
	public AutoDel(JFrame jFrame, AutoTable autoTableData){
		super (jFrame, "Autók törlése", true);
		autoTable = autoTableData;
	
		
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		
		JButton buttonClose = new JButton("Bez\u00E1r");
		buttonClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0){
					dispose();
			}
		});
		buttonClose.setBounds(297, 216, 117, 23);
		getContentPane().add(buttonClose);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 11, 452, 174);
		getContentPane().add(scrollPane);
		
		table = new JTable(autoTable);
		scrollPane.setViewportView(table);
		
		JButton buttonDelData = new JButton("T\u00F6rl\u00E9s");
		buttonDelData.setBounds(58, 216, 129, 23);
		buttonDelData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int checkBox=0, index=0, i=0;
				for(i = 0; i<autoTable.getRowCount();i++)
					if ((Boolean)autoTable.getValueAt(i,0)) {checkBox++; index=i;}
					if (checkBox==0) check.showMessage("Nincs kijelölve törlendõ rekord!", 0);
					if (checkBox>1) check.showMessage("Több rekord van kijelölve! (Egyszerre csak egy törölhetõ!)", 0); 
					if (checkBox==1) {
						String ID= autoTable.getValueAt(index, 1).toString();
						autoMethods.connect();
						autoMethods.delData(ID);
						autoMethods.disConnect();
						autoTable.removeRow(index);
						check.showMessage("A rekord törölve",1);
					}
			}
		});
		getContentPane().add(buttonDelData);
		contentPanel.setLayout(null);
		
		
		TableColumn tableCol = null; 
		for (int i = 0; i < 6; i++){
			tableCol = table.getColumnModel().getColumn(i);
			if (i==0 || i==1) tableCol.setPreferredWidth(30);
			else if (i==5) tableCol.setPreferredWidth(60);
			else if (i==2 || i==4) tableCol.setPreferredWidth(90);
			else {tableCol.setPreferredWidth(140);
			table.setAutoCreateRowSorter(true);
			TableRowSorter<AutoTable> tableRowSorter = (TableRowSorter<AutoTable>)table.getRowSorter();
			tableRowSorter.setSortable(0, false);
		}
			
		}
		}
	

}

