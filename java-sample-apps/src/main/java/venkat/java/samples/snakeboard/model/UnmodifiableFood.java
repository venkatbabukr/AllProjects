package venkat.java.samples.snakeboard.model;

import java.util.Objects;

import venkat.java.samples.snakeboard.entities.Point;
import venkat.java.samples.snakeboard.entities.Food;

public class UnmodifiableFood {

	private Food gameFood;

	public UnmodifiableFood(Food gf) {
		Objects.requireNonNull(gf, "Game food object required, can't be null!");
		this.gameFood = gf;
	}
	
	public Point getFoodPoint() {
		return this.gameFood.getFoodPoint();
	}

}
