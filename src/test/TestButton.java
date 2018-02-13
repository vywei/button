package test;

import static org.junit.Assert.*;
import logic.Button;
import logic.Skin;

import org.junit.Test;

public class TestButton {

	@Test
	public void testButtonSetSkin() {
		Skin s = new Skin(0, "Test Skin", 500);
		Button b = new Button(s);
		b.setSkin(s);
		assertEquals(b.currentSkin(), s);
	}

}
