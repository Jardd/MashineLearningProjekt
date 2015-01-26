package wsd;

public class Edge {
	
	private Vertice verticeOne;
	private Vertice verticeTwo;
	private Double value=new Double(0);
	
	public Edge(Vertice vertice_One,Vertice vertice_Two){
		verticeTwo=vertice_Two;
		verticeOne=vertice_One;
		setValue();
	}

	public Double getValue() {
		return value;
	}

	public Vertice getVerticeOne() {
		return verticeOne;
	}

	public Vertice getVerticeTwo() {
		return verticeTwo;
	}

	public void setValue() {
		String[] verticeOneWords = verticeOne.getSenseDiscription().split(" ");
		String[] verticeTwoWords = verticeTwo.getSenseDiscription().split(" ");
		int wordsInCommon=0;
		for (String w:verticeOneWords){
			if(Execute.getFunctionWords().contains(w)==false){
				for (String w2: verticeTwoWords){
					if(w.equals(w2)){
						value+=wordsInCommon++;
					}
				}
			}
		}
	}
	public Vertice[] getAllVertices(){
		Vertice[] allVertices={this.verticeOne, this.verticeTwo};
		return allVertices;
		
	}

}
