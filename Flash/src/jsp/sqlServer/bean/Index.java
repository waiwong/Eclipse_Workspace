package jsp.sqlServer.bean;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import jsp.sqlServer.database.DBConnect;

public class Index {
	
	private int ID;
	
	private String Title;
	
	private String Type;
	
	private String Content;
	
	private String Photo;
	
	private String SWF;

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
	
	public String getContent() {
		return Content;
	}

	public void setContent(String Content) {
		this.Content = Content;
	}
	
	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String Photo) {
		this.Photo = Photo;
	}
	
	public String getSWF() {
		return SWF;
	}

	public void setSWF(String SWF) {
		this.SWF = SWF;
	}
	
	//	 add record
	public int addIndex() throws Exception {

		String sql = "insert into t_index (Title,Type,Content,Photo,SWF)"
			        +"Values(?,?,?,?,?)";
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Title);
		dbc.setString(2,Type);
		dbc.setString(3,Content);
		dbc.setString(4,Photo);
		dbc.setString(5,SWF);

		int flag = dbc.executeUpdate();		
		dbc.close();
		return flag;		
		
	}
	
	// delete record
	public int deleteIndex() throws Exception {
		String sql = "delete from t_index where ID='" + ID + "'";
		DBConnect dbc = new DBConnect(sql);
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// modify record
	public int modifyIndex() throws Exception {
		String sql = "update t_index set Title=?,Type=?,Content=?,Photo=?,"
			        +"SWF=? where ID = ?"; 
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Title);
		dbc.setString(2,Type);
		dbc.setString(3,Content);
		dbc.setString(4,Photo);
		dbc.setString(5,SWF);
		dbc.setInt(6,ID);
	
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// get a object by searching index_id
	public Index getIndexByIndexID() throws IOException {
		try {
			String sql = "select * from t_index where ID = ?";
			DBConnect dbc = new DBConnect();
			dbc.prepareStatement(sql);
			dbc.setInt(1,ID);
			ResultSet rs = dbc.executeQuery();

			if (rs.next()) {
				Index index = new Index();
				index.setID(rs.getInt(1));
				index.setTitle(rs.getString(2));
				index.setType(rs.getString(3));
				index.setContent(rs.getString(4));
				index.setPhoto(rs.getString(5));
				index.setSWF(rs.getString(6));
				dbc.close();
				return index;
			}
			else
				dbc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//get all index
	public Vector getAllIndex() throws Exception {
		String sql = "select * from t_index";

		try {
			DBConnect dbc = new DBConnect();
			ResultSet rs = dbc.executeQuery(sql);
			Vector indexVector = new Vector();
			while(rs.next()){
				Index index = new Index();
				index.setID(rs.getInt(1));
				index.setTitle(rs.getString(2));
				index.setType(rs.getString(3));
				index.setContent(rs.getString(4));
				index.setPhoto(rs.getString(5));
				index.setSWF(rs.getString(6));				
				indexVector.add(index);
			}
			dbc.close();
			return indexVector;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	

}
