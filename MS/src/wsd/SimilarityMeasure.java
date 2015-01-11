package wsd;
/*
 * Calculates which sense correspond to an other sense from a different word and assigns a value.
 */
public class SimilarityMeasure {

	
	/*
	 * Constructor
	 */
	public SimilarityMeasure(String wordOne, String wordTwo){

		
	}
	/*
	 * Sets all Edges between vertices from different words in one sentence
	 * 
	 */	
	public void calculation(Sentence sentence){
		for(Word w:sentence.getWordsInSentence()){
			for(Vertice v:w.getVertices()){
				for(Word word:sentence.getWordsInSentence()){
					if(word !=w){
						Vertice[] compareVertice =word.getVertices();
						for(Vertice cv:compareVertice){
							Edge edge=new Edge(v,cv);
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
