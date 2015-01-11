package wordNet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PatternSeeker {
	/**
	 * 09.01.15
	 * @author Tobias Göbel
	 * @version 1.0
	 */
    public static String seekPattern(String inputWord, String inputLine) {
    	/**
    	 * @param String inputWord, String InputFile
    	 * @return ArrayList<String> outputList
    	 * Die Methode erhält ein inputWord sowie eine Textzeile. Sie überprüft, ob das inputWord in der Zeile steht. Wenn ja, gibt es einen String zurück
    	 * in der Form "'Nummer des Synsets' | 'beschreibender Text zum Synset'".
    	 */
    	String output = "";
    	Pattern pattern = Pattern.compile("^\\d{8}\\s\\d{2}\\s[a-z]\\s\\d{2}\\s");		// alles vor dem gesuchten wort
    	Pattern pattern2 = Pattern.compile("^([a-zA-Z_]+\\s\\d\\s)*"+inputWord +"\\s");	// (beliebig viele andere worte vorher und) gesuchtes wort
    	Pattern pattern3 = Pattern.compile("\\|\\s+[a-zA-Z\\s-]+");						// text direkt nach dem '|', der das Synset beschreibt. wird beendet durch zeichen wie ';'
    	Pattern pattern4 = Pattern.compile("\\d{8}");									// synset, in dem man gerade ist
        Matcher matcher = pattern.matcher(inputLine);
        String group3 = "";
        String group4 = "";
        boolean g3 = false;
        boolean g4 = false;
        //geprüft wird, ob die zeile die gesuchte form hat. dann wird alles vor dem lemma abgeschnitten
        if (matcher.find()) {
    		String modifiedLine = inputLine.substring(matcher.end(), inputLine.length());
    		Matcher matcher2 = pattern2.matcher(modifiedLine);
    		//gesucht wird nach dem zielwort am anfang der abgeschnittenen zeile. wenn es gefunden wird, wird der zugehörige text in der zeile gesucht und in den outputString gepackt.
        	if (matcher2.find()) {
        		Matcher matcher3 = pattern3.matcher(modifiedLine);
        		Matcher matcher4 = pattern4.matcher(inputLine);
        		if (matcher3.find()) {
        			group3 = matcher3.group();
        			g3 = true;
        		}
        		if (matcher4.find()) {
        			group4 = matcher4.group();
        			g4 = true;
        		}
        		if (g3==true && g4 ==true) {
        			output = group4 + group3;
        		}
        	}
        }
        return output;
    }
}