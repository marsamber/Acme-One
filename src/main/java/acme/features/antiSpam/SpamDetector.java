package acme.features.antiSpam;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.SystemConfiguration;

public class SpamDetector {
	
	@Autowired
	protected SpamDetectorRepository repository;
	
	private final List<String> strongSpamWords;
	private final List<String> weakSpamWords;
	private final Double strongSpamThreshold;
	private final Double weakSpamThreshold;
	
	public SpamDetector(){

		final SystemConfiguration sysConfiguration= this.repository.findTheSystemConfiguration();
		this.strongSpamWords= this.getListOfWords(sysConfiguration.getStrongSpamTerms());
		this.weakSpamWords= this.getListOfWords(sysConfiguration.getWeakSpamTerms());
		this.strongSpamThreshold= sysConfiguration.getStrongSpamThreshold();
		this.weakSpamThreshold= sysConfiguration.getWeakSpamThreshold();
		
	}
	public SpamDetector(final SystemConfiguration sysConfiguration){
		
		this.strongSpamWords= this.getListOfWords(sysConfiguration.getStrongSpamTerms());
		this.weakSpamWords= this.getListOfWords(sysConfiguration.getWeakSpamTerms());
		this.strongSpamThreshold= sysConfiguration.getStrongSpamThreshold();
		this.weakSpamThreshold= sysConfiguration.getWeakSpamThreshold();
		
	}
	
	private List<String> getListOfWords(final String words) {
		final String[] arrayWords=words.split(",");
		return Arrays.asList(arrayWords);
	}
	
	public boolean detectSpam(String text) {
		Double weakSpam; 
		Double strongSpam;
		Double weakWordsNumber=0.;
		Double strongWordsNumber=0.;
		text = text.replaceAll("\\s{2,}", " ").replace("\\n", "").trim();
		final Integer numberOfWords=text.split(" ").length;
		
		if(this.checkWord(text.trim(), this.strongSpamWords)||this.checkWord(text.trim(), this.weakSpamWords)) {
			return true;
		}
		
		final String[] textSplitted= text.split(" ");
		String word1;
		String word2;
		String word1And2;
		for(int i=0; i<(textSplitted.length-1); i++) {
			word1=textSplitted[i].trim();
			word2=textSplitted[i+1].trim();
			word1And2=(word1+word2).replaceAll("\\s+","");
			
			if(this.checkWord(word1, this.strongSpamWords) || this.checkWord(word1And2, this.strongSpamWords)) {
				strongWordsNumber++;
			}else if(this.checkWord(word1, this.weakSpamWords) || this.checkWord(word1And2, this.weakSpamWords)) {
				weakWordsNumber++;
			}
			
			if(i==textSplitted.length-2) {
				if(this.checkWord(word2, this.strongSpamWords))
					strongWordsNumber++;
				else if(this.checkWord(word2, this.weakSpamWords))
					weakWordsNumber++;
			}
		}
		weakSpam= weakWordsNumber/numberOfWords;
		strongSpam= strongWordsNumber/numberOfWords;
		
		
		if(weakSpam > (this.weakSpamThreshold/100))
			return true;
		if(strongSpam > (this.strongSpamThreshold/100))
			return true;
		
		return false;
	}
	
	private boolean checkWord(final String word, final List<String> terms) {
		for(int i=0; i<terms.size(); i++) {
			if(terms.get(i).replaceAll("\\s+","").equalsIgnoreCase(word))
				return true;
		}
		return false;
	}
}
