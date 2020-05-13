import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;


public class AutoMethods {
	private Statement statement = null; 
	private Connection connection = null;
	private ResultSet resultSet = null; 
	
	public void Reg(){
		try{
			Class.forName("org.sqlite.JDBC");
			//showMessage("Sikeres driver regisztráció!", 1);
		}
		catch (ClassNotFoundException except){
			showMessage("Hibás driver regisztráció!"+except.getMessage(), 0);
		}
	}
	
	public void showMessage (String message, int tipus){
		JOptionPane.showMessageDialog(null, message, "Program üzenet", tipus);
	}
	
	public void connect(){
		try{
			String url = "jdbc:sqlite:C:/jdbc_uj/Cegesauto";
			connection = DriverManager.getConnection(url);
			//showMessage("Connection OK!", 1);
		}catch (SQLException sqlExcept){
			showMessage("JDBC Connect: "+sqlExcept.getMessage(), 0);}
	}
	public void disConnect(){
		try{
			connection.close();
			//showMessage("DisConnection OK!", 1);
		}catch (SQLException sqlExcept){showMessage(sqlExcept.getMessage(), 0);}
	}
	
	public AutoTable readData() {
		Object datas[] = {"Jel", "ID", "Rendszam", "Tipus", "Forgalmi", "Fogyasztas"};
		AutoTable autoTable = new AutoTable(datas, 0);
		String rendszam="", tipus="", forgalmi="", space="\t"; 
		int id=0, fogyasztas=0; 
		String sqlCommand= "Select id, rendszam, tipus, forgalmi, fogyasztas from Cegesauto";
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlCommand);
			while(resultSet.next()){
				id = resultSet.getInt("id");
				rendszam = resultSet.getString("rendszam");
				tipus = resultSet.getString("tipus");
				forgalmi = resultSet.getString("forgalmi");
				fogyasztas = resultSet.getInt("fogyasztas");
				autoTable.addRow(new Object[] {false, id, rendszam, tipus, forgalmi, fogyasztas});
				System.out.println(id+space+rendszam+space+tipus+space+forgalmi+space+fogyasztas);
			}
			resultSet.close();
		}catch (SQLException sqlExcept) {showMessage(sqlExcept.getMessage(), 0);
	
		}
		return autoTable;
	}
	public void insertData(String id, String rendszam, String tipus, String forgalmi, String fogyasztas ) {
		
		try (PreparedStatement statement = connection.prepareStatement("insert into Cegesauto values(?,?,?,?,?)")) {
			statement.setString(1, id);
			statement.setString(2, rendszam);
			statement.setString(3, tipus);
			statement.setString(4, forgalmi);
			statement.setString(5, fogyasztas);
			statement.executeUpdate();
			showMessage("Új jármû sikeresen létrehozva!",1);
			} catch (SQLException sqlExcept) {
				showMessage("JDBC insert: " + sqlExcept.getMessage(), 0);
			}

		
	}
	public void delData (String ID){
		String sqlCommand = "delete from Cegesauto where ID="+ ID; 
		try{
			statement = connection.createStatement();
			statement.execute(sqlCommand);
		}catch (SQLException sqlExcept){
			showMessage("JDBC Delete: "+sqlExcept.getMessage(), 0);
		}
	}
	public void updateData (String ID, String fieldName, String fieldData ){
		String sqlCommand = "update Cegesauto set "+fieldName+ "='" +fieldData+"'where ID="+ID;
		try{
			statement = connection.createStatement();
			statement.execute(sqlCommand);
		}catch (SQLException sqlExcept){
			showMessage("JDBC Update: "+sqlExcept.getMessage(),0);
		}
	}

}
