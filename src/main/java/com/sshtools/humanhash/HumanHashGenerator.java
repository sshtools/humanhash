package com.sshtools.humanhash;

import java.util.Arrays;

public class HumanHashGenerator {

	public static final String[] DEFAULT_WORDLIST = {
		    "ack", "alabama", "alanine", "alaska", "alpha", "angel", "apart", "april",
		    "arizona", "arkansas", "artist", "asparagus", "aspen", "august", "autumn",
		    "avocado", "bacon", "bakerloo", "batman", "beer", "berlin", "beryllium",
		    "black", "blossom", "blue", "bluebird", "bravo", "bulldog", "burger",
		    "butter", "california", "carbon", "cardinal", "carolina", "carpet", "cat",
		    "ceiling", "charlie", "chicken", "coffee", "cola", "cold", "colorado",
		    "comet", "connecticut", "crazy", "cup", "dakota", "december", "delaware",
		    "delta", "diet", "don", "double", "early", "earth", "east", "echo",
		    "edward", "eight", "eighteen", "eleven", "emma", "enemy", "equal",
		    "failed", "fanta", "fifteen", "fillet", "finch", "fish", "five", "fix",
		    "floor", "florida", "football", "four", "fourteen", "foxtrot", "freddie",
		    "friend", "fruit", "gee", "georgia", "glucose", "golf", "green", "grey",
		    "hamper", "happy", "harry", "hawaii", "helium", "high", "hot", "hotel",
		    "hydrogen", "idaho", "illinois", "india", "indigo", "ink", "iowa",
		    "island", "item", "jersey", "jig", "johnny", "juliet", "july", "jupiter",
		    "kansas", "kentucky", "kilo", "king", "kitten", "lactose", "lake", "lamp",
		    "lemon", "leopard", "lima", "lion", "lithium", "london", "louisiana",
		    "low", "magazine", "magnesium", "maine", "mango", "march", "mars",
		    "maryland", "massachusetts", "may", "mexico", "michigan", "mike",
		    "minnesota", "mirror", "mississippi", "missouri", "mobile", "mockingbird",
		    "monkey", "montana", "moon", "mountain", "muppet", "music", "nebraska",
		    "neptune", "network", "nevada", "nine", "nineteen", "nitrogen", "north",
		    "november", "nuts", "october", "ohio", "oklahoma", "one", "orange",
		    "oranges", "oregon", "oscar", "oven", "oxygen", "papa", "paris", "pasta",
		    "pennsylvania", "pip", "pizza", "pluto", "potato", "princess", "purple",
		    "quebec", "queen", "quiet", "red", "river", "robert", "robin", "romeo",
		    "rugby", "sad", "salami", "saturn", "september", "seven", "seventeen",
		    "shade", "sierra", "single", "sink", "six", "sixteen", "skylark", "snake",
		    "social", "sodium", "solar", "south", "spaghetti", "speaker", "spring",
		    "stairway", "steak", "stream", "summer", "sweet", "table", "tango", "ten",
		    "tennessee", "tennis", "texas", "thirteen", "three", "timing", "triple",
		    "twelve", "twenty", "two", "uncle", "undress", "uniform", "uranus", "utah",
		    "vegan", "venus", "vermont", "victor", "video", "violet", "virginia",
		    "washington", "west", "whiskey", "white", "william", "winner", "winter",
		    "wisconsin", "wolfram", "wyoming", "xray", "yankee", "yellow", "zebra",
		    "zulu"};
	
	byte[] digest;
	int words = 4;
	String[] wordlist = DEFAULT_WORDLIST;

	public HumanHashGenerator(byte[] digest) {
		this.digest = digest;
	}
	
	public HumanHashGenerator words(int words) {
		this.words = words;
		return this;
	}
	
	public HumanHashGenerator wordList(String[] wordlist) {
		
		if(wordlist.length != DEFAULT_WORDLIST.length) {
			throw new IllegalArgumentException("Wordlist MUST have " + DEFAULT_WORDLIST.length + " words exactly");
		}
		this.wordlist = wordlist;
		return this;
	}
	
	private int xor(byte[] bytes) {
		int result = 0;
		for(int i=0;i<bytes.length;i++) {
			result ^= (bytes[i] & 0xFF);
		}
		return result;
	}
	
	public String build() {
		
		int length = digest.length;
		if(words > length) {
			throw new IllegalArgumentException("Fewer input bytes than requested output");
		}
		
		int segment = length / words;
		int remaining = length % segment;

		StringBuilder tmp = new StringBuilder();
		for(int i=0; i<words; i++) {
			if(i>0) {
				tmp.append('-');
			}
			tmp.append(wordlist[xor(Arrays.copyOfRange(digest, i*segment, (i == words-1 ? (i*segment) + segment + remaining : (i*segment) + segment)))]);
		}
		return tmp.toString();
	}
}
