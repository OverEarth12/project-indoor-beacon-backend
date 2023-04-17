package com.beaconpos.positionmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class radiomapController {
    @Autowired
    private radiomapService RadioMapService;

    @RequestMapping(value = "/savepos/{posx}/{posy}", method = POST)
    public String savePosition(@RequestBody radiomap rdm, @PathVariable("posx") Integer posx, @PathVariable("posy") Integer posy) {
        return RadioMapService.postRadioMap(rdm, posx, posy);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/getpos/{roomid}", method = GET)
    public List<userposition> getPosition(@PathVariable("roomid") String roomid) {
        return RadioMapService.trackingPosition(roomid);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/getpostudent/{roomid}", method = GET)
    public ArrayList getPositionOfStudent(@PathVariable("roomid") String roomid){
        return RadioMapService.getUserandPositioninRoom(roomid);
    }

    @RequestMapping(value = "/saverssi", method = POST)
    public String saveRssi(@RequestBody fieldrssi fr){
        fieldrssi frssi = RadioMapService.postRssi(fr);
        return "Success:"+frssi;
    }

    @RequestMapping(value = "/beaconlogin/{userid}", method = POST)
    public String loginBeacon(@PathVariable("userid") String userid){
        return RadioMapService.beaconLogin(userid);
    }

//    @CrossOrigin(origins = "http://10.72.13.78:54678")
    @RequestMapping(value = "/saveBeacons", method = POST)
    public String saveBeacons(@RequestBody scannerResult scannerValue){
        return RadioMapService.saveBeaconFound(scannerValue);
    }

    @RequestMapping(value = "/studentinroom/{roomid}", method = GET)
    public String getStudentInRoom(@PathVariable("roomid") String roomid){
        return RadioMapService.getBeaconOwnerinRoom(roomid).toString();
    }

    @RequestMapping(value = "/record/{roomid}", method = POST)
    public String recordParticipation(@PathVariable("roomid") String roomid, @RequestBody List<userposition> recordList){
        return RadioMapService.participateRecord(roomid, recordList);
    }

    @RequestMapping(value = "/addcourse", method = POST)
    public course saveCourse(@RequestBody course c){
        return RadioMapService.addNewActivity(c);
    }

    @RequestMapping(value = "/enroll", method = POST)
    public String enrollstudent(@RequestBody enrollment e){
        return RadioMapService.addNewStudentToCourse(e);
    }

    @RequestMapping(value = "/drop/{studentid}/{courseid}", method = POST)
    public String studentdrop(@PathVariable("studentid") String userid, @PathVariable("courseid") String courseid){
        return RadioMapService.dropStudentFromCourse(userid, courseid);
    }

    @RequestMapping(value = "/getcourse/{courseid}", method = GET)
    public List<enrollment> getStudentofCourse(@PathVariable("courseid") String courseid){
        return RadioMapService.getStudentinCourse(courseid);
    }

    @RequestMapping(value = "/test", method = GET)
    public radiomap testScannerinRoom(@RequestBody radiomap r){
        return r;
    }
}
