package com.hk.util

class UtilAutocomplete {
	
	public static String auto(Map params)
	{
		//	String textStr = params['textstr'];
		//	
		//	if (alfilelines == null) // needs to be session-scoped probably
		//	{
		//		O.o("re-read file");
		//		user1 = getUser();
		//		fqFileName = '/Users/hkon/sw/ustodo/favs' + user1 + '.csv';
		//		fqFileName_srchHist = '/Users/hkon/sw/ustodo/favs' + user1 + '_srchHist.csv';
		//		fqFileName_cachedCagtegs = '/Users/hkon/sw/ustodo/favs' + user1 + '_cachedCagtegs.csv';
		//		setTagsAlreadyKnown = new HashSet();
		//		alfilelines = com.hk.util.UtilFile.fileAsList (fqFileName, new Boolean(false), "", new Long(32000));
		//		alTagsHistoricalNewFirst = com.hk.util.UtilTags.convertMultiTagLinesToTagArray (alfilelines, setTagsAlreadyKnown);
		//		//alTagsHistoricalNewFirst = new ArrayList<String>();
		//		//alTagsHistoricalNewFirst.add "henry tag1"
		//		//alTagsHistoricalNewFirst.add "henry tag2"
		//	}
		//	else
		//	{
		//		if (!setTagsAlreadyKnown.contains(textStr))
		//		{
		//			setTagsAlreadyKnown.add textStr
		//			alTagsHistoricalNewFirst.add(textStr, 0);
		//		}
		//	}
		//	
		//	//ArrayList<String> alTagsHistoricalNewFirst1 = new ArrayList<String>();
		//	//alTagsHistoricalNewFirst1.add "henry tag1"
		//	//alTagsHistoricalNewFirst1.add "henry tag2"
		//	StringBuffer sb = new StringBuffer();
		//	if (textStr.trim().length() > 1)
		//	{
		//		
		//		alTagsHistoricalNewFirst.each {
		//			String tag = it;
		//			//O.o("in todo.autocompleteSearch tag [" + tag + "] textStr [" + textStr + "]")
		//			if (tag.contains(textStr))
		//			{
		//				//O.o("match appending [" + it.toString() + "]")
		//				sb.append ("   [" + it.toString() +  "]   ");
		//			}
		//		}
		//	}
		//	//else
		//	//	O.o  ("empty search")
		//
		//
		//
		//	//def results = com.grailsinaction.User.findAllByUserIdLike("%g%").userId
		//	String sRtn = sb.toString();
		//	//O.o("rtn [" + sRtn + "]")
		//	return sRtn;
		//	//def results = User.findAll()
		//	//render results as JSON
		//	//render new java.util.Date().toString();
	}
}
