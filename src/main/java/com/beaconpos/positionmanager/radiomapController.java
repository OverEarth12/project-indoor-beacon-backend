package com.beaconpos.positionmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class radiomapController {
    @Autowired
    private radiomapService RadioMapService;

    @RequestMapping(value = "/savepos", method = POST)
    public String savePosition(@RequestBody radiomap rdm) {
        return "Success: "+RadioMapService.postRadioMap(rdm);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/getpos/{roomid}", method = GET)
    public List<userposition> getPosition(@PathVariable("roomid") String roomid) {
        return RadioMapService.trackingPosition(roomid);
    }

    @RequestMapping(value = "/saverssi", method = POST)
    public String saveRssi(@RequestBody fieldrssi fr){
        fieldrssi frssi = RadioMapService.postRssi(fr);
        return "Success:"+frssi;
    }

    @RequestMapping(value = "/saveBeacons", method = POST)
    public String saveBeacons(@RequestBody List<radiomap> beaconList){
        RadioMapService.saveBeaconPosition(beaconList);
        return "Success";
    }

    @RequestMapping(value = "/testscanner", method = GET)
    public String testScannerinRoom(@RequestBody testscanners ts){
        return "Success:"+RadioMapService.isScannerInRoom(ts.getRoomid(), ts.getScannerid());
    }
}
