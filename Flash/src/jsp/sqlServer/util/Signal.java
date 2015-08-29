package jsp.sqlServer.util; 

public class Signal {
	

	public final static int COMMAND_INDEX_ADD=1;
	
	public final static int COMMAND_INDEX_EDITINFO=2;
	
	public final static int COMMAND_INDEX_DEL=3;
		
	//////////////////////////////////////////////////////////

	public final static int COMMAND_MENU_ADD=4;
	
	public final static int COMMAND_MENU_EDITINFO=5;
	
	public final static int COMMAND_MENU_DEL=6;
	
	//////////////////////////////////////////////////////////

	public final static int COMMAND_WORKS_ADD=7;
	
	public final static int COMMAND_WORKS_EDITINFO=8;
	
	public final static int COMMAND_WORKS_DEL=9;
	
	//////////////////////////////////////////////////////////

	public final static int COMMAND_INTRO_ADD=10;
	
	public final static int COMMAND_INTRO_EDITINFO=11;
	
	public final static int COMMAND_INTRO_DEL=12;
	
	//////////////////////////////////////////////////////////

	public final static int COMMAND_FLASHER_ADD=13;
	
	public final static int COMMAND_FLASHER_EDITINFO=14;
	
	public final static int COMMAND_FLASHER_DEL=15;
	
	//////////////////////////////////////////////////////////

	public final static int COMMAND_CHART1_ADD=16;
	
	public final static int COMMAND_CHART1_EDITINFO=17;
	
	public final static int COMMAND_CHART1_DEL=18;
	
	//////////////////////////////////////////////////////////

	public final static int COMMAND_CHART2_ADD=19;
	
	public final static int COMMAND_CHART2_EDITINFO=20;
	
	public final static int COMMAND_CHART2_DEL=21;
	
	//////////////////////////////////////////////////////////

	public final static int COMMAND_FEATURE_ADD=22;
	
	public final static int COMMAND_FEATURE_EDITINFO=23;
	
	public final static int COMMAND_FEATURE_DEL=24;
	
	//////////////////////////////////////////////////////////

	public final static int COMMAND_FEATURE_info_ADD=25;
	
	public final static int COMMAND_FEATURE_info_EDITINFO=26;
	
	public final static int COMMAND_FEATURE_info_DEL=27;
	
	//////////////////////////////////////////////////////////

	public final static int COMMAND_SKILL_ADD=28;
	
	public final static int COMMAND_SKILL_EDITINFO=29;
	
	public final static int COMMAND_SKILL_DEL=30;
	
	//////////////////////////////////////////////////////////

	public final static int COMMAND_CHINESE_ADD=31;
	
	public final static int COMMAND_CHINESE_EDITINFO=32;
	
	public final static int COMMAND_CHINESE_DEL=33;
	

	
	
	//default when  errors come up 
	public final static int COMMAND_ERROR = 0 ;
	
	public static void main(String[] args) {

	}
	public final static int getCommand(String command)
	{
		if(command.equals("addIndex"))
		{
			return COMMAND_INDEX_ADD;
		}

		if(command.equals("editIndexInfo"))
		{
			return COMMAND_INDEX_EDITINFO;
		}
				
		if(command.equals("delIndex"))
		{
			return COMMAND_INDEX_DEL;
		}
		
		if(command.equals("addMenu"))
		{
			return COMMAND_MENU_ADD;
		}

		if(command.equals("editMenuInfo"))
		{
			return COMMAND_MENU_EDITINFO;
		}
				
		if(command.equals("delMenu"))
		{
			return COMMAND_MENU_DEL;
		}
		
		if(command.equals("addWorks"))
		{
			return COMMAND_WORKS_ADD;
		}

		if(command.equals("editWorksInfo"))
		{
			return COMMAND_WORKS_EDITINFO;
		}
				
		if(command.equals("delWorks"))
		{
			return COMMAND_WORKS_DEL;
		}
				
		if(command.equals("addIntro"))
		{
			return COMMAND_INTRO_ADD;
		}

		if(command.equals("editIntroInfo"))
		{
			return COMMAND_INTRO_EDITINFO;
		}
				
		if(command.equals("delIntro"))
		{
			return COMMAND_INTRO_DEL;
		}
				
		if(command.equals("addFlasher"))
		{
			return COMMAND_FLASHER_ADD;
		}

		if(command.equals("editFlasherInfo"))
		{
			return COMMAND_FLASHER_EDITINFO;
		}
				
		if(command.equals("delFlasher"))
		{
			return COMMAND_FLASHER_DEL;
		}
				
		if(command.equals("addChart1"))
		{
			return COMMAND_CHART1_ADD;
		}

		if(command.equals("editChart1Info"))
		{
			return COMMAND_CHART1_EDITINFO;
		}
				
		if(command.equals("delChart1"))
		{
			return COMMAND_CHART1_DEL;
		}
		
		if(command.equals("addChart2"))
		{
			return COMMAND_CHART2_ADD;
		}

		if(command.equals("editChart2Info"))
		{
			return COMMAND_CHART2_EDITINFO;
		}
				
		if(command.equals("delChart2"))
		{
			return COMMAND_CHART2_DEL;
		}
				
		if(command.equals("addFeature"))
		{
			return COMMAND_FEATURE_ADD;
		}

		if(command.equals("editFeatureInfo"))
		{
			return COMMAND_FEATURE_EDITINFO;
		}
				
		if(command.equals("delFeature"))
		{
			return COMMAND_FEATURE_DEL;
		}
			
		if(command.equals("addFeature_info"))
		{
			return COMMAND_FEATURE_info_ADD;
		}

		if(command.equals("editFeature_infoInfo"))
		{
			return COMMAND_FEATURE_info_EDITINFO;
		}
				
		if(command.equals("delFeature_info"))
		{
			return COMMAND_FEATURE_info_DEL;
		}
						
		if(command.equals("addSkill"))
		{
			return COMMAND_SKILL_ADD;
		}

		if(command.equals("editSkillInfo"))
		{
			return COMMAND_SKILL_EDITINFO;
		}
				
		if(command.equals("delSkill"))
		{
			return COMMAND_SKILL_DEL;
		}
		
				
		if(command.equals("addChinese"))
		{
			return COMMAND_CHINESE_ADD;
		}

		if(command.equals("editChineseInfo"))
		{
			return COMMAND_CHINESE_EDITINFO;
		}
				
		if(command.equals("delChinese"))
		{
			return COMMAND_CHINESE_DEL;
		}
		return COMMAND_ERROR;		
	}
}
