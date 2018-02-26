package loot.models;

import loot.enums.AttackType;

public class Weapon extends Equipment{

	public final boolean isTwoHanded;
	public final AttackType attackType;
	
	public Weapon(boolean isTwoHanded, AttackType attackType) {
		super();
		this.isTwoHanded = isTwoHanded;
		this.attackType = attackType;
	}
	
}
