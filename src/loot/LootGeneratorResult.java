package loot;

import java.util.ArrayList;

import loot.models.Loot;

public class LootGeneratorResult {

	public final ArrayList<Loot> loot;
	public final int gold;
	
	public LootGeneratorResult(ArrayList<Loot> loot, int gold) {
		this.loot = loot;
		this.gold = gold;
	}
}
