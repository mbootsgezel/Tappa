package game;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

public class SkillBar extends JPanel{
	
	private static SkillBar instance;
	
	private Skill skill1, skill2, skill3, skill4, skill5, skill6;
	
	private ArrayList <Skill> skills;
	
	private int skillCount = 6;
	
	private int skillSlotPosition;
	
	private SkillBar() {
		this.setLayout(null);
		this.setSize(skillCount * 54, 54);
		this.skillSlotPosition = 2;
		
		this.skills = new <Skill> ArrayList();
		this.setSkills();
		this.startSkillThread();
		
		this.setBackground(Color.BLACK);
		
		this.setVisible(true);
		
	}
	
	private void setSkills(){
		skills.add(this.skill1 = new Skill(Skill.BLAZEIT, 10));
		skills.add(this.skill2 = new Skill(Skill.BLAZEIT, 15));
		skills.add(this.skill3 = new Skill(Skill.BLAZEIT, 20));
		skills.add(this.skill4 = new Skill(Skill.BLAZEIT, 25));
		skills.add(this.skill5 = new Skill(Skill.BLAZEIT, 30));
		skills.add(this.skill6 = new Skill(Skill.BLAZEIT, 60));
	}
	
	private void startSkillThread(){
		for(int i = 0; i < skills.size(); i++){
			this.add(skills.get(i));
			this.skills.get(i).setBounds(skillSlotPosition, 2, 50, 50);
			skillSlotPosition = skillSlotPosition + 52;
			new Thread(skills.get(i)).start();
		}
	}
	
	public static SkillBar getInstance(){
		if(instance == null){
			instance = new SkillBar();
		}
		return instance;
	}

}
