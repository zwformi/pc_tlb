package com.yunrer.util;
/**
 * 
 * @author ZGF
 *
 */
public class MoveCharUtil {
	
	public static String moveChars(String oldHistory, int missDays){
		StringBuffer result =new StringBuffer();
		result.append(oldHistory);
		for(int i=0;i<missDays+1;i++){
			if(i == missDays)
				result.append('1');
			else
				result.append('0');
		}
		return result.toString();
		
	}
	

}
