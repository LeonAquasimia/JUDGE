package engine.util;

import java.util.ArrayList;

public class Grama
{
	public static String[] removeEmptyStrings(String[] strings)
	{
		ArrayList<String> tempArrayList=new ArrayList<>();
		
		for (int i = 0; i < strings.length; i+=1)
		{
			if(!strings[i].equals(""))
			{
				tempArrayList.add(strings[i]);
			}
		}
		
		String[] result=new String[tempArrayList.size()];
				
		return tempArrayList.toArray(result);
	}
}
