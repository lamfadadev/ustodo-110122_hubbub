package com.hk.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class UrlConverterHttp {
// from http://stackoverflow.com/questions/4201982/detect-and-convert-url-in-string-java-is-regex-better-or-even-faster
	
	
	public static String detectAndConvertURLs(String text) {
	    String[] parts = text.split("\\s");
	    String rtn = text;
	    for (String item : parts)
	        try {
	          // adjustment based one of the answers 
	          Pattern p = Pattern.compile("((mailto\\:|(news|(ht|f)tp(s?))\\://){1}\\S+)");
	          Matcher m = p.matcher(item);
	          if( m.matches() ) item = m.group(1);

	            URL url = new URL(item);
	            String link = url.getProtocol() + "://" + url.getHost()  + (url.getPath() == null ? "" : url.getPath()) + (url.getQuery() == null ? "" : "?" + url.getQuery());
	            O.o("link [" + link +  "]");
	            rtn = StringUtils.replace(rtn, item, "<a rel=\"nofollow\" href=\"" + link + "\">" + link + "</a> ");
	        } catch (MalformedURLException ignore) {
	        }
	    return rtn;
	}
	
	public static String detectAndConvertURLs2_NotWorkItSeems(String text) {
		   //Regex pattern (unescaped), matches any Internet URL: 
		   //((mailto\:|(news|(ht|f)tp(s?))\://){1}\S+)
		   Pattern p = Pattern.compile( "((mailto\\:|(news|(ht|f)tp(s?))\\://){1}\\S+)" );
		   Matcher m = p.matcher( text );
		   if( m.matches() ){
		      return m.group(1);
		   }else return null;
		}
}


