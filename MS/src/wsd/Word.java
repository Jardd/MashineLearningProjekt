package wsd;

public class Word {
	
	
	private String[] senses;
	private Vertice[] vertices;

	public Word(String word){
		senses=getSenses(word);
		vertices=new Vertice[senses.length];
		
		for (int i = 0; i<senses.length; i++){
			Vertice v=new Vertice(senses[i],this);
			v.setSenseDiscription(senses[i]);
			v.setIndex(i);
			vertices[i]=v;
		}
	}
	
	//Platzhalterklasse
	public String[] getSenses(String wordOne){
		String[] example={"dddd"};
		return example;
	}

	public Vertice[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertice[] vertices) {
		this.vertices = vertices;
	}
}
