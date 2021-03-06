/**
 * 
 */
package it.esdebitamiretake.retake_ai.manager.handler;

import java.util.ArrayList;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.esdebitamiretake.retake_ai.manager.IManager.IManagerResponseHandler;
import it.esdebitamiretake.retake_ai.manager.listener.IOnResultListener;
import opennlp.tools.stemmer.snowball.SnowballStemmer;

/**
 * @author Utente
 *
 */
public class HandleStemming implements IManagerResponseHandler<ArrayList<String>> {

	private static Logger LOG = LoggerFactory.getLogger(HandleStemming.class);
	private IOnResultListener<ArrayList<String>> resultListener;
	
	
	public HandleStemming() {
	}
	
	
	public HandleStemming(IOnResultListener<ArrayList<String>> resultListener) {
		this.resultListener = resultListener;
	}

	
	@Override
	public void handleBusiness(ArrayList<String> inputFromManager) {
		final ArrayList<String> resultToManage = new ArrayList<String>(0);
//	    STEMMING
	    final SnowballStemmer ps = new SnowballStemmer(SnowballStemmer.ALGORITHM.ITALIAN,5);
	    inputFromManager.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				CharSequence charSequence = ps.stem(t);
				LOG.info("STEMMING#=>Input["+t+"] Output["+charSequence.toString()+"]");
				resultToManage.add(charSequence.toString());
			}
		});
	    this.resultListener.onSuccess(resultToManage);	
	}
}
