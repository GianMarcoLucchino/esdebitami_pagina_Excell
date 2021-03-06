package it.esdebitamiretake.retake_ir.scraping.nlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

class Splitter {

	private final SentenceModel model;
	
	Splitter(Language language) throws IOException {
		Path path=Paths.get("models",String.format("%s-sent.bin",language.name().toLowerCase()));
		
		if(!Files.exists(path))
			path=Paths.get("models","en-sent.bin");
			
		try(InputStream inputStream=getClass().getClassLoader().getResourceAsStream("models/en-sent.bin") ){
			model = new SentenceModel(inputStream);
		} 
	}

	String[] split(String text) {

	    return new SentenceDetectorME(model).sentDetect(text);
	}
}