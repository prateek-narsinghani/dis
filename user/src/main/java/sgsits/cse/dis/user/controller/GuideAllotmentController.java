package sgsits.cse.dis.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sgsits.cse.dis.user.constants.RestAPI;
import sgsits.cse.dis.user.exception.ConflictException;
import sgsits.cse.dis.user.jwt.JwtResolver;
import sgsits.cse.dis.user.message.request.AddNewUser;
import sgsits.cse.dis.user.message.request.CreateBatch;
import sgsits.cse.dis.user.message.response.BatchData;
import sgsits.cse.dis.user.message.response.ResponseMessage;
import sgsits.cse.dis.user.service.GuideAllotmentService;

@CrossOrigin(origins = "*")
@Api(value = "Guide Allotment Controller")
@RestController
@RequestMapping(path = "/guideAllotment")
public class GuideAllotmentController {
	
	private JwtResolver jwtResolver = new JwtResolver();
	@Autowired
	private GuideAllotmentService guideServiceImpl;

	@ApiOperation(value = "Get All Batches", response = BatchData.class, httpMethod = "GET", produces = "application/json")
	@GetMapping(value = RestAPI.GET_ALL_BATCHES, produces = "application/json")
	public ResponseEntity<List<BatchData>> getAllBatches(@PathVariable("session") String session,@PathVariable("ugOrPg") String ugOrPg) {
		return new ResponseEntity<List<BatchData>>(guideServiceImpl.getAllBatches(session, ugOrPg),HttpStatus.OK);
	}
	
	@ApiOperation(value = "create batch", response = ResponseMessage.class, httpMethod = "POST", produces = "application/json")
	@PostMapping(value = RestAPI.CREATE_BATCH, produces = "application/json")
	public ResponseEntity<ResponseMessage> createBatch(@RequestBody CreateBatch createBatch) throws ConflictException {
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(guideServiceImpl.createBatch(createBatch)),HttpStatus.OK);
	}
	
	@ApiOperation(value = "update students, guide and co guide", response = ResponseMessage.class, httpMethod = "PUT", produces = "application/json")
	@PutMapping(value = RestAPI.UPDATE_BATCH, produces = "application/json")
	public ResponseEntity<ResponseMessage> updateBatch(@RequestBody BatchData updatedBatch) throws ConflictException {
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(guideServiceImpl.updateBatch(updatedBatch)),HttpStatus.OK);
	}	
}