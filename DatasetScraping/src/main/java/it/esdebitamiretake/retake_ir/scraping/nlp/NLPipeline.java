package it.esdebitamiretake.retake_ir.scraping.nlp;

import java.io.IOException;
import opennlp.tools.stemmer.snowball.SnowballStemmer;
import opennlp.tools.stemmer.snowball.SnowballStemmer.ALGORITHM;
import opennlp.tools.tokenize.SimpleTokenizer;

public class NLPipeline {

	private final Language language;
	private final LangDetector langDetector;
	private final Splitter splitter;
	private final SnowballStemmer stemmer;
	private final SimpleTokenizer tokenizer;
	private final StopWords stopWords;
	private final POSTagger posTagger;

	NLPipeline(Language lang) throws IOException {

		this.langDetector = new LangDetector();
		this.splitter = new Splitter(lang);
		this.tokenizer = SimpleTokenizer.INSTANCE;
		this.stopWords = new StopWords(lang);
		this.posTagger = new POSTagger(lang);

		ALGORITHM algho;

		switch (lang) {

		case IT:
			algho = ALGORITHM.ITALIAN;
			break;

		default:
			algho = ALGORITHM.ENGLISH;
			break;
		}

		this.stemmer = new SnowballStemmer(algho);
		this.language=lang;
	}
	
	public Language detectLanguage(String text) {

		Language language;

		switch (langDetector.detectLanguage(text)) {

		case "ita":
			language = Language.IT;
			break;

		default:
			language = Language.EN;
		}

		return language;
	}

	public String[] detectSentences(String text) throws IOException {

		return splitter.split(text);
	}

	public String[] tokenize(String text) {

		return tokenizer.tokenize(text);
	}

	public CharSequence[] stem(String... words) {

		CharSequence[] stems = new String[words.length];

		for (int i = 0; i < words.length; i++)
			stems[i] = stemmer.stem(words[i]);

		return stems;
	}

	public String[] stop(String... words) {

		return stopWords.stop(words);
	}

	public String[] pos(String... words) {

		return posTagger.pos(words);
	}

	public Language getLanguage() {
		
		return language;
	}
}