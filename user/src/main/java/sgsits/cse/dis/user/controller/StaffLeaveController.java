package sgsits.cse.dis.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import sgsits.cse.dis.user.constants.RestAPI;
import sgsits.cse.dis.user.jwt.JwtResolver;
import sgsits.cse.dis.user.message.request.ApplyStaffLeaveForm;
import sgsits.cse.dis.user.message.request.StaffLeaveSettingsForm;
import sgsits.cse.dis.user.message.response.StaffLeaveResponse;
import sgsits.cse.dis.user.model.StaffLeaveSettings;
import sgsits.cse.dis.user.repo.StaffLeaveSettingsRepository;
import sgsits.cse.dis.user.serviceImpl.StaffLeaveServiceImpl;

@CrossOrigin(origins = "*")
@RestController
// @RequestMapping(path = "/leave")
public class StaffLeaveController {

    @Autowired
    private StaffLeaveServiceImpl staffLeaveServiceImpl;

    @Autowired
    private StaffLeaveSettingsRepository staffLeaveSettingsRepository; 

    JwtResolver jwtResolver =new JwtResolver();


    @ApiOperation(value="apply for leave",response=StaffLeaveResponse.class,httpMethod = "POST",produces = "application/json")
    @PostMapping(path = RestAPI.APPLY_LEAVE,produces = "application/json")
    public ResponseEntity<StaffLeaveResponse> applyLeave(@RequestBody ApplyStaffLeaveForm applyStaffLeaveForm,HttpServletRequest request){
                
        Long leaveId;
        String token=request.getHeader("Authorization");
        applyStaffLeaveForm.setAppliedBy(jwtResolver.getUserNameFromJwtToken(token));
        applyStaffLeaveForm.setUserId(jwtResolver.getIdFromJwtToken(token));
        leaveId = staffLeaveServiceImpl.applyLeave(applyStaffLeaveForm);
        return new ResponseEntity<StaffLeaveResponse>(new StaffLeaveResponse(" Leave applied successfully.",leaveId),HttpStatus.OK) ;
    }


    @ApiOperation(value = "get staff leave settings", response = StaffLeaveSettings.class,httpMethod = "GET",produces = "application/json")
    @GetMapping(path=RestAPI.GET_LEAVE_SETTINGS)
    public ResponseEntity<StaffLeaveSettings> getSettings()
    {
        return new ResponseEntity<StaffLeaveSettings>(staffLeaveServiceImpl.getSettings(),HttpStatus.OK);
    }


    @ApiOperation(value = "update staff leave settings",httpMethod = "PUT",produces = "text/plain")
    @PutMapping(path=RestAPI.UPDATE_LEAVE_SETTINGS,produces = "text/plain")
    public ResponseEntity<String> updateSettings(@RequestBody StaffLeaveSettingsForm staffLeaveSettingsForm)
    {
        long leaves;
        leaves=staffLeaveServiceImpl.updateSettings(staffLeaveSettingsForm);
        return new ResponseEntity<String>("Settings Updated.",HttpStatus.OK); 
    }


    @GetMapping(path="/getLeave")
    public StaffLeaveSettings getLeave(HttpServletRequest request)
    {
        //getting latest record

        StaffLeaveSettings top =  staffLeaveSettingsRepository.findTopByOrderByIdDesc();
        return top;
        
        // String token=request.getHeader("Authorization");
        // return jwtResolver.getUserTypeFromJwtToken(token);
        // return staffLeaveLeftRepository.findByUserIdAndYear(jwtResolver.getIdFromJwtToken(token), 2020);
        // return staffLeaveRepository.findByAppliedBy("uthakar");
    }
}

// { 
// 	"details": "Optional leave",
// 	"fromDate": "14-06-1999", 
// 	"halfdayFullday": "full", 
// 	"remarks": "remark", 
//     "status": "pending", 
//     "subject": "sub", 
//     "toDate": "30-06-1999", 
//     "typeOfLeave": "EL"
	
// }

// eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJhM2NmOTRlNC0yMGIxLTExZWEtYmJkOS1hY2QxYjhjOTMxZjciLCJzdWIiOiJ1dGhha2FyIiwiYXVkIjoiaGVhZCIsImlhdCI6MTU4OTUzMTc4MSwiZXhwIjoxNTg5NjE4MTgxfQ.8b7rB6BYUzqBADf3I7JNs9J-Jc5bWrwm2UG91Iz-kDvvtH8fihPBR5dfADxw8sEN0mvXSRowtFUcC6LcnfEbww