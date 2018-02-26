package interfaces;

public interface IHasStats 
{
	public int getMaxHealth();
	public int getStrength();
	public int getDexterity();
	public int getIntelligence();
	public int getArmor();
	public void setMaxHealth(int hp);
	public void setStrength(int str);
	public void setDexterity(int dex);
	public void setIntelligence(int intel);
	public void setArmor(int armor);
}
