package com.newsong.gui;

import java.io.*;
public class Test {
	static int countNormal  = 0 ;
	static int countNotes = 0;
	static int countBlankLines = 0;
	public static void main(String []args){
		File f = new File("E:/java/");
		File [] files = f.listFiles();
		for(File fs : files){
			if(fs.getName().matches(".*\\.java$")){
				System.out.println(fs.getName());
				parse(fs);
				System.out.println("CountNormal: "+countNormal);
				System.out.println("CountNotes: "+countNotes);
				System.out.println("CountBlankLines: "+countBlankLines);
				System.out.println();
				countNormal  = 0 ;
				countNotes = 0;
				countBlankLines = 0;
			}
		}	
	}
	private static void parse(File fs) {
		BufferedReader br = null;
		String str = null;
		boolean inNote = false;
		try {
			br = new BufferedReader(new FileReader(fs));
			try {
				while((str = br.readLine())!= null){
					str = str.trim();
					if(str.matches("^[\\s&&[^\n]]*$")){	
						countBlankLines++;
					}
					else if(str.startsWith("//")){
						countNotes++;
					}
					else if (str.startsWith("/*")){
						countNotes++;
						if(!str.endsWith("*/"))
							inNote  = true;
					}else if(inNote == true ){
						countNotes++;
						if(str.endsWith("*/")){
							inNote = false;
						}
					}else {
						countNormal++;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
