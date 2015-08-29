package jsp.sqlServer.bean;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import jsp.sqlServer.database.DBConnect;

public class Works {
	
	private int ID;
	
	private String Title;
	
	private String Level1;
	
	private String Level2;
	
	private String Level3;
	
	private String Author;
	
	private String Photo;
	
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

	public String getLevel1() {
		return Level1;
	}

	public void setLevel1(String Level1) {
		this.Level1 = Level1;
	}
	
	public String getLevel2() {
		return Level2;
	}

	public void setLevel2(String Level2) {
		this.Level2 = Level2;
	}
	
	
	public String getLevel3() {
		return Level3;
	}

	public void setLevel3(String Level3) {
		this.Level3 = Level3;
	}
	
	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String Photo) {
		this.Photo = Photo;
	}
	
	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String Author) {
		this.Author = Author;
	}
	
	public String getPath() {
		return Path;
	}

	public void setPath(String Path) {
		this.Path = Path;
	}
	
	//	 add record
	public int addWorks() throws Exception {

		String sql = "insert into t_works (Title,Level1,Level2,Level3,Author,Photo,SWF)"
			        +"Values(?,?,?,?,?,?,?)";
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Title);
		dbc.setString(2,Level1);
		dbc.setString(3,Level2);
		dbc.setString(4,Level3);
		dbc.setString(5,Author);
		dbc.setString(6,Photo);
		dbc.setString(7,Path);

		int flag = dbc.executeUpdate();		
		dbc.close();
		return flag;		
		
	}
	
	// delete record
	public int deleteWorks() throws Exception {
		String sql = "delete from t_works where ID='" + ID + "'";
		DBConnect dbc = new DBConnect(sql);
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// modify record
	public int modifyWorks() throws Exception {
		String sql = "update t_works set Title=?,Level1=?,Level2=?,Level3=?,Author=?,"
			        +"Photo=?,SWF=? where ID = ?"; 
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Title);
		dbc.setString(2,Level1);
		dbc.setString(3,Level2);
		dbc.setString(4,Level3);
		dbc.setString(5,Author);
		dbc.setString(6,Photo);
		dbc.setString(7,Path);
		dbc.setInt(8,ID);
	
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// get works object by searching works_id
	public Works getWorksByWorksID() throws IOException {
		try {
			String sql = "select * from t_works where ID = ?";
			DBConnect dbc = new DBConnect();
			dbc.prepareStatement(sql);
			dbc.setInt(1,ID);
			ResultSet rs = dbc.executeQuery();

			if (rs.next()) {
				Works works = new Works();
				works.setID(rs.getInt(1));
				works.setTitle(rs.getString(2));
				works.setLevel1(rs.getString(3));
				works.setLevel2(rs.getString(4));
				works.setLevel3(rs.getString(5));
				works.setAuthor(rs.getString(6));
				works.setPhoto(rs.getString(7));
				works.setPath(rs.getString(8));
				dbc.close();
				return works;
			}
			else
				dbc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//get all works
	public Vector getAllWorks() throws Exception {
		String sql = "select * from t_works";

		try {
			DBConnect dbc = new DBConnect();
			ResultSet rs = dbc.executeQuery(sql);
			Vector worksVector = new Vector();
			while(rs.next()){
				Works works = new Works();
				works.setID(rs.getInt(1));
				works.setTitle(rs.getString(2));
				works.setLevel1(rs.getString(3));
				works.setLevel2(rs.getString(4));
				works.setLevel3(rs.getString(5));
				works.setAuthor(rs.getString(6));
				works.setPhoto(rs.getString(7));
				works.setPath(rs.getString(8));		
				worksVector.add(works);
			}
			dbc.close();
			return worksVector;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	
	public Vector getLevel() throws Exception {
		String sql = "select Distinct Level1 from t_works Order By Level1 DESC";

		try {
			DBConnect dbc = new DBConnect();
			ResultSet rs = dbc.executeQuery(sql);
			Vector worksVector = new Vector();
			while(rs.next()){
				Works works = new Works();
				works.setLevel1(rs.getString(1));
				worksVector.add(works);
			}
			dbc.close();
			return worksVector;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	public Vector searchWorks(String param,String value) throws IOException
	{
		if(param!=null&&value!=null&&(param.equals("第一代闪客")||param.equals("第二代闪客")||param.equals("新生代闪客")||param.equals("国外闪客")))
		{
			String sql="select * from t_works where Level1='" +param+"'"
			           +"and Author like '%"+value+"%'";
			try {
				DBConnect dbc = new DBConnect();
				ResultSet rs=dbc.executeQuery(sql);
				Vector worksVector = new Vector();
				while (rs.next()) {
					Works works=new Works();
					works.setID(rs.getInt(1));
					works.setTitle(rs.getString(2));
					works.setLevel1(rs.getString(3));
					works.setLevel2(rs.getString(4));
					works.setLevel3(rs.getString(5));
					works.setAuthor(rs.getString(6));
					works.setPhoto(rs.getString(7));
					works.setPath(rs.getString(8));	
					worksVector.add(works);
				}
				dbc.close();
				return worksVector;
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return null;
		}
		else
			return null;
	}		
}
