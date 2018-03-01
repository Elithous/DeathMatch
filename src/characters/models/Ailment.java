package characters.models;

import characters.enums.AilmentType;

public class Ailment 
{
	public final AilmentType type;
	public final float multi;
	private int turnsLeft;

	public Ailment(AilmentType type, float multi, int turnsLeft) 
	{
		super();
		this.type = type;
		this.multi = multi;
	}

	public int getTurnsLeft()
	{
		return turnsLeft;
	}

	public void decreaseTurns()
	{
		turnsLeft--;
	}
}
