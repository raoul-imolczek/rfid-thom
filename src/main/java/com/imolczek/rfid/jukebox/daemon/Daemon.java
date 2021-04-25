package com.imolczek.rfid.jukebox.daemon;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Daemon {

	public static void main(String[] args) {
		
		String filesLocation = "~/jukebox/data/";
		String playMusicCommand = "mpg123 -C ";
		
		Scanner inputScanner = new Scanner(System.in);
		
		Pattern pattern = Pattern.compile("\\d{10}");
		Stream<MatchResult> distinct = inputScanner.findAll(pattern).distinct();
		
		distinct.forEach(matchResult -> {
			//System.out.println("----");
			//System.out.println(matchResult.group());
			String filePathString = filesLocation + matchResult.group() + ".mp3";

			try {
				String command = playMusicCommand + filePathString;
				System.out.println(command);
				Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			/*
			Path path = Paths.get(filePathString);
			if(Files.exists(path)) { 
			} else {
				System.out.println("... "+ filePathString + " not found");
			}
			*/
			System.out.println("----");
		});

		System.out.println("Finished!");
		
		inputScanner.close();

	}
	
}
