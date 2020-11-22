package com.imolczek.rfid.jukebox.daemon;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Daemon {

	public static void main(String[] args) {
		
		String filesLocation = "D:/RFID/jukebox-data/";
		String playMusicCommand = "cmd.exe /c start /b ";
		
		Scanner inputScanner = new Scanner(System.in);
		
		Pattern pattern = Pattern.compile("\\d{10}");
		Stream<MatchResult> distinct = inputScanner.findAll(pattern).distinct();
		
		distinct.forEach(matchResult -> {
			System.out.println("----");
			System.out.println(matchResult.group());
			String filePathString = filesLocation + matchResult.group() + ".mp3";
			File f = new File(filePathString);
			if(f.exists() && !f.isDirectory()) { 
				try {
					String command = playMusicCommand + filePathString;
					System.out.println(command);
					Runtime.getRuntime().exec(command);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("----");
		});

		System.out.println("Finished!");
		
		inputScanner.close();

	}
	
}
