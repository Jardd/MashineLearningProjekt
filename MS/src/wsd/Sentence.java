package wsd;

public class Sentence {
	
	private Word[] wordsInSentence;
	private String[] words;

	public Sentence(String sentence){
		words=sentence.split(" ");
		
		
		for (int i = 0; i<words.length; i++){
			Word w=new Word(words[i]);
			wordsInSentence[i]=w;
		}
	}

	public Word[] getWordsInSentence() {
		return wordsInSentence;
	}

	public void setWordsInSentence(Word[] wordsInSentence) {
		this.wordsInSentence = wordsInSentence;
	}

}
