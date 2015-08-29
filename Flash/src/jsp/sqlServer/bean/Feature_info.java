package jsp.sqlServer.bean;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import jsp.sqlServer.database.DBConnect;

public class Feature_info {
	
	private int ID;
	
	private int NO;
	
	private String Title;
	
	private String Summary;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getNO() {
		return NO;
	}

	public void setNO(int NO) {
		this.NO = NO;
	}
	
	public String getTitle() {
		return Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String Summary) {
		this.Summary = Summary;
	}
	
	//	 add record
	public int addFeature_info() throws Exception {

		String sql = "insert into t_feature_info(NO,Title,Summary) Values(?,?,?)";
		DBConnect dbc = new DBConnect(sql);
		dbc.setInt(1,NO);
		dbc.setString(2,Title);
		dbc.setString(3,Summary);

		int flag = dbc.executeUpdate();		
		dbc.close();
		return flag;		
		
	}
	
	// delete record
	public int deleteFeature_info() throws Exception {
		String sql = "delete from t_feature_info where ID='" + ID + "'";
		DBConnect dbc = new DBConnect(sql);
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// modify record
	public int modifyFeature_info() throws Exception {
		String sql = "update t_feature_info set NO=?,Title=?,Summary=? where ID = ?"; 
		DBConnect dbc = new DBConnect(sql);
		dbc.setInt(1,NO);
		dbc.setString(2,Title);
		dbc.setString(3,Summary);
		dbc.setInt(4,ID);
	
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// get a feature_info object by searching feature_info_id
	public Feature_info getFeature_infoByFeature_infoID() throws IOException {
		try {
			String sql = "select * from t_feature_info where ID = ?";
			DBConnect dbc = new DBConnect();
			dbc.prepareStatement(sql);
			dbc.setInt(1,ID);
			ResultSet rs = dbc.executeQuery();

			if (rs.next()) {
				Feature_info feature_info = new Feature_info();
				feature_info.setID(rs.getInt(1));
				feature_info.setNO(rs.getInt(2));
				feature_info.setTitle(rs.getString(3));
				feature_info.setSummary(rs.getString(4));
				dbc.close();
				return feature_info;
			}
			else
				dbc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//get all feature_info
	public Vector getAllFeature_info() throws Exception {
		String sql = "select * from t_feature_info";

		try {
			DBConnect dbc=new DBConnect();
			ResultSet rs=dbc.executeQuery(sql);
			Vector feature_infoVector = new Vector();
			while(rs.next()){
				Feature_info feature_info = new Feature_info();
				feature_info.setID(rs.getInt(1));
				feature_info.setNO(rs.getInt(2));
				feature_info.setTitle(rs.getString(3));
				feature_info.setSummary(rs.getString(4));				
				feature_infoVector.add(feature_info);
			}
			dbc.close();
			return feature_infoVector;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	

}
