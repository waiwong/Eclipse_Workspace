package jsp.sqlServer.bean;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import jsp.sqlServer.database.DBConnect;

public class Feature {
	
	private int ID;
	
	private String Title;
	
	private String Type;
	
	private String Summary;
	
	private String Path;
	
	private String URL;
	
	private String Photo;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String getTitle() {
		return Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public String getType() {
		return Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}
	
	public String getSummary() {
		return Summary;
	}

	public void setSummary(String Summary) {
		this.Summary = Summary;
	}
	
	public String getPath() {
		return Path;
	}

	public void setPath(String Path) {
		this.Path = Path;
	}
	
	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}
		
	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String Photo) {
		this.Photo = Photo;
	}
	
	//	 add record
	public int addFeature() throws Exception {

		String sql = "insert into t_feature(Title,Type,Summary,SWF,URL,Photo)"
		             +"Values(?,?,?,?,?,?)";
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Title);
		dbc.setString(2,Type);
		dbc.setString(3,Summary);
		dbc.setString(4,Path);
		dbc.setString(5,URL);
		dbc.setString(6,Photo);
		
		int flag = dbc.executeUpdate();		
		dbc.close();
		return flag;				
	}
	
	// delete record
	public int deleteFeature() throws Exception {
		String sql = "delete from t_feature where ID='" + ID + "'";
		DBConnect dbc = new DBConnect(sql);
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// modify record
	public int modifyFeature() throws Exception {
		String sql = "update t_feature set Title=?,Type=?,Summary=?,"
			         +"SWF=?,URL=?,Photo=? where ID = ?"; 
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Title);
		dbc.setString(2,Type);
		dbc.setString(3,Summary);
		dbc.setString(4,Path);
		dbc.setString(5,URL);
		dbc.setString(6,Photo);
		dbc.setInt(7,ID);
	
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// get a feature object by searching feature_id
	public Feature getFeatureByFeatureID() throws IOException {
		try {
			String sql = "select * from t_feature where ID = ?";
			DBConnect dbc = new DBConnect();
			dbc.prepareStatement(sql);
			dbc.setInt(1,ID);
			ResultSet rs = dbc.executeQuery();

			if (rs.next()) {
				Feature feature = new Feature();
				feature.setID(rs.getInt(1));
				feature.setTitle(rs.getString(2));
				feature.setType(rs.getString(3));
				feature.setSummary(rs.getString(4));
				feature.setPath(rs.getString(5));
				feature.setURL(rs.getString(6));
				feature.setPhoto(rs.getString(7));
				dbc.close();
				return feature;
			}
			else
				dbc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//get all feature
	public Vector getAllFeature() throws Exception {
		String sql = "select * from t_feature";

		try {
			DBConnect dbc=new DBConnect();
			ResultSet rs=dbc.executeQuery(sql);
			Vector featureVector = new Vector();
			while(rs.next()){
				Feature feature = new Feature();
				feature.setID(rs.getInt(1));
				feature.setTitle(rs.getString(2));
				feature.setType(rs.getString(3));
				feature.setSummary(rs.getString(4));
				feature.setPath(rs.getString(5));
				feature.setURL(rs.getString(6));
				feature.setPhoto(rs.getString(7));
				featureVector.add(feature);
			}
			dbc.close();
			return featureVector;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
