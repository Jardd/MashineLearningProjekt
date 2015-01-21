package wsd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataExtraction.DataReader;

	
public class Execute {
	
	private File file;
	private FileWriter writer;
	private ArrayList<Sentence> sentences=new ArrayList<Sentence>();
	private ArrayList<String> functionWords=new ArrayList<String>();
	
	public Execute(List<String> text){
		setFunctionWords("FunctionWordsEnglish\\EnglishAuxiliaryVerbs.txt");
		setFunctionWords("FunctionWordsEnglish\\EnglishConjunctions.txt");
		setFunctionWords("FunctionWordsEnglish\\EnglishDeterminers.txt");
		setFunctionWords("FunctionWordsEnglish\\EnglishPrepositions.txt");
		setFunctionWords("FunctionWordsEnglish\\EnglishPronouns.txt");
		setFunctionWords("FunctionWordsEnglish\\EnglishQuantifiers.txt");
	
	
		this.file = new File("Senses.txt");
	    try {
			writer = new FileWriter(file);
			
		//Crate Sentences
		makeSentences(text);
		for(Sentence s:sentences){
			//System.out.println("in for execute");
			//Calculate lesk and PR for every sentence
			algorithem(s);
//			System.out.println("after algorithem");
			//System.out.println(s.getWordsInSentence().length);
			//get bet senses and write them into a file
			for(Word w:s.getWordsInSentence()){
				//System.out.println("in for w get words in sentence");
				if(getBestSense(w)!=null){//is null if no sense was found for this word
					writeInFile(getBestSense(w));
				}
				
			}
			
			
		}
		writer.close();
	    System.out.println("file written to :"+file.getAbsolutePath());
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	
	public ArrayList<String> getFunctionWords() {
		return functionWords;
	}


	public void setFunctionWords(String functionWordspath) {
		try {
			BufferedReader reader=new BufferedReader(new FileReader(functionWordspath));
			ArrayList<String> tmp=null;
			String line;
		    while ((line = reader.readLine()) != null) {
		    	//System.out.println(line);
		       if(line.startsWith("//")==false){
		    	   //System.out.println(line);
		    	   functionWords.add(line);
		       }
		    }
		    //functionWords=tmp;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//	public Sentence getSentences() {
//		return sentences;
//	}
//
//	public void setSentences(Sentence sentence) {
//		this.sentences = sentence;
//	}
	//Text einlesen. 
	//Satz für Satz
	public void makeSentences(List<String> text){
	//	System.out.println("in macesentence");
		
		//Text= [wort-wortart,...,...]
		Pattern p=Pattern.compile(".*- CODE");
		ArrayList<String> tmpSentence=new ArrayList<String>();
		ArrayList<String> words=new ArrayList<String>();
		ArrayList<String> wordclasses=new ArrayList<String>();
		
		for(String wordAndWordclass:text){
			Matcher m=p.matcher(wordAndWordclass);
			int counter=0;
			//System.out.println(wordAndWordclass);
			//System.out.println(m.matches());
			if(m.matches()==true && tmpSentence!=null){
				//System.out.println("in if makesentence");
				//fertigen satz(String) in wort und wortarten teilen
				for(String s:tmpSentence){
					String[] tmp=s.split(" - ");
					if(functionWords.contains(tmp[0])==false){//Nur wenn es kein Funktionswort ist soll es verarbeitet werden
//						System.out.println(tmp[1].equalsIgnoreCase("n")||tmp[1].equalsIgnoreCase("v")/*("v||n")==true*/);
//						System.out.println(tmp[1]);
						
						
						if(tmp[0].startsWith("\'")==false&& (tmp[1].equalsIgnoreCase("n")||tmp[1].equalsIgnoreCase("v"))){
							//System.out.println("in if add words and class");
							words.add(tmp[0]);//Wort wird hinzugefuegt
							wordclasses.add(tmp[1]);//Wortart wird hinzugefuegt
						}
					
					}
				}
				//tmpSentenz wieder leeren, damit neuer sentence eingefuegt werden kann
				tmpSentence=new ArrayList();
				Sentence sentence=new Sentence(words, wordclasses);
				counter++;
				this.sentences.add(sentence);
				words=new ArrayList<String>();
				wordclasses=new ArrayList<String>();
				
//				System.out.println("tmp sentence (sollte leer sein)"+tmpSentence.toString());
//				System.out.println("Satz nummer :"+counter+" wurde erstellt");
//				//System.out.println("make sentenze size: "+sentence.getWordsInSentence().length);
				
				
			}else{
				//System.out.println("in else");
				//element satz hinzufügen
				
				tmpSentence.add(wordAndWordclass);
				//System.out.println("tmp sentence :"+tmpSentence.toString());
				
			}
		}
		
		//Sentence Objekte, Word Objekte und Vertice Ofjekte werden erstellt
//		Sentence sentence=new Sentence(satz);
//		this.setSentence(sentence);
		
	}
	

	//Algotithmus rechnen.
	
	public void algorithem(Sentence sentence){
		//System.out.println("in algorithem");
		SimilarityMeasure sm=new SimilarityMeasure(sentence);
		for(Word word:sentence.getWordsInSentence()){
			for(Vertice v:word.getVertices()){
				PageRank_2 pr=new PageRank_2(v);
				pr.calculateTMPPageRank();
				//System.out.println(v.getPageRank());
			}
			
		}
		for(Word word:sentence.getWordsInSentence()){
			for(Vertice v:word.getVertices()){
				PageRank_2 pr=new PageRank_2(v);
				pr.calculatePageRank();
				//SSystem.out.println(v.getPageRank());
			}
			
		}
		
	}
	
	
	//Besten Sense bekommen
	public Vertice getBestSense(Word word){
		//System.out.println("in get best sense");
		Vertice[] vertices=word.getVertices();
		Vertice bestSense=null;
		if(vertices.length>0){
			if(vertices.length>1){
				//System.out.println("in if best sense");
				Double test=new Double(vertices[0].getPageRank());
//				System.out.println("ptint pagerank "+test.doubleValue());
				Double max=vertices[0].getPageRank();
			
		
				for(int i=1; i<vertices.length;i++){
//					System.out.println("in for best sense");
//					System.out.println("max: "+max);
//					System.out.println("pr :"+vertices[i].getPageRank());
					Double nextVertex=vertices[i].getPageRank();
					int value=nextVertex.compareTo(max);
//					System.out.println("value: "+value);
					if ( value>0) {
						//System.out.println("in if2 best sense");
						max = vertices[i].getPageRank();
						bestSense=vertices[i]; 
					}else if((nextVertex.compareTo(max))==0){
					//was tun bei gleichen pagerank
					//häufigsten wählen
					//überganglösung einfach ersten sense wähln. muss noch bearbeitet werden!!
						//System.out.println("in else1 best sense");
						bestSense=vertices[0];
					}
				}
			}else{
//				System.out.println("in else2 best sense");
				bestSense=vertices[0];
			}
		}
		
		return bestSense;
	}
	
	
	//Ergebnisse in File Schreiben
	
	public void writeInFile(Vertice bestSense){
	//System.out.println(bestSense.getIndex());
    
   // if(bestSense!=null){
    	Integer senseNumber=bestSense.getIndex();
    	String word=bestSense.getWord().getWordstring();
    	//System.out.println(word);
    	
    	try {
//    	System.out.println("writing in file..");
    	// Writes the content to the file
		writer.write(word);
		//System.out.println(word);
		writer.write("\t");
		writer.write(bestSense.getIndex().toString());
		//System.out.println(bestSense.getIndex().toString());
		writer.write("\n");
		writer.flush();
		//writer.close();
    	} 
    	catch (IOException e) {
		e.printStackTrace();
    	} 
    //}
    
    
    
	}
	
	public static void main(String[] args){
		List<String> text = DataReader.parseTheCorpus("Corpus\\test_cnn_parse.txt","Corpus\\test_cnn_names.txt");
		Execute execute=new Execute(text);
	}

}
