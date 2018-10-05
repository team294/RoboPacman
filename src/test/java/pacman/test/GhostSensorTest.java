package pacman.test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pacman.base.GhostSensorEngine;
import pacman.base.Util;
import pacman.graphics.Ghost;

public class GhostSensorTest {

	@Test 
	public void testDistanceCalc() {
		assertEquals("up",5,Util.getDistance(105, 95, 105, 90));
		assertEquals("down",5,Util.getDistance(105, 95, 105, 100));
		assertEquals("front",5,Util.getDistance(105, 95, 100, 95));
		assertEquals("front/down",7,Util.getDistance(105, 95, 100, 100));
		assertEquals("front/up",7,Util.getDistance(105, 95, 100, 90));
		
		assertEquals("behind",5,Util.getDistance(105, 95, 110, 95));
		
		assertEquals("test 550",550,Util.getDistance(0, 400, 550, 400));
		
	}
    
    @Test 
    public void testPing() {
		GhostSensorEngine sensor = new GhostSensorEngine();

		List<Ghost> ghostList = new ArrayList<Ghost>();

		ghostList.add(new Ghost(100,100));

		// check basic cases
		
		assertEquals("1 below 0 angle",1,sensor.getPing(100,101,0,ghostList));
		assertEquals("1 above 0 angle",0,sensor.getPing(100,99,0,ghostList));
		assertEquals("1 Right 0 angle",0,sensor.getPing(101,100,0,ghostList));
		assertEquals("1 Left  0 angle",0,sensor.getPing(99,100,0,ghostList));

		assertEquals("1 below 90 angle",0,sensor.getPing(100,101,90,ghostList));
		assertEquals("1 above 90 angle",0,sensor.getPing(100,99,90,ghostList));
		assertEquals("1 Right 90 angle",0,sensor.getPing(101,100,90,ghostList));
		assertEquals("1 Left  90 angle",1,sensor.getPing(99,100,90,ghostList));

		assertEquals("1 below -90 angle",0,sensor.getPing(100,101,-90,ghostList));
		assertEquals("1 above -90 angle",0,sensor.getPing(100,99,-90,ghostList));
		assertEquals("1 Right -90 angle",1,sensor.getPing(101,100,-90,ghostList));
		assertEquals("1 Left  -90 angle",0,sensor.getPing(99,100,-90,ghostList));

		assertEquals("1 below 180 angle",0,sensor.getPing(100,101,180,ghostList));
		assertEquals("1 above 180 angle",1,sensor.getPing(100,99,180,ghostList));
		assertEquals("1 Right 180 angle",0,sensor.getPing(101,100,180,ghostList));
		assertEquals("1 Left  180 angle",0,sensor.getPing(99,100,180,ghostList));
		
		// check range
		assertEquals("2 below 0 angle",2,sensor.getPing(100,102,0,ghostList));
		assertEquals("10 below 0 angle",10,sensor.getPing(100,110,0,ghostList));

    }
	
	@Test 
    public void testDirectionalRadar() {
		GhostSensorEngine sensor = new GhostSensorEngine();

		List<Ghost> ghostList = new ArrayList<Ghost>();

		ghostList.add(new Ghost(100,100));

		// check basic cases

		assertEquals("1 below 180 angle",0,sensor.getDirectionalRadar(100,101,180,ghostList));
		assertEquals("1 above 180 angle",1,sensor.getDirectionalRadar(100,99,180,ghostList));
		assertEquals("1 Right 180 angle",0,sensor.getDirectionalRadar(101,100,180,ghostList));
		assertEquals("1 Left  180 angle",0,sensor.getDirectionalRadar(99,100,180,ghostList));

		assertEquals("1 below -90 angle",0,sensor.getDirectionalRadar(100,101,-90,ghostList));
		assertEquals("1 above -90 angle",0,sensor.getDirectionalRadar(100,99,-90,ghostList));
		assertEquals("1 Right -90 angle",1,sensor.getDirectionalRadar(101,100,-90,ghostList));
		assertEquals("1 Left  -90 angle",0,sensor.getDirectionalRadar(99,100,-90,ghostList));

		assertEquals("1 below 90 angle",0,sensor.getDirectionalRadar(100,101,90,ghostList));
		assertEquals("1 above 90 angle",0,sensor.getDirectionalRadar(100,99,90,ghostList));
		assertEquals("1 Right 90 angle",0,sensor.getDirectionalRadar(101,100,90,ghostList));
		assertEquals("1 Left  90 angle",1,sensor.getDirectionalRadar(99,100,90,ghostList));

		assertEquals("1 below 0 angle",1,sensor.getDirectionalRadar(100,101,0,ghostList));
		assertEquals("1 above 0 angle",0,sensor.getDirectionalRadar(100,99,0,ghostList));
		assertEquals("1 Right 0 angle",0,sensor.getDirectionalRadar(101,100,0,ghostList));
		assertEquals("1 Left  0 angle",0,sensor.getDirectionalRadar(99,100,0,ghostList));
    }
}
