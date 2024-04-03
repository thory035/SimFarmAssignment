/*
 * Encapsulated bankBalance in Farm
 * to allow the access and modification 
 * from Shop class
 * */

package students;

import students.items.Item;
import students.tools.Tool;

public class Shop {
	
	public Farm farm;
	
	public Shop(Farm farm) {
		this.farm = farm;
	}
	
	public boolean buyItem(Item item) {
		if (farm.getBankBalance() >= item.getValue()) {
			farm.setBankBalanance(farm.getBankBalance() - item.getValue());
			System.out.println(item.getClass().toolName() + "purchased for $" + item.getValue());
		}
	}
	
	
	private void applyWeedKiller() {
		
	}
	
	public void buyHoe() {
		int value = 20;
		if (farm.getBankBalance() <= value) {
			farm.setBankBalanance(farm.getBankBalance() - value ;
		}
	}
	
	private void applyHoe() {
		
	}
	
	public void buyPlantingGloves() {
		int cost = 10;
	}
	
	private void applyPlantingGloves() {
		
	}
	
	public void buyBasket() {
		int cost = 15;
	}
	
	public void applyBasket() {
		
	}
	
}