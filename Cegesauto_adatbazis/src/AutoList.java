import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.border.EmptyBorder;


public class AutoList extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private AutoTable autoTable;
	
	public AutoList(JFrame jFrame, AutoTable autoTableData){
		super (jFrame, "Autók listája", true);
		autoTable = autoTableData;
	
		
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JButton buttonClose = new JButton("Bez\u00E1r");
			buttonClose.setBounds(191, 227, 89, 23);
			buttonClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				dispose();
			}
			}
			);
			getContentPane().add(buttonClose);
		
		

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 484, 219);
		getContentPane().add(scrollPane);
		
		table = new JTable(autoTable);
		scrollPane.setViewportView(table);

	}
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

