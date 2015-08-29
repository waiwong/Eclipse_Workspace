package jsp.sqlServer.bean;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import jsp.sqlServer.database.DBConnect;

public class Menu {
	
	private int ID;
	
	private String menu;
	
	private String url;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}
	
	//	 add record
	public int addMenu() throws Exception {

		String sql = "insert into t_menu(Menu,URL) Values(?,?)";
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,menu);
		dbc.setString(2,url);

		int flag = dbc.executeUpdate();		
		dbc.close();
		return flag;		
		
	}
	
	// delete record
	public int deleteMenu() throws Exception {
		String sql = "delete from t_menu where ID='" + ID + "'";
		DBConnect dbc = new DBConnect(sql);
		int flag = dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// modify record
	public int modifyMenu() throws Exception {
		String sql = "update t_menu set Menu=?,URL=? where ID = ?"; 
		DBConnect dbc = new DBConnect(sql);
		dbc.setString(1,menu);
		dbc.setString(2,url);
		dbc.setInt(3,ID);
	
		int flag=dbc.executeUpdate();
		dbc.close();
		return flag;
	}
	
	// get a menu object by searching menu_id
	public Menu getMenuByMenuID() throws IOException {
		try {
			String sql = "select * from t_menu where ID = ?";
			DBConnect dbc = new DBConnect();
			dbc.prepareStatement(sql);
			dbc.setInt(1,ID);
			ResultSet rs = dbc.executeQuery();

			if (rs.next()) {
				Menu menu = new Menu();
				menu.setID(rs.getInt(1));
				menu.setMenu(rs.getString(2));
				menu.setURL(rs.getString(3));
				dbc.close();
				return menu;
			}
			else
				dbc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//get all menu
	public Vector getAllMenu() throws Exception {
		String sql = "select * from t_menu";

		try {
			DBConnect dbc=new DBConnect();
			ResultSet rs=dbc.executeQuery(sql);
			Vector menuVector = new Vector();
			while(rs.next()){
				Menu menu = new Menu();
				menu.setID(rs.getInt(1));
				menu.setMenu(rs.getString(2));
				menu.setURL(rs.getString(3));				
				menuVector.add(menu);
			}
			dbc.close();
			return menuVector;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	

}
