import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class NewAuto extends JDialog {
	private JTextField ID;
	private JTextField Rendszam;
	private JTextField Tipus;
	private JTextField Forgalmi;
	private JTextField Fogyasztas;
	private AutoMethods autoMethods = new AutoMethods();
	
	
	public NewAuto() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel labelID = new JLabel("ID:");
		labelID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelID.setBounds(10, 32, 46, 14);
		getContentPane().add(labelID);
		
		ID = new JTextField();
		ID.setBounds(106, 31, 86, 20);
		getContentPane().add(ID);
		ID.setColumns(10);
		
		JLabel labelRendszam = new JLabel("Rendsz\u00E1m:");
		labelRendszam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelRendszam.setBounds(10, 70, 70, 20);
		getContentPane().add(labelRendszam);
		
		Rendszam = new JTextField();
		Rendszam.setBounds(106, 70, 86, 20);
		getContentPane().add(Rendszam);
		Rendszam.setColumns(10);
		
		JLabel labelTipus = new JLabel("T\u00EDpus:");
		labelTipus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelTipus.setBounds(10, 114, 70, 20);
		getContentPane().add(labelTipus);
		
		Tipus = new JTextField();
		Tipus.setBounds(106, 114, 86, 20);
		getContentPane().add(Tipus);
		Tipus.setColumns(10);
		
		JLabel labelForgalmi = new JLabel("Forgalmi:");
		labelForgalmi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelForgalmi.setBounds(10, 156, 86, 14);
		getContentPane().add(labelForgalmi);
		
		Forgalmi = new JTextField();
		Forgalmi.setBounds(106, 156, 86, 20);
		getContentPane().add(Forgalmi);
		Forgalmi.setColumns(10);
		
		JLabel labelFogyasztas = new JLabel("Fogyaszt\u00E1s:");
		labelFogyasztas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelFogyasztas.setBounds(10, 198, 86, 20);
		getContentPane().add(labelFogyasztas);
		
		Fogyasztas = new JTextField();
		Fogyasztas.setBounds(106, 198, 86, 20);
		getContentPane().add(Fogyasztas);
		Fogyasztas.setColumns(10);
		
		JButton buttonInsert = new JButton("Besz\u00FAr\u00E1s");
		buttonInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Checker check = new Checker();
				if (check.goodInt(ID, "ID"))
					if(check.isFilled (Rendszam, "Rendszam"))
						if (check.isFilled (Tipus, "Tipus"))
							if (check.goodDate (Forgalmi, "Forgalmi"))
								if (check.goodInt (Fogyasztas, "Fogyasztas")){
				autoMethods.connect();
				autoMethods.insertData(Rtextfield(ID), Rtextfield(Rendszam), Rtextfield(Tipus), Rtextfield(Forgalmi), Rtextfield(Fogyasztas));
				autoMethods.disConnect();	
				}
			}
		});
		buttonInsert.setBounds(296, 115, 89, 23);
		getContentPane().add(buttonInsert);
	}
		
		
		public String Rtextfield(JTextField data){
		return data.getText();
		}
}
