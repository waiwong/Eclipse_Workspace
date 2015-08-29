package jsp.sqlServer.bean;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import jsp.sqlServer.database.DBConnect;

public class Intro {
	
	private int ID;
	
	private String Title;
	
	private String Type;
	
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

	public String getType() {
		return Type;
	}

	public void setType(String Type) {
		this.Type = Type;
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
	public int addIntro() throws Exception {

		String sql = "insert into t_intro (Title,Type,Detail,Path)"
			        +"Values(?,?,?,?)";
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Title);
		dbc.setString(2,Type);
		dbc.setString(3,Detail);
		dbc.setString(4,Path);

		int flag = dbc.executeUpdate();		
		dbc.close();
		return flag;		
		
	}
	
	// delete record
	public int deleteIntro() throws Exception {
		String sql = "delete from t_intro where ID='" + ID + "'";
		DBConnect dbc = new DBConnect(sql);
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// modify record
	public int modifyIntro() throws Exception {
		String sql = "update t_intro set Title=?,Type=?,Detail=?,Path=? where ID = ?"; 
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Title);
		dbc.setString(2,Type);
		dbc.setString(3,Detail);
		dbc.setString(4,Path);
		dbc.setInt(5,ID);
	
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// get a intro object by searching intro_id
	public Intro getIntroByIntroID() throws IOException {
		try {
			String sql = "select * from t_intro where ID = ?";
			DBConnect dbc = new DBConnect();
			dbc.prepareStatement(sql);
			dbc.setInt(1,ID);
			ResultSet rs = dbc.executeQuery();

			if (rs.next()) {
				Intro intro = new Intro();
				intro.setID(rs.getInt(1));
				intro.setTitle(rs.getString(2));
				intro.setType(rs.getString(3));
				intro.setDetail(rs.getString(4));
				intro.setPath(rs.getString(5));
				dbc.close();
				return intro;
			}
			else
				dbc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//get all intro
	public Vector getAllIntro() throws Exception {
		String sql = "select * from t_intro";

		try {
			DBConnect dbc = new DBConnect();
			ResultSet rs = dbc.executeQuery(sql);
			Vector introVector = new Vector();
			while(rs.next()){
				Intro intro = new Intro();
				intro.setID(rs.getInt(1));
				intro.setTitle(rs.getString(2));
				intro.setType(rs.getString(3));
				intro.setDetail(rs.getString(4));
				intro.setPath(rs.getString(5));			
				introVector.add(intro);
			}
			dbc.close();
			return introVector;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	

}
