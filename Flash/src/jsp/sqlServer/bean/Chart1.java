package jsp.sqlServer.bean;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import jsp.sqlServer.database.DBConnect;

public class Chart1 {
	
	private int ID;
	
	private String Name;
	
	private String Percent;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getPercent() {
		return Percent;
	}

	public void setPercent(String Percent) {
		this.Percent = Percent;
	}
	
	//	 add record
	public int addChart1() throws Exception {

		String sql = "insert into t_chart1(Name,Percentage) Values(?,?)";
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Name);
		dbc.setString(2,Percent);

		int flag = dbc.executeUpdate();		
		dbc.close();
		return flag;		
		
	}
	
	// delete record
	public int deleteChart1() throws Exception {
		String sql = "delete from t_chart1 where ID='" + ID + "'";
		DBConnect dbc = new DBConnect(sql);
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// modify record
	public int modifyChart1() throws Exception {
		String sql = "update t_chart1 set Name=?,Percentage=? where ID = ?"; 
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Name);
		dbc.setString(2,Percent);
		dbc.setInt(3,ID);
	
		int flag=dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// get a chart object by searching chart_id
	public Chart1 getChart1ByChart1ID() throws IOException {
		try {
			String sql = "select * from t_chart1 where ID = ?";
			DBConnect dbc = new DBConnect();
			dbc.prepareStatement(sql);
			dbc.setInt(1,ID);
			ResultSet rs = dbc.executeQuery();

			if (rs.next()) {
				Chart1 chart1 = new Chart1();
				chart1.setID(rs.getInt(1));
				chart1.setName(rs.getString(2));
				chart1.setPercent(rs.getString(3));
				dbc.close();
				return chart1;
			}
			else
				dbc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//get all chart1
	public Vector getAllChart1() throws Exception {
		String sql = "select * from t_chart1";

		try {
			DBConnect dbc=new DBConnect();
			ResultSet rs=dbc.executeQuery(sql);
			Vector chart1Vector = new Vector();
			while(rs.next()){
				Chart1 chart1 = new Chart1();
				chart1.setID(rs.getInt(1));
				chart1.setName(rs.getString(2));
				chart1.setPercent(rs.getString(3));				
				chart1Vector.add(chart1);
			}
			dbc.close();
			return chart1Vector;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	

}
