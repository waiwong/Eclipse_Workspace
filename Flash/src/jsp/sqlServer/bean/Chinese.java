package jsp.sqlServer.bean;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import jsp.sqlServer.database.DBConnect;

public class Chinese {
	
	private int ID;
	
	private String Title;
	
	private String Detail;
	
	private String Path;

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
	
	public String getDetail() {
		return Detail;
	}

	public void setDetail(String Detail) {
		this.Detail = Detail;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String Path) {
		this.Path = Path;
	}
	
	
	//	 add record
	public int addChinese() throws Exception {

		String sql = "insert into t_chinese (Title,Detail,Photo)"
			        +"Values(?,?,?)";
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Title);
		dbc.setString(2,Detail);
		dbc.setString(3,Path);

		int flag = dbc.executeUpdate();		
		dbc.close();
		return flag;		
		
	}
	
	// delete record
	public int deleteChinese() throws Exception {
		String sql = "delete from t_chinese where ID='" + ID + "'";
		DBConnect dbc = new DBConnect(sql);
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// modify record
	public int modifyChinese() throws Exception {
		String sql = "update t_chinese set Title=?,Detail=?,Photo=? where ID = ?"; 
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Title);
		dbc.setString(2,Detail);
		dbc.setString(3,Path);
		dbc.setInt(4,ID);
	
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// get a chinese object by searching chinese_id
	public Chinese getChineseByChineseID() throws IOException {
		try {
			String sql = "select * from t_chinese where ID = ?";
			DBConnect dbc = new DBConnect();
			dbc.prepareStatement(sql);
			dbc.setInt(1,ID);
			ResultSet rs = dbc.executeQuery();

			if (rs.next()) {
				Chinese chinese = new Chinese();
				chinese.setID(rs.getInt(1));
				chinese.setTitle(rs.getString(2));
				chinese.setDetail(rs.getString(3));
				chinese.setPath(rs.getString(4));
				dbc.close();
				return chinese;
			}
			else
				dbc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//get all chinese
	public Vector getAllChinese() throws Exception {
		String sql = "select * from t_chinese";

		try {
			DBConnect dbc = new DBConnect();
			ResultSet rs = dbc.executeQuery(sql);
			Vector chineseVector = new Vector();
			while(rs.next()){
				Chinese chinese = new Chinese();
				chinese.setID(rs.getInt(1));
				chinese.setTitle(rs.getString(2));
				chinese.setDetail(rs.getString(3));
				chinese.setPath(rs.getString(4));			
				chineseVector.add(chinese);
			}
			dbc.close();
			return chineseVector;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	

}
