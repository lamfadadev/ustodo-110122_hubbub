package com.hk.util;

import java.util.Calendar;

import java.sql.Date;

class FileLine {
	String lineMinusDateStr
	String date
	int fileLineNumThisLine1Based
	String ageLetter

	public FileLine(int fileLineNumThisLine1Based, String fileLineRaw)
	{
		try {
			this.fileLineNumThisLine1Based = fileLineNumThisLine1Based
			date = fileLineRaw[0..18]
			lineMinusDateStr = fileLineRaw[19..-1]
			ageLetter = com.hk.util.UtilDate.renderAgeAsLetterFromNowToFileDateStr (date)
		} catch ( Exception e) {
			O.o ("error constructing fileline from:" + fileLineRaw)
		}
		//O.o ("idx:" + i + ". FileLine: date [" + date + "] line [" + line + "]");
	}



}