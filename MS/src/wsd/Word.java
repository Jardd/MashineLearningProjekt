package wsd;

import java.io.IOException;
import java.util.ArrayList;

import wordNet.WorkingAlgorithm;

public class Word {
	
	
	private ArrayList<String> senses;
	private Vertice[] vertices;
	private String wordString;
	/**
	 * Constructor
	 * @param word 
	 * @param wordclass
	 */
	public Word(String word, String wordclass){
		this.wordString=word;
		if (getSenses(word, wordclass)!=null){
			senses=getSenses(word, wordclass);
			vertices=new Vertice[senses.size()];
		
			for (int i = 0; i<senses.size(); i++){
				String senseAndIndex=senses.get(i);
				String[]tmp=senseAndIndex.split(":");
				String sense=tmp[1];
				int index=Integer.parseInt(tmp[0]);
			
			
				Vertice v=new Vertice(sense,this);
				v.setSenseDiscription(sense);
				v.setIndex(index);
				vertices[i]=v;
			}
		}
	}
	
	/**
	 * Get Senses for a word from Wordnet
	 * @param wordOne
	 * @param wordclass
	 * @return ArrayList<String> mit Senses und Index. Bsp. ["nummer des Senses1 aus Wordnet : Sense2", "nummer des Senses1 aus Wordnet : Sense2"]
	 */
	public ArrayList<String> getSenses(String wordOne, String wordclass){
		
		try {
			ArrayList<String> sense=null;
			sense = WorkingAlgorithm.getSenses(wordOne, wordclass);
			
			return sense;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

	public String getWordstring() {
		return wordString;
	}

	public void setWordString(String word) {
		this.wordString = word;
	}

	public Vertice[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertice[] vertices) {
		this.vertices = vertices;
	}
}
