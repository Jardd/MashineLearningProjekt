package wordNet;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WorkingAlgorithm {
/**
 * 04.01.15
 * @author Tobias Göbel
 * @version 1.0
 */
	
	
	public static ArrayList<String> getSenses(String inputWord, String pos) throws IOException {
		/**
		 * @param String inputWord, String pos
		 * @return ArrayList<String> outputList
		 * die ausgabe hat die form ["'nummer des ersten synset' : 'text zum ersten synset'", "'nummer des zweiten synset' : 'text zum zweiten synset'", ...]
		 * @throws IOException
		 * Der Algorithmus erhält ein Wort und den zugehörigen POS. Der String, der den POS beschreibt, wird verwendet, um auf den entsprechenden Datensatz
		 * aus dem Wordnet 2.1 zuzugreifen. Dann liest der Algorithmus den entsprechenden Datensatz zeilenweise aus und übergibt den Inhalt sowie das 
		 * InputWord der Methode PatternSeeker.seekPattern()
		 */
		//pos wird umbenannt, um danach die entsprechende datei aufzurufen, in der die wortarten gespeichert sind.
		if (pos == "n") {
			pos = "noun";
		}
		else if (pos == "v") {
			pos = "verb";
		}
		else if (pos == "a") {
			pos = "adj";
		}
		else if (pos == "r") {
			pos = "adv";
		}
		else {
			System.out.println("Fehler: pos hat einen ungültigen Wert: " + pos);
		}
		
		ArrayList<String> outputList = new ArrayList<String>();
		String dataDocument = "data/data." + pos; //link zum datenset
		
		//datei wird ausgelesen
		try{
			FileReader inputFile = new FileReader(dataDocument);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			String line;
			while ((line = bufferReader.readLine()) != null)   {
				String temp = PatternSeeker.seekPattern(inputWord, line);
				if (temp != "") {
					outputList.add(temp);
				}
			}
			
			bufferReader.close();
		}
		catch(Exception e) {
	        System.out.println("Error while reading file line by line:" + e.getMessage());
	    }

		System.out.println("outputList:");
		System.out.println(outputList);
	return outputList;
	}
}

