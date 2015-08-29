package jsp.sqlServer.bean;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import jsp.sqlServer.database.DBConnect;

public class Chart2 {
	
	private int ID;
	
	private String Region;
	
	private String FP10;
	
	private String FP9;
	
	private String FP8;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String getRegion() {
		return Region;
	}

	public void setRegion(String Region) {
		this.Region = Region;
	}

	public String getFP10() {
		return FP10;
	}

	public void setFP10(String FP10) {
		this.FP10 = FP10;
	}
	
	public String getFP9() {
		return FP9;
	}

	public void setFP9(String FP9) {
		this.FP9 = FP9;
	}
	
	public String getFP8() {
		return FP8;
	}

	public void setFP8(String FP8) {
		this.FP8 = FP8;
	}
	
	//	 add record
	public int addChart2() throws Exception {

		String sql = "insert into t_chart2(Region,FP10,FP9,FP8) Values(?,?,?,?)";
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Region);
		dbc.setString(2,FP10);
		dbc.setString(3,FP9);
		dbc.setString(4,FP8);

		int flag = dbc.executeUpdate();		
		dbc.close();
		return flag;		
		
	}
	
	// delete record
	public int deleteChart2() throws Exception {
		String sql = "delete from t_chart2 where ID='" + ID + "'";
		DBConnect dbc = new DBConnect(sql);
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// modify record
	public int modifyChart2() throws Exception {
		String sql = "update t_chart2 set Region=?,FP10=?,FP9=?,FP8=? where ID = ?"; 
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Region);
		dbc.setString(2,FP10);
		dbc.setString(3,FP9);
		dbc.setString(4,FP8);
		dbc.setInt(5,ID);
	
		int flag=dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// get a chart object by searching chart2_id
	public Chart2 getChart2ByChart2ID() throws IOException {
		try {
			String sql = "select * from t_chart2 where ID = ?";
			DBConnect dbc = new DBConnect();
			dbc.prepareStatement(sql);
			dbc.setInt(1,ID);
			ResultSet rs = dbc.executeQuery();

			if (rs.next()) {
				Chart2 chart2 = new Chart2();
				chart2.setID(rs.getInt(1));
				chart2.setRegion(rs.getString(2));
				chart2.setFP10(rs.getString(3));
				chart2.setFP9(rs.getString(4));
				chart2.setFP8(rs.getString(5));
				dbc.close();
				return chart2;
			}
			else
				dbc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//get all chart2
	public Vector getAllChart2() throws Exception {
		String sql = "select * from t_chart2";

		try {
			DBConnect dbc=new DBConnect();
			ResultSet rs=dbc.executeQuery(sql);
			Vector chart2Vector = new Vector();
			while(rs.next()){
				Chart2 chart2 = new Chart2();
				chart2.setID(rs.getInt(1));
				chart2.setRegion(rs.getString(2));
				chart2.setFP10(rs.getString(3));
				chart2.setFP9(rs.getString(4));
				chart2.setFP8(rs.getString(5));			
				chart2Vector.add(chart2);
			}
			dbc.close();
			return chart2Vector;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	

}
