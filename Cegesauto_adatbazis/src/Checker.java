import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Checker {
	
	public boolean isFilled (JTextField data, String dataField){
		String dataString = RTextField(data);
		if (dataString.length()>0) return true;
		else{
			showMessage("Hiba: a(z) "+dataField+" mezõ üres!", 0);
			return false;
		}
	}
	
	public boolean goodInt(JTextField data, String dataField){
		String dataString = RTextField(data);
		boolean filled = isFilled (data, dataField);
		if (filled) try {
			Integer.parseInt(dataString);
		}catch (NumberFormatException numform){
			showMessage("A(z) "+dataField+" mezõben hibás számadat van!", 0);
			filled = false;
		}
		return filled;
	}
	public String RTextField(JTextField textField){
		return textField.getText();
	}
	
	public void showMessage(String message, int tipus){
		JOptionPane.showMessageDialog(null, message, "Program uzenet", tipus);
	}
	public boolean dateFormatChecker(String sDate) {
		SimpleDateFormat RDateForm= new SimpleDateFormat("yyyy.MM.dd");
		try {
			java.util.Date pDate = RDateForm.parse (sDate);
			if (!RDateForm.format(pDate).equals(sDate)) {return false;}
			return true;
		} catch(ParseException parseExcept) {return false;}
	}
	
	public boolean goodDate(JTextField data, String dataField) {
		String dateString=RTextField(data);
		boolean filled = isFilled (data,dataField);
		if (filled && dateFormatChecker(dateString))return true;
		else {
			showMessage ("A(z) "+dataField+" Mezõben hibás a dátum",0);
			return false;	
		}
	}
	public boolean filled (JTextField data){
		String dataString = RTextField(data);
		if (dataString.length()>0) return true;
		else return false;
	}
	public int stringToInt (String dataString){
		int monitorInt = -1;
		try{
			monitorInt=Integer.valueOf(dataString);
		}catch (NumberFormatException numform){
			showMessage("stringToInt: "+numform.getMessage(), 0);
		}
		return monitorInt;
	}

}
