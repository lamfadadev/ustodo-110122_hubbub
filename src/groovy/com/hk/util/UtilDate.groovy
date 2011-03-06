package com.hk.util

class UtilDate {
	
	public static String renderAgeAsLetterFromNowToFileDateStr (String d)
	{
		try
		{
			// NOW
			def gc = new GregorianCalendar();
			long yyyy23 = gc.get(Calendar.YEAR);
			long mm23 = gc.get(Calendar.MONTH)+1;
			long dd23 = gc.get(Calendar.DAY_OF_MONTH);
			long hh23 = gc.get(Calendar.HOUR_OF_DAY);
			long mn23 = gc.get(Calendar.MINUTE);
			long ss23 = gc.get(Calendar.SECOND);

			// THEN
			// 012345678901234567890
			// 2007-12-27 11:40:01
			long yyyy = Long.parseLong (d[0..3])
			long mm = Long.parseLong (d[5..6])
			long dd = Long.parseLong (d[8..9])
			long hh = Long.parseLong (d[11..12])
			long mn = Long.parseLong (d[14..15])
			long ss = Long.parseLong (d[17..18])

			long ago =
					(yyyy23-yyyy) * 365 * 24 * 3600 +
					(mm23-mm)     * 30.5* 24 * 3600 +
					(dd23-dd)     *       24 * 3600 +
					(hh23-hh)     *            3600 +
					(mn23-mn)                *   60 +
					(ss23-ss);

			long _yyyy = 365 * 24 * 3600;
			long _mm =   30.5* 24 * 3600;
			long _ww =     7 * 24 * 3600;
			long _dd =         24 * 3600;
			long _hh =              3600;
			long _mn =                60;
			long _ss =                 1;

			if (ago < _ss)
				return "s"
			else if (ago < Math.round(ago/_mn))
			{
			}
			else if (ago < _hh)
			{
				int ageInMins = Math.round(ago/_mn);
				return ageInMins+"m"
			}
			
			else if (ago < _dd)
			{
				int ageInHours = Math.round(ago/_hh);
				return ageInHours+"h"
			}
			else if (ago < _ww)
			{
				int ageInDays = Math.round(ago/_dd);
				return ageInDays+"d"
			}
			else if (ago < _mm) 
			{
				int ageInWeeks = Math.round(ago/_ww);
				return ageInWeeks+"w"
			}
			else if (ago < _yyyy)
			{
				int ageInMo = Math.round(ago/(int) (30.5*24*3600));
				return ageInMo+"m"
			}
			
			return "" 


//				if (ago > _yyyy)
//				return "y"
//			else if (ago > _mm)
//				return "m"
//			else if (ago > _ww)
//				return "w"
//			else if (ago > _dd)
//				return "d"
//			else if (ago > _hh)
//				return "h"
//			else if (ago > _mn)
//				return "m"
//			else if (ago > _ss)
//				return "s"
//			else // subsecond
//				return"s"
		} catch ( Exception e)
		{
			System.err.println ("error converting date ["+d+"] " + e.getMessage());
			//e.printStackTrace();
			return "E";
		}


	} // render age as letter 

	public static String getDateForFile()
	{
		def gc = new GregorianCalendar();
		long yyyy = gc.get(Calendar.YEAR);
		long mm = gc.get(Calendar.MONTH)+1;
		long dd = gc.get(Calendar.DAY_OF_MONTH);
		long hh = gc.get(Calendar.HOUR_OF_DAY);
		long mn = gc.get(Calendar.MINUTE);
		long ss = gc.get(Calendar.SECOND);

		return "" + yyyy + "-" + pz(mm) + "-" + pz(dd) + " " + pz(hh) + ":" +pz(mn) + ":" + pz(ss);

	}
	
	// padZeroToTwoWide
	public static String pz(long l) {
		String s = "" + l
		if (s.length() ==2 )
			return s
		else 
			return "0" + s;

	}
}


//package com.hk.util
//
//class UtilDate {
//	
//	public static String renderAgeAsLetterFromNowToFileDateStr (String d)
//	{
//		try
//		{
//			// NOW
//			def gc = new GregorianCalendar();
//			long yyyy23 = gc.get(Calendar.YEAR);
//			long mm23 = gc.get(Calendar.MONTH)+1;
//			long dd23 = gc.get(Calendar.DAY_OF_MONTH);
//			long hh23 = gc.get(Calendar.HOUR_OF_DAY);
//			long mn23 = gc.get(Calendar.MINUTE);
//			long ss23 = gc.get(Calendar.SECOND);
//
//			// THEN
//			// 012345678901234567890
//			// 2007-12-27 11:40:01
//			long yyyy = Long.parseLong (d[0..3])
//			long mm = Long.parseLong (d[5..6])
//			long dd = Long.parseLong (d[8..9])
//			long hh = Long.parseLong (d[11..12])
//			long mn = Long.parseLong (d[14..15])
//			long ss = Long.parseLong (d[17..18])
//
//			long ago =
//					(yyyy23-yyyy) * 365 * 24 * 3600 +
//					(mm23-mm)     * 30.5* 24 * 3600 +
//					(dd23-dd)     *       24 * 3600 +
//					(hh23-hh)     *            3600 +
//					(mn23-mn)                *   60 +
//					(ss23-ss);
//
//			long _yyyy = 365 * 24 * 3600;
//			long _mm =   30.5* 24 * 3600;
//			long _ww =     7 * 24 * 3600;
//			long _dd =         24 * 3600;
//			long _hh =              3600;
//			long _mn =                60;
//			long _ss =                 1;
//
//			if (ago > _yyyy)
//				return "y"
//			else if (ago > _mm)
//				return "m"
//			else if (ago > _ww)
//				return "w"
//			else if (ago > _dd)
//				return "d"
//			else if (ago > _hh)
//				return "h"
//			else if (ago > _mn)
//				return "m"
//			else if (ago > _ss)
//				return "s"
//			else // subsecond
//				return"s"
//
//
//		} catch ( Exception e)
//		{
//			System.err.println ("error converting date ["+d+"] " + e.getMessage());
//			//e.printStackTrace();
//			return "E";
//		}
//
//
//	} // render age as letter
//
//	public static String getDateForFile()
//	{
//		def gc = new GregorianCalendar();
//		long yyyy = gc.get(Calendar.YEAR);
//		long mm = gc.get(Calendar.MONTH)+1;
//		long dd = gc.get(Calendar.DAY_OF_MONTH);
//		long hh = gc.get(Calendar.HOUR_OF_DAY);
//		long mn = gc.get(Calendar.MINUTE);
//		long ss = gc.get(Calendar.SECOND);
//
//		return "" + yyyy + "-" + pz(mm) + "-" + pz(dd) + " " + pz(hh) + ":" +pz(mn) + ":" + pz(ss);
//
//	}
//	
//	// padZeroToTwoWide
//	public static String pz(long l) {
//		String s = "" + l
//		if (s.length() ==2 )
//			return s
//		else
//			return "0" + s;
//
//	}
//}