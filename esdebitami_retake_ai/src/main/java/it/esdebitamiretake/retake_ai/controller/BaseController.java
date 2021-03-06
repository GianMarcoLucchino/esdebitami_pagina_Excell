/**
 * 
 */
package it.esdebitamiretake.retake_ai.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.esdebitamiretake.retake_ai.beans.User;
import it.esdebitamiretake.retake_ai.exceptions.Errors;
import it.esdebitamiretake.retake_ai.exceptions.ServiceException;
import it.esdebitamiretake.retake_ai.response.VasResponse;


@RestController
@EnableAutoConfiguration
@RequestMapping("/base")
public class BaseController extends AController{

	private static Logger LOG = LoggerFactory.getLogger(BaseController.class);
	
	@Value("${ai.anyvalue}")
	private String anyValue;
	
	
	public BaseController() {
		super();
	}	
	
	@PostMapping(value="/demospost")
	@ResponseBody
    public ResponseEntity<VasResponse> demospost(HttpServletRequest request, HttpServletResponse response,@Valid @RequestBody User input) {
		LOG.info("START demospost");
		User userBean = new User();
		userBean.setNome(input.getNome());
		userBean.setCognome(this.anyValue);
		userBean.setId(Math.addExact(12L, input.getId()));
		LOG.info("STOP demospost");
		return buildResponseOK(userBean);
    }
	
	@GetMapping(value="/demos")
	@ResponseBody
    public ResponseEntity<VasResponse> other(HttpServletRequest request, HttpServletResponse response) {
		User userBean = new User();
		userBean.setNome("dix");userBean.setCognome(this.anyValue);
		userBean.setId(Long.valueOf(13L));
//		response.setStatus(HttpStatus.FORBIDDEN.value());
//		VasResponse vasResponse = new VasResponse();
//		vasResponse.setContent(userBean);
//		vasResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
//		vasResponse.setStatusMessage(HttpStatus.FORBIDDEN.getReasonPhrase());
		return buildResponseOK(userBean);
    }
	
	@GetMapping(value="/errore")
	@ResponseBody
    public ResponseEntity<VasResponse> errore(HttpServletRequest request, HttpServletResponse response) {
		if(true) {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "errore request baddddddddddd");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			request.setAttribute("javax.servlet.error.status_code", HttpStatus.FORBIDDEN.value());
//			throw new RuntimeException("Errore di test.");
		}		
		User userBean = new User();
		userBean.setNome("dix");userBean.setCognome(this.anyValue);
		userBean.setId(Long.valueOf(13L));
        return buildResponseOK(userBean);
    }
	
	@GetMapping(value="/errore2")
	@ResponseBody
    public ResponseEntity<VasResponse> errore2(HttpServletRequest request, HttpServletResponse response) {
		if(true) {			
			throw new ServiceException(Errors.NOT_FOUND);
		}		
		User userBean = new User();
		userBean.setNome("dix");userBean.setCognome(this.anyValue);
		userBean.setId(Long.valueOf(13L));
        return buildResponseOK(userBean);
    }
}
