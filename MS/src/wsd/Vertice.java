package wsd;

import java.util.ArrayList;

public class Vertice {
	
	private ArrayList<Edge> edges=new ArrayList<Edge>(); //[index of word in sentence][index of sense in word][value]
	private String senseDiscription;
	private Word word;
	private Integer index=new Integer(00000000);
	private double pageRank=0.0;
	private double tmpPageRank=0.0;
	
	public Vertice(String sense, Word word){
		this.word=word;
		
		
	}

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
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
		this.pageRank = pageRank;
		
	}

	public double getTmpPageRank() {
		return tmpPageRank;
	}

	public void setTmpPageRank(double tmpPageRank) {
		this.tmpPageRank = tmpPageRank;
	}


}
