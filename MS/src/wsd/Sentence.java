package wsd;

import java.util.ArrayList;

public class Sentence {
	
	private Word[] wordsInSentence;
	private ArrayList<String> words;

	public Sentence(ArrayList<String> sentence,ArrayList<String> wordclasses){
		//words=sentence.split(" ");
		//String wordklasses = null;
		wordsInSentence=new Word[sentence.size()];
		
		for (int i = 0; i<sentence.size(); i++){
			Word w=new Word(sentence.get(i), wordclasses.get(i));
			wordsInSentence[i]=w;
		}
//		System.out.println("saetz: ");
//		for (Word w:wordsInSentence){
//			System.out.print(w.getWordstring()+" ");
//		}
	}

	public Word[] getWordsInSentence() {
		return wordsInSentence;
	}

	public void setWordsInSentence(Word[] wordsInSentence) {
		this.wordsInSentence = wordsInSentence;
	}

}
