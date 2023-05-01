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

    @RequestMapping(value = "/getroomrssi/{roomid}", method = GET)
    public List<fieldrssi> getRoomRadiomap(@PathVariable("roomid") String roomid){
        return RadioMapService.getsavedRadioMap(roomid);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/getpos/{roomid}", method = GET)
    public List<userposition> getPosition(@PathVariable("roomid") String roomid) {
        //Return user's position in room
        return RadioMapService.trackingPosition(roomid);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/getpostudent/{roomid}", method = GET)
    public List<positionResult> getPositionOfStudent(@PathVariable("roomid") String roomid){
        //Return user's position in room
        return RadioMapService.getUserandPositioninRoom(roomid);
    }

    @RequestMapping(value = "/saverssi", method = POST)
    public String saveRssi(@RequestBody fieldrssi fr){
        fieldrssi frssi = RadioMapService.postRssi(fr);
        return "Success:"+frssi;
    }

    @RequestMapping(value = "/regbeacon", method = POST)
    public String registerBeacon(@RequestBody beaconposition bp){return RadioMapService.beaconRegister(bp);}

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/beaconlogin/{userid}", method = POST)
    public String loginBeacon(@PathVariable("userid") String userid){
        return RadioMapService.beaconLogin(userid);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/beaconlogout/{userid}", method = POST)
    public String logoutBeacon(@PathVariable("userid") String userid){
        return RadioMapService.beaconLogout(userid);
    }

//    @CrossOrigin(origins = "http://10.72.13.78:54678")
    @RequestMapping(value = "/saveBeacons", method = POST)
    public String saveBeacons(@RequestBody scannerResult scannerValue){
        return RadioMapService.saveBeaconFound(scannerValue);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/studentinroom/{roomid}", method = GET)
    public String getStudentInRoom(@PathVariable("roomid") String roomid){
        return RadioMapService.getBeaconOwnerinRoom(roomid).toString();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/record/{roomid}", method = POST)
    public String recordParticipation(@PathVariable("roomid") String roomid, @RequestBody List<userposition> recordList){
        return RadioMapService.participateRecord(roomid, recordList);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/addcourse", method = POST)
    public course saveCourse(@RequestBody course c){
        return RadioMapService.addNewActivity(c);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/enroll", method = POST)
    public String enrollstudent(@RequestBody enrollment e){
        return RadioMapService.addNewStudentToCourse(e);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/drop/{studentid}/{courseid}", method = POST)
    public String studentdrop(@PathVariable("studentid") String userid, @PathVariable("courseid") String courseid){
        return RadioMapService.dropStudentFromCourse(userid, courseid);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/getcourse/{courseid}", method = GET)
    public List<users> getStudentofCourse(@PathVariable("courseid") String courseid){
        return RadioMapService.getStudentinCourse(courseid);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/roominfo/{roomid}", method = GET)
    public classroom getRoomInfo(@PathVariable("roomid") String roomid){
        return RadioMapService.getRoomInfo(roomid);
    }

    @RequestMapping(value = "/roominfo", method = POST)
    public String postRoomInfo(@RequestBody classroom c){
        return RadioMapService.addNewRoom(c);
    }

//    @RequestMapping(value = "/regscanner", method = POST)
//    public String regScanner(@RequestBody scanner s){
//        return RadioMapService.registerScanner(s);
//    }

    @RequestMapping(value = "/testTime/{roomid}", method = GET)
    public course testTime(@PathVariable("roomid") String roomid){
        return RadioMapService.getcurrentCourse(roomid);
    }

    @RequestMapping(value = "/test", method = GET)
    public radiomap testScannerinRoom(@RequestBody radiomap r){
        return r;
    }
}
