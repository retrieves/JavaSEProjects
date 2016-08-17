
package serviceImpl;

import java.rmi.RemoteException;

import service.ExecuteService;
import service.UserService;
/**
 * 真正的解释器
 * 需要自己实现
 * @author Administrator
 *
 */
public class ExecuteServiceImpl implements ExecuteService {

	
	public String execute(String code, String param) throws Exception {
		char[] input = code.toCharArray();
		char[] para = param.toCharArray();
		char[] data = new char[10240];
		char[] output = new char[10240];
		int ptr = 0;
		int pParam = 0;
		int pOutput = 0;
		boolean isLoop[] = new boolean [1024];
		int []startPos = new int [1024] ;
		int loopDepth = -1;
		
		try{
			for (int i = 0; i < input.length; i++) {
				
				switch(input[i]){
					case '+':data[ptr]++;break;
					case '-':data[ptr]--;break;
					case '>':ptr++;break;
					case '<':ptr--;break;
					case '.':output[pOutput] =  data[ptr] ;pOutput++;break;
					case ',':data[ptr] = para[pParam];
							 pParam++;
							 break;
					case '[':loopDepth++;
							 isLoop[loopDepth] = true;
							 startPos [loopDepth] = i; 
							 break;
					
					case ']':if(data[ptr] == 0){
								isLoop[loopDepth] = false;
								loopDepth--;
							 }else{
								 i = startPos[loopDepth];
							 }
							 break;
				}
			}
		}catch(Exception e){
			return "Code or Input Error";
		}
		if(pOutput == 0)
			return "Code or Input Error";
		return  new String(output,0,pOutput);
	
	}

}
