package wordNet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PatternSeeker {
	/**
	 * 17.01.15
	 * @author Tobias Göbel
	 * @version 1.1
	 * Neuerungen: seekSynsets implementiert. Alle Zeichen außer Buchstabend und Leerzeichen aus der Ausgabe von seekPattern genommen.
	 * Pattern implementiert, der nicht nur die Beschreibung, sondern auch Beispielsätze mit ausgibt.
	 */
    public static String seekPattern(String inputWord, String inputLine) {
    	/**
    	 * @param String inputWord, String InputFile
    	 * @return ArrayList<String> outputList
    	 * Die Methode erhält ein inputWord sowie eine Textzeile. Sie überprüft, ob das inputWord in der Zeile steht. Wenn ja, gibt es einen String zurück
    	 * in der Form "'Nummer des Synsets' | 'beschreibender Text zum Synset'".
    	 */
    	String output = "";
        String group3 = "";
        String group4 = "";
        String outputWords = "";
    	Pattern pattern = Pattern.compile("^\\d{8}\\s\\d{2}\\s[a-z]\\s\\d{2}\\s");		// alles vor dem gesuchten wort
    	Pattern pattern2 = Pattern.compile("^([a-zA-Z_]+\\s\\d\\s)*"+inputWord +"\\s");	// (beliebig viele andere worte vorher und) gesuchtes wort
    																					//text direkt nach dem '|', der das Synset beschreibt. wird beendet durch zeichen wie ';'
    	//Pattern pattern3 = Pattern.compile("\\|[^\"]*");									// Variante 1: nur der beschreibende Text
    	Pattern pattern3 = Pattern.compile("\\|.*");									// Variante 2: beschreibender Text und alle Beispiele
    	Pattern pattern4 = Pattern.compile("\\d{8}");									// synset, in dem man gerade ist
    	Pattern justLetters = Pattern.compile("[a-zA-Z ]*");
        Matcher matcher = pattern.matcher(inputLine);
        boolean g3 = false;
        boolean g4 = false;
        //geprüft wird, ob die zeile die gesuchte form hat. dann wird alles vor dem lemma abgeschnitten
        if (matcher.find()) {
    		String modifiedLine = inputLine.substring(matcher.end());
    		Matcher matcher2 = pattern2.matcher(modifiedLine);
    		//gesucht wird nach genau dem zielwort an der richtigen Stelle der abgeschnittenen zeile. wenn es gefunden wird, wird der zugehörige text in der zeile gesucht und in den outputString gepackt.
        	if (matcher2.find()) {
        		Matcher matcher3 = pattern3.matcher(modifiedLine);
        		Matcher matcher4 = pattern4.matcher(inputLine);
        		if (matcher3.find()) {
        			group3 = matcher3.group();
        			Matcher wordsMatcher = justLetters.matcher(group3);
        			while (wordsMatcher.find()) {
        				outputWords += wordsMatcher.group();
        				g3 = true;
        			}
        		}
        		if (matcher4.find()) {
        			group4 = matcher4.group();
        			g4 = true;
        		}
        		if (g3==true && g4 ==true) {
        			output = group4 + outputWords;
        		}
        	}
        }
        return output;
    }
    
    public static String seekSynsets(String inputWord, String pos) {
    	/**
    	 * @param String inputWord, String pos
    	 * @return ArrayList<String> outputList
    	 * Die Methode erhält ein inputWord sowie den Part of Speech des gesuchten Wortes (noun, verb, adj, adv). Sie sucht das entsprechende Wort in der index Datei des Wordnet
    	 * und gibt alle zugehörigen Synsets in der Reihenfolge ihres Auftretens zurück, getrennt durch jeweils ein Leerzeichen.
    	 */
    	String dataDocument = "data/index." + pos; //link zum datenset
    	String synSets = "";
		try{
			FileReader inputFile = new FileReader(dataDocument);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			String line;
			Pattern pattern1 = Pattern.compile("^"+inputWord+" ");							//name des Zielwortes
			Pattern pattern2 = Pattern.compile("\\d{8}");							//name des wortes in der entsprechenden Zeile der index Datei
			while ((line = bufferReader.readLine()) != null)   {
				Matcher matcher1 = pattern1.matcher(line);
				Matcher matcher2 = pattern2.matcher(line);
				if (matcher1.find()) {
					System.out.println(matcher1.group());
					while (matcher2.find()) {
						synSets += " " + matcher2.group();
					}
				}
			}
			
			
			bufferReader.close();
		}
		catch(Exception e) {
	        System.out.println("Error while reading file line by line:" + e.getMessage());
	    }
		if (!synSets.equals("")) {
			synSets = synSets.substring(1);
		}
		System.out.println(synSets+"a");
    	return synSets;
    }
}