package wordNet;

import java.io.IOException;


public class Manager {

	public static void main(String[] args) {
    	
		try {
    		WorkingAlgorithm.getSenses("rainbow", "n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

