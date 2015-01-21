package wsd;
/*
 * Calculates which sense correspond to an other sense from a different word and assigns a value.
 */
public class SimilarityMeasure {

	
	private Sentence sentence;
	/**
	 * Constructor
	 */
	public SimilarityMeasure(Sentence sentence){
		this.sentence=sentence;
		calculation(this.sentence);

		
	}
	/**
	 * Sets all Edges between vertices from different words in one sentence
	 * 
	 */	
	public void calculation(Sentence sentence){
		//Iterates over all words
		for(Word w:sentence.getWordsInSentence()){
			//Iterates over all Vertices
			for(Vertice v:w.getVertices()){
				//Iterates over all words in one sentence
				for(Word word:sentence.getWordsInSentence()){
					if(!word.equals(w)){//We dont want to compare a vertex to another vertex of the same word
						Vertice[] compareVertice =word.getVertices();
						for(Vertice cv:compareVertice){
							Edge edge=new Edge(v,cv);
							//System.out.println("edge value: "+edge.getValue());
							v.setEdges(edge);
						}
					}	
				}				
			}
		}
	}
	
	

	/*
	 * Calculates how many Words has one sense description with another
	 */
//	public int wordsInCommon(Vertice verticeOne, Vertice verticeTwo){
//		String[] verticeOneWords = verticeOne.getSenseDiscription().split(" ");
//		String[] verticeTwoWords = verticeTwo.getSenseDiscription().split(" ");
//		int wordsInCommon=0;
//		for (String w:verticeOneWords){
//			for (String w2: verticeTwoWords){
//				if(w==w2){
//					wordsInCommon++;
//				}
//			}
//		}
//		return wordsInCommon;
//	}
	
	
	
}
