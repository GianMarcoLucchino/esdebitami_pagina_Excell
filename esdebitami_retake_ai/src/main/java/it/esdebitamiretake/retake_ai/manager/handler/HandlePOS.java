/**
 * 
 */
package it.esdebitamiretake.retake_ai.manager.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.esdebitamiretake.retake_ai.exceptions.Errors;
import it.esdebitamiretake.retake_ai.exceptions.ServiceException;
import it.esdebitamiretake.retake_ai.manager.IManager.IManagerResponseHandler;
import it.esdebitamiretake.retake_ai.manager.listener.IOnResultListener;
import it.esdebitamiretake.retake_ai.response.WordPOSTag;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

/**
 * @author Utente
 *
 */
public class HandlePOS implements IManagerResponseHandler<ArrayList<String>> {
	
	
	private static Logger LOG = LoggerFactory.getLogger(HandlePOS.class);
	private File posmaxentBinFile;
	private IOnResultListener<ArrayList<WordPOSTag>> resultListener;

	
	
	public HandlePOS() {
	}
	
	public HandlePOS(IOnResultListener<ArrayList<WordPOSTag>> resultListener) {
		this.resultListener = resultListener;
	}

	public void setPosmaxentBinFile(File posmaxentBinFile) {
		this.posmaxentBinFile = posmaxentBinFile;
	}

	
	
	@Override
	public void handleBusiness(ArrayList<String> inputFromManager) {
		ArrayList<WordPOSTag> resultToManage = new ArrayList<WordPOSTag>(0);
		FileInputStream posmaxentBinStream = null;
		try {
			posmaxentBinStream = new FileInputStream(this.posmaxentBinFile);
			POSModel posModel = new POSModel(posmaxentBinStream);
		    POSTaggerME posTagger = new POSTaggerME(posModel);
		    String tags[] = posTagger.tag(inputFromManager.toArray(new String[0]));		
		    for(int i=0;i<tags.length;i++) {
		    	LOG.info("tag#=>"+tags[i]+";word#=>"+inputFromManager.get(i));
		    	resultToManage.add(new WordPOSTag(inputFromManager.get(i),tags[i]));
		    }
		    this.resultListener.onSuccess(resultToManage);	    
		} catch (IOException e) {
			this.resultListener.onFailure(new ServiceException(Errors.NOT_MANAGED));
		}finally {
			this.closeFiles(posmaxentBinStream);
		}		
	}
	
	
	
	private void closeFiles(InputStream ...streams) {
		for(InputStream currentStream : streams) {
			if(!Objects.isNull(currentStream)) {
				try {
					currentStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
