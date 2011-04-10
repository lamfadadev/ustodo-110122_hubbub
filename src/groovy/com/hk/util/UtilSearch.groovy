package com.hk.util

import java.util.ArrayList;

class UtilSearch {
	
	public static ArrayList<String> getAutoCompleteLinesOut(ArrayList<String> fileLines, String autocomp, long countwanted, String fqFileName)
	{
		
		long fileLen = com.hk.util.UtilFile.fileLen (fqFileName);
		long lineCountToStartKeepAt = (Math.max(0,fileLen - countwanted));

		fileLines = fileLines.reverse ();
				//String[] sarr = (String[] ) fileLines.toArray(new String[0] a);
		ArrayList alLinesReturn = new ArrayList();
		if (autocomp.length() > 2)
		{
			fileLines.each {
				String fileLine = it;
				if (fileLine.length() > 20 && UtilSearch.match ( fileLine,  autocomp))
					alLinesReturn.add  UtilDate.renderAgeAsLetterFromNowToFileDateStr(fileLine [0..18]) + " &nbsp;" + fileLine [20..-1]
			}
			
			//O.o ("generated [" + alTagsRtn.size() + "] tags from [" + fileLines.size()+ "] filelines")
		}
		//O.o ("autocomp < size min")
		return alLinesReturn;
	}

	public static boolean match (String candidateLine, String searchCriteria) {
		try {
			boolean hitRemove = false;
			// FOR EACH SRCH WORD MATCH MATCH MATCH MATCH MATCH
			(searchCriteria.split(" ")).eachWithIndex
			{ srchWrd, ii ->
				// O.o("in here xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				srchWrd = srchWrd.trim().toLowerCase();
				if (!hitRemove)
				{
					if (srchWrd.startsWith("-")) // subtractive search
					{
						//O.o ((new Date()).toString() + "********* in sub testing neg on [" + srchWrd + "]");
						if (srchWrd.length() > 1 && candidateLine.contains(srchWrd[1..-1])) // / ignoreif there is a "-" alone
						{
							hitRemove = true;
							//O.o ((new Date()).toString() + "hit remove ");
						}
					}
					else // positive search
					{
						if (!candidateLine.contains(srchWrd))
						{
							hitRemove = true;
							//O.o ((new Date()).toString() + "hit remove: searchword [" + srchWrd + "] line [" + candidateLine+ "]");
						} else
						{
							//O.o ((new Date()).toString() + "found match: searchword [" + srchWrd + "] line [" + candidateLine+ "]");
						}
					}
				}
			}
			
			return !hitRemove;
		} catch ( Throwable t) 
		{
					O.o ("in throwable");
			t.printStackTrace();
		}
		return false; //assume no match if error
	}
	
	public static String colorTags (String uncoloredMatchedLine, String searchedFor, String scolor) 
	{
		if (searchedFor == null)
		{
			return uncoloredMatchedLine;
		}
		else if (uncoloredMatchedLine.indexOf("http") == -1)  //  no fear of mangling a url with coloring 
		{  
			//O.o "in here 3333333333";
			searchedFor.split(" ").each {
				String searchedForElement = it;
				if (!searchedForElement.startsWith("-"))
				{
					uncoloredMatchedLine = uncoloredMatchedLine.replaceAll (searchedForElement,"!!@@!!" + searchedForElement + "##%%##");
				}
			}
			uncoloredMatchedLine = uncoloredMatchedLine.replaceAll ("!!@@!!","<font color=" + scolor + ">");
			uncoloredMatchedLine = uncoloredMatchedLine.replaceAll ("##%%##","</font>");
			return uncoloredMatchedLine; // now colored
		}
		else if (uncoloredMatchedLine.lastIndexOf(" / ") > 0)
		{
			//O.o "in here 444444444444444444444444";
			String tagsOnly = uncoloredMatchedLine.substring (0, uncoloredMatchedLine.lastIndexOf(" / "));
			String theRest = uncoloredMatchedLine.substring (uncoloredMatchedLine.lastIndexOf(" / "));
			searchedFor.split(" ").each {
				String searchedForElement = it;
				if (!searchedForElement.startsWith("-"))
				{
					tagsOnly = tagsOnly.replaceAll (searchedForElement,"!!@@!!" + searchedForElement + "##%%##");
				}
			}
			tagsOnly = tagsOnly.replaceAll ("!!@@!!","<font color=" + scolor + ">");
			tagsOnly = tagsOnly.replaceAll ("##%%##","</font>");
			return tagsOnly + theRest;
		}
		
		else
		{
			//O.o "in here 55555555555555 on [" + uncoloredMatchedLine + "]";
			return uncoloredMatchedLine;
		}
	}
}
