# Sample Demos and their meanings
## Demo#demoWithPlainDataAnnotation
### Overview
* Using just plain ***@Data*** annotation will give us ***equals*** and ***hashCode*** methods that perform the two operations ***considering every field***.
* If we want to ***exclude any field*** from comparison or hashcode, use ***@EqualsAndHashCode.Exclude***. See [BaseProductWithDataAnnotation](PojoClasses.java#L16) for example.
### Demo explanation
- In the demo, here are the three products P1, P2, P3.
- P1 and P2 have exactly same field values
- P3 has category different from P1
- Therefore:

    - P1.equals(P2) = true
    - P1.equals(P3) = false
    - 
    - hashCode(P1) and hashCode(P3) are same
    - P1.equals(P2) = false

## Demo#demoWithHashCodeAnnotationCallSuper
### Overview
* Using ***@EqualsAndHashCode(callSuper = true)*** annotation will call super class equals and hashcode methods from subclass to compare and compute on all fields!

### Demo explanation
- In the demo, here are the three products P1, P2, P3 of class [ProductWithPriceAndOnlyChildHashCode](PojoClasses.java#L25).
- P1 and P2 have exactly same field values including price field from subclass.
- P3 has ***category different*** from P1 ***but same price***.
- Therefore:

    - P1.equals(P2) = true
    - P1.equals(P3) = true
    - 
    - hashCode(P1, P2 and P3) are all same

## Demo#demoWithHashCodeAnnotationNoCallSuper
### Overview
* Using ***@EqualsAndHashCode(callSuper = false)*** annotation will restrict equals and hashcode methods of subclass to just compare or compute on fields of subclass.

### Demo explanation
- In the demo, here are the three products P1, P2, P3 of class [ProductWithPriceAndOnlyChildHashCode](PojoClasses.java#L25).
- P1 and P2 have exactly same field values including price field from subclass.
- P3 has ***category different*** from P1 ***but same price***.
- Therefore:

    - P1.equals(P2) = true
    - P1.equals(P3) = true
    - 
    - hashCode(P1, P2 and P3) are all same
