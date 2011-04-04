package com.hk.util

import java.io.BufferedReader;
import com.hk.util.O;

// some crufty file utils 

class UtilFile {

//	public static String[] getFileAsStrArray (String filename, boolean bRespectComments, String commentChar, long start)
//	{
//		try
//		{
//			String stextline;
//			BufferedReader inx = null;
//			inx = new BufferedReader(new FileReader(filename));
//			int iCnt = -1;
//			Vector v = new Vector();
//			while (inx.ready())     {
//				iCnt++;
//				if (iCnt < start)
//					continue;
//				stextline = inx.readLine().trim();
//				if (!stextline.startsWith("#") && !stextline.equals("")) // if not a comment and not blank
//				{
//					v.add(stextline);
//					// iRet = Integer.valueOf(stextline).intValue();
//				}
//			}
//			inx.close();
//			
//			String[] sArr = v.toArray(new String[0]);
//			//String[] sArr = new String[v.size()];
//			//for ( int i = 0; i < v.size(); i++)
//			//{
//			//	System.err.println (v.get(i).getClass().getName());
//			//	sArr[i] = (String) v.get(i);
//			//}
//			
//			return sArr;
//		}
//		catch ( Exception e )
//		{
//			System.err.println("error in file:" + filename, e);
//			O.or("error in file:" + filename, e);
//		}
//		return null;
//		
//	}
	public static void TestHk()
	{
		
	}

	public static long fileLen (String filename)
	{
		try
		{
			BufferedReader inx = null;
			inx = new BufferedReader(new FileReader(filename));
			long lCnt = 0;
			while (inx.ready())     
			{
				lCnt++;
				inx.readLine().trim();
			}
			inx.close();
			
			return lCnt;
		}
		catch ( Exception e )
		{
			System.err.println("error in file:" + filename, e);
			O.or("error in file:" + filename, e);
		}
		return 0;
		
	}

	public static List fileAsList (String filename, Boolean bRespectComments, String commentChar, Long numLinesWanted)
	{
		long lineCountToStartKeepAt = fileLen(filename) - numLinesWanted;
		List alRtn = new ArrayList<String>();
		try
		{
			String stextline;
			BufferedReader inx = null;
			inx = new BufferedReader(new FileReader(filename));
			int iCnt = -1;
			//O.o "start:" + lineCountToStartKeepAt;
			while (inx.ready())     
			{
				iCnt++;
				stextline = inx.readLine().trim();
				if (iCnt < lineCountToStartKeepAt)
					continue;
				if (!stextline.startsWith("#") && !stextline.equals("")) // if not a comment and not blank
				{
					//O.o("in UtilFIle, got  [" + iCnt + "] iter" + stextline, false);
						 
					alRtn.add(stextline);
					// iRet = Integer.valueOf(stextline).intValue();
				}
			}
			// O.o("added file lines [" + iCnt + "]");
			inx.close();
			
			return alRtn;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			System.err.println("error in file:" + filename, e);
			O.or("error in file:" + filename, e);
		}
		return null;
		
	}

	// convert string array to int number array
	// allows us to filter secIDs in EOD assignment or calc loop on the fly using
	// if ((new File(sDebugFileName)).exists())
	//     CalcTrace.convertStrArrayToIntArr((CalcTrace.getFileAsStrArray(sDebugFileName));
	// and then only calcing or assigning if the secID matches
	public static long[] cvtSArrToLArr (String[] sArrNums)
	{
		long[] iArr = new long[sArrNums.length];
		for (int i = 0; i < sArrNums.length; i++)
			iArr[i] = Long.parseLong(sArrNums[i]);
		return iArr;
	}
	
	public static HashSet cvtLarrToHs(long[] lArr)
	{
		HashSet hs = new HashSet();
		for (int i = 0; i < lArr.length; i++)
		{
			Long L = new Long(lArr[i]);
			hs.add(L);
		}
		return hs;
	}
	
	//	public static HashSet getFileAsLongHashSet(String filename)
	//	{
	//		return cvtLarrToHs(cvtSArrToLArr(CalcTrace.getFileAsStrArray(filename)));
	//	}

	
	public static String arrayToString(String[] a, String separator) {
		StringBuffer result = new StringBuffer();
		if (a.length > 0) {
			result.append(a[0]);
			for (int i=1; i<a.length; i++) {
				result.append(separator);
				result.append(a[i]);
			}
		}
		return result.toString();
	}
	
	
}
