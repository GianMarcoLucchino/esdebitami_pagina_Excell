/**
 * 
 */
package it.esdebitamiretake.retake_ai.response;

import java.util.ArrayList;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Utente
 *
 */
public class Words {

	@Valid
	@NotNull
	@Size(min=1)
	private ArrayList<String> words;
	
	public Words() {
	}

	public ArrayList<String> getWords() {
		if(Objects.isNull(words)) {
			words = new ArrayList<String>(0);
		}
		return words;
	}
	
//	public void setWords(ArrayList<String> words) {
//		this.words = words;
//	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this.words);
	}

}
