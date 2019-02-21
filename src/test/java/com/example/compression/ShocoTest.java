package com.example.compression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class ShocoTest {

	@Test
	void shocoTest() throws Exception {
		Shoco shoco = new Shoco();

		String text = "un texto de prueba";
		byte[] bytes = shoco.compress(text);

		assertEquals(text, shoco.decompress(bytes));
	}

	@Test
	void shocoTestMF() throws Exception {
		Shoco shoco = new Shoco();

		Path path = Paths.get(getClass().getClassLoader()
				.getResource("martin-fierro-extracto.txt").toURI());

		Stream<String> lines = Files.lines(path);
		String text = lines.collect(Collectors.joining("\n"));
		lines.close();

		long startTime = System.nanoTime();
		byte[] bytes = shoco.compress(text);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		
		System.out.println(Instant.now().toString());
		System.out.println("original size: " + text.length());
		System.out.println("compressed size: " + bytes.length);
		System.out.format("reduction: %.2f%%\n", (1-((float)bytes.length / text.length())) * 100f);
		System.out.format("compression duration: %.2f ms\n", duration / 1000000f);
		
		
		startTime = System.nanoTime();
		String decompressed = shoco.decompress(bytes);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.format("decompression duration: %.2f ms\n", duration / 1000000f);
		
		assertEquals(text, decompressed);
	}

}
