package it.esdebitamiretake.retake_ticket.controller;

import java.net.URI;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.axis2.client.Options;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tempuri.XSportService;
import org.tempuri.XSportService.XSpGetApplication;
import org.tempuri.XSportService.XSpIsCurrentSessionAuthenticate;
import org.tempuri.XSportService.XSpIsCurrentSessionAuthenticateResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import it.esdebitamiretake.retake_ticket.collection.Color;
import it.esdebitamiretake.retake_ticket.service.ColorService;

@SwaggerDefinition(tags = { @Tag(name = "Color", description = "Operations pertaining to colors in VVAS") })
@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ColorController {

	private static final Logger logger = LoggerFactory.getLogger(ColorController.class);

	private static final String MESSAGE_401 = "You are not authorized to perform this action";

	@Autowired
	private ColorService colorService;

	@PostMapping(value = "/color", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create a color for a specific user", tags = "colors")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Color created"),
			@ApiResponse(code = 401, message = MESSAGE_401),
			@ApiResponse(code = 404, message = "No user found") })
	@ResponseBody
	public ResponseEntity<URI> postColor(@ApiParam(required = true) @RequestBody @Valid Color color,
			@RequestHeader("Authorization") String accessToken) throws RemoteException, ResourceNotFoundException {

		String applicationName = loadApplicationName(accessToken);
		
		color.setAppName(applicationName);

		try {

			return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
					.buildAndExpand(colorService.saveColor(color).getId()).toUri()).build();

		} catch (DuplicateKeyException e1) {

			throw new ResourceAlreadyExistsException("Color", color.toString());
			
		}
	}
	
	/*@PutMapping(value = "/color", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update a color for a specific user", tags = "colors")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Color updated"),
			@ApiResponse(code = 401, message = MESSAGE_401),
			@ApiResponse(code = 404, message = "No user found") })
	@ResponseBody
	public Color updateColor(@ApiParam(required = true) @RequestParam String colorNameNew, @ApiParam(required = true) @RequestParam Integer colorCodeNew, 
			@ApiParam(required = true) @RequestParam String id,
			@RequestHeader("Authorization") String accessToken) throws RemoteException, ResourceNotFoundException {
		
		Color color = colorService.findByapplicationAndCode(loadApplicationName(accessToken), colorCode);
		

		try {

			return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
					.buildAndExpand(colorService.saveColor(color).getId()).toUri()).build();

		} catch (DuplicateKeyException e1) {

			throw new ResourceAlreadyExistsException("Color", color.toString());
		}
	}*/

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/colors", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get the list of colors", tags = "colors")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The colors have been successfully retrieved"),
			@ApiResponse(code = 401, message = MESSAGE_401),
			@ApiResponse(code = 404, message = "No color found") })
	@ResponseBody
	public JSONObject getColors(@RequestHeader("Authorization") String accessToken) throws ResourceNotFoundException, RemoteException {
		
		JSONObject response = new JSONObject();

		List<Color> colors = colorService.findByapplication(loadApplicationName(accessToken));
		
		List<Integer> codes = new ArrayList<Integer>();
		List<String> names = new ArrayList<String>();

		if (!colors.isEmpty()) {
			for (Color c : colors) {
				codes.add(c.getColorCode());
				names.add(c.getColorName());
			}
			response.put("namesColors", names);
			response.put("codesColors",codes);
		} else
			throw new ResourceNotFoundException("Colors");

		return response;
	}
	
	@GetMapping(value = "/color", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get the color", tags = "colors")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The color has been successfully retrieved"),
			@ApiResponse(code = 401, message = MESSAGE_401),
			@ApiResponse(code = 404, message = "No color found") })
	@ResponseBody
	public Color getColors(@RequestHeader("Authorization") String accessToken, @RequestParam(required=true) Integer colorCode) throws ResourceNotFoundException, RemoteException {
		
		Color color = colorService.findByapplicationAndCode(loadApplicationName(accessToken), colorCode);
		
		if (color == null) 
			throw new ResourceNotFoundException("Color", colorCode.toString());

		return color;
	}
	
	@DeleteMapping("/color/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete a color", tags = "colors")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Color has been successfully deleted"),
			@ApiResponse(code = 404, message = "Color not found") })
	public void deleteColor(@ApiParam(value = "The id of the color", required = true) @PathVariable String id)
			throws ResourceNotFoundException {

		logger.info("DELETE color [?]", id);

		Color color = colorService.findById(id);
		
		if(color==null)
			throw new ResourceNotFoundException("Color",id);
		
		colorService.delete(color.getId());
	}



	private String loadApplicationName(String accessToken) throws RemoteException {

		return loadXSportService(accessToken).xSpGetApplication(new XSpGetApplication()).getXSpGetApplicationResult();
	}

	/*private void checkUser(String userName, String accessToken) throws RemoteException, ResourceNotFoundException {

		XSpGetUser xSpGetUser = new XSpGetUser();
		xSpGetUser.setUser(userName);

		if (loadXSportService(accessToken).xSpGetUser(xSpGetUser).getXSpGetUserResult() == null)
			throw new ResourceNotFoundException("user", userName);
	}*/

	private XSportService loadXSportService(String authToken) throws RemoteException {

		XSportService xSportService = new XSportService();
		Options options = xSportService._getServiceClient().getOptions();
		options.setManageSession(true);
		options.setProperty(org.apache.axis2.transport.http.HTTPConstants.COOKIE_STRING, authToken);

		XSpIsCurrentSessionAuthenticateResponse xSpIsCurrentSessionAuthenticateResponse = xSportService
				.xSpIsCurrentSessionAuthenticate(new XSpIsCurrentSessionAuthenticate());

		if(!xSpIsCurrentSessionAuthenticateResponse.getXSpIsCurrentSessionAuthenticateResult())
			throw new UnauthorizedException();
		
		return xSportService;
	}
}
