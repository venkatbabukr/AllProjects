package venkat.lombok.simple.lombok.hashcode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
class BaseProductWithDataAnnotation {
	
	private int id;
	private String name;
	private String category;

	@EqualsAndHashCode.Exclude
	private double avgRating;

}

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
class ProductWithPriceAndOnlyChildHashCode extends BaseProductWithDataAnnotation {

	private double price;

}

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
class ProductWithPriceAndHashCodeCallSupper extends BaseProductWithDataAnnotation {

	private double price;

}

public class PojoClasses {

}
