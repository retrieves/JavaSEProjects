package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;

import service.ExecuteService;
import service.IOService;
/**
 * 实现文件操作
 * @author Administrator
 *
 */

public class IOServiceImpl implements IOService{
	
	@Override
	public boolean writeFile(String code, String username, String fileName) {
		searchVersion(username,fileName.substring(0,fileName.length()-14));
		
		File newFile = new File("Codes/"+username+"/"+fileName.substring(0,fileName.length()-14),fileName);
		try {
			FileWriter fw = new FileWriter(newFile, false);
			fw.write(code);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public String readFile(String username, String fileName) {
		File file = new File("Codes/"+username+"/"+fileName.substring(0, fileName.length()-14),fileName);
		BufferedReader br  = null;
		if(file.exists() && file.isFile()){
			try {
				br = new BufferedReader(new FileReader(file));
				StringBuilder sb = new StringBuilder((int) file.length());
				String line = "";
				while((line = br.readLine()) != null){
					sb.append(line+'\n');
				}
				return sb.toString();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "NotFound";
			} catch (IOException e) {
				e.printStackTrace();
				return "ReadError";
			} finally{
				if(br != null){
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "NotFound";
	}
	
	public String execCode(String username, String fileName,String param) throws Exception{
		String code = readFile(username,fileName);
		if(code.equals("NotFound")||code.equals("ReadError"))
			return "Error";
		ExecuteService execServ = new ExecuteServiceImpl();
		String result  = "";
		try {
			result = execServ.execute(code, param);
		} catch (RemoteException e) {
			e.printStackTrace();
			result  = "Error";
		}
		return result;
	}

	
	@Override
	public String readFileList(String username) {
		StringBuilder sb = new StringBuilder();
		File[] fileDirs = new File("Codes",username).listFiles();
		for (File fileDir : fileDirs) {
			File[] files = fileDir.listFiles();
			for (int i = 0; i < files.length; i++) {
				sb.append(files[i].getName()+(i+1 == files.length ? ";":","));
			}
		}
		
		return sb.toString();
	}
	
	
	public void searchVersion(String username, String fileName){
		
		File fileDir = new File("Codes/"+username,fileName);
		if(!fileDir.exists()){
			fileDir.mkdir();
		}
		File [] oldFile = fileDir.listFiles();
		File oldestFile = null;
		int count = 0;
		long time = 0L;
		for (int i = 0; i < oldFile.length; i++) {
			File f = oldFile[i];
			if(f.getName().startsWith(fileName)){
				count++;
				if(time < f.lastModified()){
					time = f.lastModified();
					oldestFile = f;
				}
			}
		}	
		if(count == 10){
			oldestFile.delete();
		}
	}
	/*public static void main(String[] args) {
		IOServiceImpl io = new IOServiceImpl();
		System.out.println(io.readFile("admin","tt20160506220534.txt"));
	}*/
}
