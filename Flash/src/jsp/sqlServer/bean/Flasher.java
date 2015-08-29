package jsp.sqlServer.bean;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import jsp.sqlServer.database.DBConnect;

public class Flasher {
	
	private int ID;
	
	private String Nickname;
	
	private String Truename;
	
	private String Kind;
	
	private String First;
	
	private String Classic;
	
	private String Photo;
	
	private String Detail;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String getNickname() {
		return Nickname;
	}

	public void setNickname(String Nickname) {
		this.Nickname = Nickname;
	}

	public String getTruename() {
		return Truename;
	}

	public void setTruename(String Truename) {
		this.Truename = Truename;
	}
	
	public String getKind() {
		return Kind;
	}

	public void setKind(String Kind) {
		this.Kind = Kind;
	}
	
	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String Photo) {
		this.Photo = Photo;
	}
	
	public String getFirst() {
		return First;
	}

	public void setFirst(String First) {
		this.First = First;
	}
	
	public String getClassic() {
		return Classic;
	}

	public void setClassic(String Classic) {
		this.Classic = Classic;
	}
	
	public String getDetail() {
		return Detail;
	}

	public void setDetail(String Detail) {
		this.Detail = Detail;
	}
	
	//	 add record
	public int addFlasher() throws Exception {

		String sql = "insert into t_flasher (Nickname,Truename,Type,Photo,"
			         +"First,Classic,Detail) Values(?,?,?,?,?,?,?)";
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Nickname);
		dbc.setString(2,Truename);
		dbc.setString(3,Kind);
		dbc.setString(4,Photo);
		dbc.setString(5,First);
		dbc.setString(6,Classic);
		dbc.setString(7,Detail);

		int flag = dbc.executeUpdate();		
		dbc.close();
		return flag;		
		
	}
	
	// delete record
	public int deleteFlasher() throws Exception {
		String sql = "delete from t_flasher where ID='" + ID + "'";
		DBConnect dbc = new DBConnect(sql);
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// modify record
	public int modifyFlasher() throws Exception {
		String sql = "update t_flasher set Nickname=?,Truename=?,Type=?,"
			         +"Photo=?,First=?,Classic=?,Detail=? where ID = ?"; 
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,Nickname);
		dbc.setString(2,Truename);
		dbc.setString(3,Kind);
		dbc.setString(4,Photo);
		dbc.setString(5,First);
		dbc.setString(6,Classic);
		dbc.setString(7,Detail);
		dbc.setInt(8,ID);
	
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// get a flasher object by searching flasher_id
	public Flasher getFlasherByFlasherID() throws IOException {
		try {
			String sql = "select * from t_flasher where ID = ?";
			DBConnect dbc = new DBConnect();
			dbc.prepareStatement(sql);
			dbc.setInt(1,ID);
			ResultSet rs = dbc.executeQuery();

			if (rs.next()) {
				Flasher flasher = new Flasher();
				flasher.setID(rs.getInt(1));
				flasher.setNickname(rs.getString(2));
				flasher.setTruename(rs.getString(3));
				flasher.setKind(rs.getString(4));
				flasher.setPhoto(rs.getString(5));
				flasher.setFirst(rs.getString(6));
				flasher.setClassic(rs.getString(7));
				flasher.setDetail(rs.getString(8));
				dbc.close();
				return flasher;
			}
			else
				dbc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//get all index
	public Vector getAllFlasher() throws Exception {
		String sql = "select * from t_flasher";

		try {
			DBConnect dbc = new DBConnect();
			ResultSet rs = dbc.executeQuery(sql);
			Vector flasherVector = new Vector();
			while(rs.next()){
				Flasher flasher = new Flasher();
				flasher.setID(rs.getInt(1));
				flasher.setNickname(rs.getString(2));
				flasher.setTruename(rs.getString(3));
				flasher.setKind(rs.getString(4));
				flasher.setPhoto(rs.getString(5));
				flasher.setFirst(rs.getString(6));
				flasher.setClassic(rs.getString(7));
				flasher.setDetail(rs.getString(8));			
				flasherVector.add(flasher);
			}
			dbc.close();
			return flasherVector;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	public Vector getFlasherType() throws Exception {
		String sql = "select Distinct Type from t_flasher";

		try {
			DBConnect dbc = new DBConnect();
			ResultSet rs = dbc.executeQuery(sql);
			Vector flasherVector = new Vector();
			while(rs.next()){
				Flasher flasher = new Flasher();
				flasher.setKind(rs.getString(1));		
				flasherVector.add(flasher);
			}
			dbc.close();
			return flasherVector;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	

	public Vector searchFlasher(String param,String value) throws IOException
	{
		if(param!=null&&value!=null&&(param.equals("男闪客")||param.equals("女闪客")||param.equals("闪客工作室")))
		{
			String sql="select * from t_flasher where Type='" +param+"'"
			           +"and Nickname like '%"+value+"%' or Truename like '%"+value+"%'";
			try {
				DBConnect dbc = new DBConnect();
				ResultSet rs=dbc.executeQuery(sql);
				Vector flasherVector = new Vector();
				while(rs.next()){
					Flasher flasher = new Flasher();
					flasher.setID(rs.getInt(1));
					flasher.setNickname(rs.getString(2));
					flasher.setTruename(rs.getString(3));
					flasher.setKind(rs.getString(4));
					flasher.setPhoto(rs.getString(5));
					flasher.setFirst(rs.getString(6));
					flasher.setClassic(rs.getString(7));
					flasher.setDetail(rs.getString(8));			
					flasherVector.add(flasher);
				}
				dbc.close();
				return flasherVector;
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return null;
		}
		else
			return null;
	}		
}

