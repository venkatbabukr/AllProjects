package venkat.java.samples.snakeboard.entities;

import java.util.UUID;

import com.google.common.base.Objects;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import venkat.java.samples.snakeboard.config.GameConfig;
import venkat.java.samples.snakeboard.enums.BoardPixel;

@Slf4j
public class Board {

	@Getter
	// Board ID
	private UUID id;

//  Have these attributes if you want a rectangular board
//	private int width;
//	private int height;
    // Just one size member is enough for both width & height for a
	// square board
	@Getter
    private int size;

    private BoardPixel[][] pixArray;
    
    public Board(GameConfig cfg) {
    	this.id = UUID.randomUUID();
    	log.debug("Board created with ID: {}, using config with ID: {}", id, cfg.getId());
    	size = cfg.getBoardSize();
    	log.debug("Creating board array of size: {}", size);
    	pixArray = new BoardPixel[size][size];
    }

    public BoardPixel getBoardPix(Point point) {
    	return pixArray[point.getX()][point.getY()];
    }

    public synchronized boolean checkAndSetBoardPix(BoardPixel pix, Point point) {
    	if (!isPointInBoard(point))
    		throw new IllegalArgumentException("Point collides or going outside of board!");
    	boolean setOk = pixArray[point.getX()][point.getY()] == null || Objects.equal(pix, pixArray[point.getX()][point.getY()]);
    	if (setOk) {
    		pixArray[point.getX()][point.getY()] = pix;
    	}
    	return setOk;
    }

    public synchronized void setBoardPix(BoardPixel pix, Point point) {
    	if (!isPointInBoard(point))
    		throw new IllegalArgumentException("Point collides or going outside of board!");
    	pixArray[point.getX()][point.getY()] = pix;
    }

    public void clearBoardPix(Point point) {
    	setBoardPix(null, point);
    }

    public BoardPixel[][] getPixArray() {
    	return pixArray;
    }

    public boolean isPointInBoard(Point point) {
    	return point.getX() > -1 && point.getX() < size
    	          && point.getY() > -1 && point.getY() < size;
    }

}
