package questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionA {
	
	public static String firstWord(final Stream input) {
		Map<String, Integer> wordsOccurrenceCount = new HashMap<String, Integer>();
		List<String> wordsOrderOccurrence = new ArrayList<String>();
		
		StringBuffer word = new StringBuffer();
		while (input.hasNext()) {
			char c = input.getNext();
			if (String.valueOf(c).matches("[a-zA-Z0-9]")) {
				word.append(c);
			} else if (word.length() != 0) {
				String wordString = word.toString().toLowerCase();
				Integer wordOccurrenceCount = wordsOccurrenceCount.get(wordString);
				if (wordOccurrenceCount == null) {
					wordsOccurrenceCount.put(wordString, 1);
					wordsOrderOccurrence.add(wordString);
				} else {
					wordsOccurrenceCount.put(wordString, ++wordOccurrenceCount);
				}
				word = new StringBuffer();
			}
		}
		
		for (String aWord : wordsOrderOccurrence) {
			if (wordsOccurrenceCount.get(aWord) == 1) {
				return aWord;
			}
		}
		
		return "";
	}
	
    public static void main(String[] args) {
		Stream aStream =  new StringStream("The angry dog was red. And the cat was also angry.");
		System.out.println(firstWord(aStream));
	}
    
    static class StringStream implements Stream {
    	private String str;
    	private int currentPosition = 0;
    	
    	public StringStream(String str) {
    		this.str = str;
    	}

		@Override
		public char getNext() {
			return str.charAt(currentPosition++);
		}

		@Override
		public boolean hasNext() {
			return currentPosition != str.length();
		}
    }
}
