package com.saraha.demo.utils;

import java.util.Random;

public class GenerationRandomKey {

	public static long generateRandomKey() {

		Random random = new Random();

		return random.nextInt();
	}
}