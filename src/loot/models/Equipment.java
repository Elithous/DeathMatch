package loot.models;

import interfaces.IHasStats;

public class Equipment extends Loot implements IHasStats{

	private boolean isEquipped;
	protected int maxHealth;
	protected int strength;
	protected int dexterity;
	protected int intelligence;
	protected int armor;
}
