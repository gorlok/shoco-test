package com.example.benchmark;


import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import com.example.compression.Shoco;

@Fork(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Measurement(iterations = 8)
@Warmup(iterations = 3)
@BenchmarkMode(Mode.Throughput)
public class StringFormatBenchmark {

	@Benchmark
	public void stringFormat(Blackhole blackhole) {
		Shoco shoco = new Shoco();
		
		String text = "un texto de prueba";
		byte[] bytes = shoco.compress(text);

		blackhole.consume(bytes);
	}


}