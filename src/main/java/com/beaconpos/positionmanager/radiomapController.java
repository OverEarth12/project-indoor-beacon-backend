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

    @RequestMapping(value = "/savepos/{posx}/{posy}", method = POST)
    public String savePosition(@RequestBody radiomap rdm, @PathVariable("posx") Integer posx, @PathVariable("posy") Integer posy) {
        return RadioMapService.postRadioMap(rdm, posx, posy);
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

//    @CrossOrigin(origins = "http://10.72.13.78:54678")
    @RequestMapping(value = "/saveBeacons", method = POST)
    public String saveBeacons(@RequestBody List<radiomap> beaconList){
        return RadioMapService.saveBeaconPosition(beaconList);
    }

    @RequestMapping(value = "/test", method = GET)
    public radiomap testScannerinRoom(@RequestBody radiomap r){
        return r;
    }
}
