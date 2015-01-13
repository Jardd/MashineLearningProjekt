package wsd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

	
public class Execute {
	
	private File file;
	private FileWriter writer;
	private Sentence sentence;
	
	public Execute(String text){
		
		this.file = new File("Hello1.txt");
	    
	    // creates a FileWriter Object
	    try {
			writer = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	public Sentence getSentence() {
		return sentence;
	}

	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}
	//Text einlesen. 
	//Satz für Satz
	public void makeSentence(String satz){
		//Sentence Objekte, Word Objekte und Vertice Ofjekte werden erstellt
		Sentence sentence=new Sentence(satz);
		this.setSentence(sentence);
		
	}
	

	//Algotithmus rechnen.
	
	
	
	
	//Besten Sense bekommen
	public Vertice getBestSense(Word word){
		
		Vertice[] vertices=word.getVertices();
		double max=vertices[0].getPageRank();
		Vertice bestSense=null;
		
		for(int i=1; i<vertices.length;i++){
			if ( vertices[i].getPageRank()> max) {
			      max = vertices[i].getPageRank();
			      bestSense=vertices[i]; 
			      }
		}
		return bestSense;
	}
	
	
	//Ergebnisse in File Schreiben
	
	public void writeInFile(Vertice bestSense){
	
    int senseNumber=bestSense.getIndex();
    String word=bestSense.getWord().getWordstring();
    
    try {
    	// Writes the content to the file
		writer.write(word);
		writer.write("\t");
		writer.write(senseNumber);
		writer.flush();
		writer.close();
    } 
   catch (IOException e) {
		e.printStackTrace();
	} 
	}

}
