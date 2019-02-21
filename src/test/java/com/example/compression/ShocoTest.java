package com.example.compression;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ShocoTest {
	
	@Test
	void shocoTest() throws Exception {
		Shoco shoco = new Shoco();
		
		String text = "un texto de prueba";
		byte[] bytes = shoco.compress(text);
		
		assertEquals(text, shoco.decompress(bytes));
	}

}
