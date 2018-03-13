package test;

import static org.junit.Assert.*;
import logic.Btn;
import logic.Skin;

import org.junit.Test;

public class TestButton {

	@Test
	public void testButtonSetSkin() {
<<<<<<< HEAD
		Skin s = new Skin(0, "Test Skin", 500, "", "");
		Btn b = new Btn(s, 1, null);
=======
		Skin s = new Skin(0, "Test Skin", 500, "", "", "");
		Btn b = new Btn(s, 1);
>>>>>>> ca6527a383b07df092cbd83e038a8f57944489ed
		b.setSkin(s);
		assertEquals(b.getCurrentSkin(), s);
	}

}
