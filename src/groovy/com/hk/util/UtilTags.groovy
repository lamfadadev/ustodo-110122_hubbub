package com.hk.util;

import java.util.ArrayList;

import org.hsqldb.lib.HashSet;

public class UtilTags {

	public static ArrayList<String> convertMultiTagLinesToTagArray(ArrayList<String> fileLines, String autocomp)
	{
		HashSet<String> setTagsAlreadyKnown = new HashSet<String>(); 
		fileLines = fileLines.reverse ();
				//String[] sarr = (String[] ) fileLines.toArray(new String[0] a);
		ArrayList alTagsRtn = new ArrayList();  
		if (autocomp.length() > 1)
		{
			fileLines.each {
				String fileLine = it;
				if (fileLine.length() > 20)
				{
					fileLine = fileLine [20..-1]
					String[] tags = fileLine.split(" / ");
					// BREAK LINE INTO TAGS
					int iCnt = -1; 
					tags.each {
						iCnt++;
						String tag = it;
						//O.o("check tag contains tag[" + tag + "] srchStr [" + autocomp + "]");
						if (autocomp != null && tag != null && tag.contains(autocomp))
						{
							tag = tag.trim();
							
							if (!setTagsAlreadyKnown.contains(tag))
							{
								//O.o("adding tag hist [" + tag.trim() + "] srchStr [" + autocomp + "]");
								if (iCnt < (tags.length-1)) // don't want instance data - assumed to be after last " / "
									alTagsRtn.add (tag.trim());
								setTagsAlreadyKnown.add tag
							}
						}
					}
				}
			}
			if (alTagsRtn.size() == 0)
			{
//				if (!autocomp.equals("autocomplete test"))
//					alTagsRtn.add ("sorry hombre, no matches for " + autocomp);
			}
			
			O.o ("generated [" + alTagsRtn.size() + "] tags from [" + fileLines.size()+ "] filelines")
		}
		else
		{
			//if (autocomp.length() == 1)
			//		alTagsRtn.add ("one more letter hombre, you can do it!");
		}	
		//O.o ("autocomp < size min")
		
		return alTagsRtn;
	}
	
}
