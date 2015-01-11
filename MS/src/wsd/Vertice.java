package wsd;

import java.util.ArrayList;

public class Vertice {
	
	private ArrayList<Edge> edges; //[index of word in sentence][index of sense in word][value]
	private String senseDiscription;
	private Word word;
	private int index;
	private double pageRank;
	
	public Vertice(String sense, Word word){
		this.word=word;
		
		
	}

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	private int[][][] calculateEdges() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSenseDiscription() {
		return senseDiscription;
	}

	public void setSenseDiscription(String senseDiscription) {
		this.senseDiscription = senseDiscription;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(Edge edge) {
		edges.add(edge);
	}

	public double getPageRank() {
		return pageRank;
	}

	public void setPageRank(double pageRank) {
		pageRank = pageRank;
	}


}
