/*
 * Encapsulated bankBalance in Farm
 * to allow the access and modification 
 * from Shop class
 * */

package students;

import students.tools.Tool;

public class Shop {
	
	private Farm farm;
	
	public Shop(Farm farm) {
		
		// initialiser
		this.farm = farm;
	}
	
	public void buyWeedKiller(Field field, int x, int y, int[] bankBalance) {
		int value = 50;
		if (farm.getBankBalance() <= value) {
			farm.setBankBalanance(farm.getBankBalance() - value);
			applyWeedKiller(field, x, y);
			bankBalance[0]-= value;
			System.out.println("You applied weed killer. "
					+ "It was super effective!");
		} else {
			System.out.println("Insufficient funds :(");
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