package com.hk.util;

import java.util.ArrayList;

import org.hsqldb.lib.HashSet;

public class UtilTags {

	public static ArrayList<String> getAutoCompleteLinesOut(ArrayList<String> fileLines, String autocomp)
	{
		HashSet<String> setTagsAlreadyKnown = new HashSet<String>(); 
		fileLines = fileLines.reverse ();
				//String[] sarr = (String[] ) fileLines.toArray(new String[0] a);
		ArrayList alTagsRtn = new ArrayList();  
		if (autocomp.length() > 2)
		{
			fileLines.each {
				String fileLine = it;
				if (fileLine.length() > 20)
				{
					//fileLine = fileLine [20..-1]
					
					def age = 
						UtilDate.renderAgeAsLetterFromNowToFileDateStr(fileLine [0..18]) 

					fileLine = age + " " + fileLine [20..-1]
						
										//hbk 110401 String[] tags = fileLine.split(" / ");
					String[] tags = new String[2];
					tags[0] = fileLine; 
					tags[1] = ""; 
					// BREAK LINE INTO TAGS
					int iCnt = -1; 
					tags.each {
						iCnt++;
						String filelineNoDate = it;
						//O.o("check tag contains tag[" + tag + "] srchStr [" + autocomp + "]");
						if (autocomp != null && filelineNoDate != null && filelineNoDate.contains(autocomp))
						{
							filelineNoDate = filelineNoDate.trim();
							
							if (!setTagsAlreadyKnown.contains(filelineNoDate))
							{
								//O.o("adding tag hist [" + tag.trim() + "] srchStr [" + autocomp + "]");
								if (iCnt < (tags.length-1)) // don't want instance data - assumed to be after last " / "
								{
									filelineNoDate = filelineNoDate.trim()
									filelineNoDate = UrlConverterHttp.detectAndConvertURLs (filelineNoDate);
									
									alTagsRtn.add (filelineNoDate);
								}
								setTagsAlreadyKnown.add filelineNoDate
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
