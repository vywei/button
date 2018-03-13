package test;

import static org.junit.Assert.*;
import logic.Btn;
import logic.Skin;
import org.junit.Test;

public class TestButton {

	@Test
	public void testButtonSetSkin() {
		Skin s = new Skin(0, "Test Skin", 500, "", "", "");
		Btn b = new Btn(s, 1, null);
		b.setSkin(s);
		assertEquals(s, s);
	}

}