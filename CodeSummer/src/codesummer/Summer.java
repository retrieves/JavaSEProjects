package codesummer;

import java.io.*;

public class Summer {


	public static void main(String[] args) {
		File f = new File("E:/java/");
		File[] files = f.listFiles();
		for (File fs : files) {
			if (fs.getName().matches(".*\\.java$")) {
				System.out.println(fs.getName());
				parse(fs);
			}
		}
	}
	
	public static int  parseFolder(File folder){
		int sum = 0;
		File[] subFiles = folder.listFiles();
		for (File file : subFiles) {
			if(file.isDirectory()){
				sum+=parseFolder(file);
			}else {
				if(file.getName().matches(".*\\.java$")){
					sum+=parse(file);
				}
			}
		}
		return sum;
	}
	
	public  static  int parse(File fs) {
		BufferedReader br = null;
		String str = null;
		boolean inNote = false;
		int count = 0;
		try {
			br = new BufferedReader(new FileReader(fs));
			try {
				while ((str = br.readLine()) != null) {
					str = str.trim();
					if (str.matches("^[\\s&&[^\n]]*$") ) {
						continue;
					} else if (str.startsWith("//")) {
						continue;
					} else if (str.startsWith("/*")) {
						
						if (!str.endsWith("*/"))
							inNote = true;
						continue;
					} else if (inNote == true) {
						if (str.endsWith("*/")) {
							inNote = false;
						}
						continue;
					} else {
						count++;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return count;
	}
}
