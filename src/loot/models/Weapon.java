package loot.models;

import java.util.Random;

import loot.enums.AttackType;

public class Weapon extends Equipment{

	public final boolean isTwoHanded;
	public final AttackType attackType;
	private int attackMin;
	private int attackMax;
	
	public Weapon(boolean isTwoHanded, AttackType attackType) {
		this.isTwoHanded = isTwoHanded;
		this.attackType = attackType;
	}
	
	public void setAttackMin(int attackMin) {
		this.attackMin = attackMin;
	}

	public void setAttackMax(int attackMax) {
		this.attackMax = attackMax;
	}

	@Override
	public int getAttack()
	{
		return attackMin + (new Random().nextInt(attackMax-attackMin));
	}
	
}
