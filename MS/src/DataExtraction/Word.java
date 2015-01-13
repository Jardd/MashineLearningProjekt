package DataExtraction;
//Makes it easier to create arrays.
public class Word {
	//Variables
		public String longstuff;
		public int sentence;
		public int word_in_sentence;
		public String lemma;
		public int sense;
		
		//Builder
		public Word(String longstuff, int sentence, int word_in_sentence, String lemma, int sense){
			this.longstuff = longstuff;
			this.sentence = sentence;
			this.word_in_sentence = word_in_sentence;
			this.lemma = lemma;
			this.sense = sense;
		}
		
		//get
		public String get_longstuff(){
			return this.longstuff;
		}
		public int get_sentence(){
			return this.sentence;
		}
		public int get_word_in_sentence(){
			return this.word_in_sentence;
		}
		public String get_lemma(){
			return this.lemma;
		}
		public int get_sense(){
			return this.sense;
		}
		
		//set
		public String set_longstuff(String neu){
			this.longstuff = neu;
			return this.longstuff;
		}
		public int set_sentence(int neu){
			this.sentence = neu;
			return this.sentence;
		}
		public int set_word_in_sentence(int neu){
			this.word_in_sentence = neu;
			return this.word_in_sentence;
		}
		public String set_word_type(String neu){
			this.lemma = neu;
			return this.lemma;
		}
		public int set_sense(int neu){
			this.sense = neu;
			return this.sense;
		}
}
