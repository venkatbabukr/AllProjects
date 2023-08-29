package venkat.lombok.simple.lombok.hashcode;

public class Demo {
	
	private static void demoWithPlainDataAnnotation() {
		BaseProductWithDataAnnotation p1 = BaseProductWithDataAnnotation.builder()
																	.id(1).category("C1").name("P1").build();
		BaseProductWithDataAnnotation p2 = BaseProductWithDataAnnotation.builder()
																	.id(1).category("C1").name("P1").build();
		BaseProductWithDataAnnotation p3 = BaseProductWithDataAnnotation.builder()
																	.id(1).category("C2").name("P1").build();

		System.out.format("Demo with simple @Data annotation: %n%n");
		System.out.println("Equals:");
		System.out.format("Product P1: %s, P2: %s, P1.equals(P2)=%s%n", p1, p2, p1.equals(p2));
		System.out.format("Product P1: %s, P3: %s, P1.equals(P3)=%s%n", p1, p3, p1.equals(p3));
		System.out.println();
		System.out.println("Hashcode:");
		System.out.format("hashCode(P1 %s)=%d%n", p1, p1.hashCode());
		System.out.format("hashCode(P2 %s)=%d%n", p2, p2.hashCode());
		System.out.format("hashCode(P3 %s)=%d%n", p3, p3.hashCode());
	}
	
	private static void demoWithHashCodeAnnotationCallSuper() {
		ProductWithPriceAndHashCodeCallSupper p1 = ProductWithPriceAndHashCodeCallSupper.builder()
																							.id(1).category("C1").name("P1").price(9.99).build();
		ProductWithPriceAndHashCodeCallSupper p2 = ProductWithPriceAndHashCodeCallSupper.builder()
																							.id(1).category("C1").name("P1").price(9.99).build();
		ProductWithPriceAndHashCodeCallSupper p3 = ProductWithPriceAndHashCodeCallSupper.builder()
																							.id(1).category("C2").name("P1").price(9.99).build();

		System.out.format("Demo with @EqualsAndHashCode(callSuper = true) annotation: %n%n");
		System.out.println("Equals:");
		System.out.format("Product P1: %s, P2: %s, P1.equals(P2)=%s%n", p1, p2, p1.equals(p2));
		System.out.format("Product P1: %s, P3: %s, P1.equals(P3)=%s%n", p1, p3, p1.equals(p3));
		System.out.println();
		System.out.println("Hashcode:");
		System.out.format("hashCode(P1 %s)=%d%n", p1, p1.hashCode());
		System.out.format("hashCode(P2 %s)=%d%n", p2, p2.hashCode());
		System.out.format("hashCode(P3 %s)=%d%n", p3, p3.hashCode());
	}

	private static void demoWithHashCodeAnnotationNoCallSuper() {
		ProductWithPriceAndOnlyChildHashCode p1 = ProductWithPriceAndOnlyChildHashCode.builder()
																							.id(1).category("C1").name("P1").price(9.99).build();
		ProductWithPriceAndOnlyChildHashCode p2 = ProductWithPriceAndOnlyChildHashCode.builder()
																							.id(1).category("C1").name("P1").price(9.99).build();
		ProductWithPriceAndOnlyChildHashCode p3 = ProductWithPriceAndOnlyChildHashCode.builder()
																							.id(1).category("C2").name("P1").price(9.99).build();

		System.out.format("Demo with @EqualsAndHashCode(callSuper = false) annotation: %n%n");
		System.out.println("Equals:");
		System.out.format("Product P1: %s, P2: %s, P1.equals(P2)=%s%n", p1, p2, p1.equals(p2));
		System.out.format("Product P1: %s, P3: %s, P1.equals(P3)=%s%n", p1, p3, p1.equals(p3));
		System.out.println();
		System.out.println("Hashcode:");
		System.out.format("hashCode(P1 %s)=%d%n", p1, p1.hashCode());
		System.out.format("hashCode(P2 %s)=%d%n", p2, p2.hashCode());
		System.out.format("hashCode(P3 %s)=%d%n", p3, p3.hashCode());
	}

	public static void main(String[] args) {
		demoWithPlainDataAnnotation();
		System.out.format("%n-----%n%n");
		demoWithHashCodeAnnotationCallSuper();
		System.out.format("%n-----%n%n");
		demoWithHashCodeAnnotationNoCallSuper();
	}

}
