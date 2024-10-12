package venkat.java.samples.snakeboard.entities;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Point {

	int x;
	int y;

	public Point adjustBy(Point adjustCoords) {
		Point newPoint = Optional.ofNullable(adjustCoords)
									.map(ac -> new Point(x + ac.x, y + ac.y))
									.orElse(null);
		log.debug("{}.adjustBy({})={}", this, adjustCoords, newPoint);
		return newPoint;
	}

}
