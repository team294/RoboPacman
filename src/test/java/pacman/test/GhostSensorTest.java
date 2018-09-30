package pacman.test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pacman.base.GhostSensorEngine;
import pacman.graphics.Ghost;

public class GhostSensorTest {

    
    @Test public void testPing() {
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
		assertEquals("11 below 0 angle",0,sensor.getPing(100,111,0,ghostList));

    }
    
}
