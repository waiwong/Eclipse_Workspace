package jsp.sqlServer.bean;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import jsp.sqlServer.database.DBConnect;

public class Skill {
	
	private int ID;
	
	private int NO;
	
	private String Label;
	
	private String subLabel;
	
	private String Type;
	
	private String Summary;
	
	private String Code;
	
	private String Path;

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
	
	public String getLabel() {
		return Label;
	}

	public void setLabel(String Label) {
		this.Label = Label;
	}

	public String getsubLabel() {
		return subLabel;
	}

	public void setsubLabel(String subLabel) {
		this.subLabel = subLabel;
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
		
	public String getCode() {
		return Code;
	}

	public void setCode(String Code) {
		this.Code = Code;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String Path) {
		this.Path = Path;
	}
	
	//	 add record
	public int addSkill() throws Exception {

		String sql = "insert into t_skill(NO,Label,subLabel,Type,Summary,Code,Path)"
			         +"Values(?,?,?,?,?,?,?)";
		DBConnect dbc = new DBConnect(sql);
		dbc.setInt(1,NO);
		dbc.setString(2,Label);
		dbc.setString(3,subLabel);
		dbc.setString(4,Type);
		dbc.setString(5,Summary);
		dbc.setString(6,Code);
		dbc.setString(7,Path);

		int flag = dbc.executeUpdate();		
		dbc.close();
		return flag;		
		
	}
	
	// delete record
	public int deleteSkill() throws Exception {
		String sql = "delete from t_skill where ID='" + ID + "'";
		DBConnect dbc = new DBConnect(sql);
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// modify record
	public int modifySkill() throws Exception {
		String sql = "update t_skill set NO=?,Label=?,subLabel=?,Type=?,Summary=?," 
			         +"Code=?,Path=? where ID = ?"; 
		DBConnect dbc = new DBConnect(sql);
		dbc.setInt(1,NO);
		dbc.setString(2,Label);
		dbc.setString(3,subLabel);
		dbc.setString(4,Type);
		dbc.setString(5,Summary);
		dbc.setString(6,Code);
		dbc.setString(7,Path);
		dbc.setInt(8,ID);
	
		int flag=dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// get a skill object by searching skill_id
	public Skill getSkillBySkillID() throws IOException {
		try {
			String sql = "select * from t_skill where ID = ?";
			DBConnect dbc = new DBConnect();
			dbc.prepareStatement(sql);
			dbc.setInt(1,ID);
			ResultSet rs = dbc.executeQuery();

			if (rs.next()) {
				Skill skill = new Skill();
				skill.setID(rs.getInt(1));
				skill.setNO(rs.getInt(2));
				skill.setLabel(rs.getString(3));
				skill.setsubLabel(rs.getString(4));
				skill.setType(rs.getString(5));
				skill.setSummary(rs.getString(6));
				skill.setCode(rs.getString(7));
				skill.setPath(rs.getString(8));
				dbc.close();
				return skill;
			}
			else
				dbc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//get all skill
	public Vector getAllSkill() throws Exception {
		String sql = "select * from t_skill";

		try {
			DBConnect dbc=new DBConnect();
			ResultSet rs=dbc.executeQuery(sql);
			Vector skillVector = new Vector();
			while(rs.next()){
				Skill skill = new Skill();
				skill.setID(rs.getInt(1));
				skill.setNO(rs.getInt(2));
				skill.setLabel(rs.getString(3));
				skill.setsubLabel(rs.getString(4));
				skill.setType(rs.getString(5));
				skill.setSummary(rs.getString(6));
				skill.setCode(rs.getString(7));
				skill.setPath(rs.getString(8));			
				skillVector.add(skill);
			}
			dbc.close();
			return skillVector;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
