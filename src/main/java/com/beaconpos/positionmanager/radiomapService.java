package com.beaconpos.positionmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class radiomapService {
    @Autowired
    private radiomapRepository repository;
    @Autowired
    private fieldrssiRepository fieldrssiRepository;
    @Autowired
    private classroomRepository classroomRepository;
    @Autowired
    private beaconpositionRepository beaconPositionRepository;
    private ArrayList<Integer> averageBeacon;

    public List<radiomap> getAllInRoom(String roomid){return repository.findByRoomid(roomid); }

    public List<fieldrssi> getRSSIInRoom(String roomid){return fieldrssiRepository.findByRoomid(roomid); }

    public int isScannerInRoom(String roomid, String scannerid){
        List<classroom> allscannerinroom = classroomRepository.findByRoomid(roomid);
        for (classroom i:allscannerinroom) {
//            System.out.println(i+"\n"+ scannerid);
            if(scannerid.equals(i.getScanner_1())){
                return 1;
            }
            if(scannerid.equals(i.getScanner_2())){
                return 2;
            }
            if(scannerid.equals(i.getScanner_3())){
                return 3;
            }
        }
        return 0;
    }
    public String postRadioMap(radiomap rdm, int posx, int posy){
//        List<radiomap> currentRadioMap = getAllInRoom(rdm.getRoomid());
//        for (radiomap item: currentRadioMap) {
//            if(item.getPositionx() == rdm.getPositionx() && item.getPositiony() == rdm.getPositiony()){
//                rdm.setId(item.getId());
//                break;
//            }
//        }
//        System.out.println(rdm);
//        return repository.save(rdm);
        int scannerSlot = isScannerInRoom(rdm.getRoomid(), rdm.getScannerid());
        if(scannerSlot == 0){
            return "Sorry, This scanner not belong in room you request.";
        }
//        System.out.println(getRSSIInRoom(rdm.getRoomid()));
        List<fieldrssi> allrssinroom = getRSSIInRoom(rdm.getRoomid());
        for (fieldrssi item: allrssinroom) {
            if(item.getPositionx() == posx && item.getPositiony() == posy){
                item.setRssiBySlot(scannerSlot, rdm.getRssi());

                fieldrssiRepository.save(item);
                return "Updated, "+item;
                }
        }
        fieldrssi newfield = new fieldrssi(posx, posy, rdm.getRoomid(), scannerSlot,rdm.getRssi());
        fieldrssiRepository.save(newfield);
        return "Saved, "+newfield;
    }

    public List<userposition> trackingPosition(String roomId){
        List<userposition> allUserPosition = new ArrayList<>();
        List<beaconposition> allBeaconPosition = beaconPositionRepository.findByRoomid(roomId);
        List<fieldrssi> rssiInRoom = getRSSIInRoom(roomId);
        for (beaconposition item: allBeaconPosition) {
            double min = 1000000;
            int posx = -1, posy = -1;
            if(!item.isOnline()){
                continue;
            }
            for (fieldrssi p:rssiInRoom) {
                double score = Math.sqrt(Math.pow(item.getRssi1()-p.getRssi1(),2)+Math.pow(item.getRssi2()-p.getRssi2(),2)+Math.pow(item.getRssi3()-p.getRssi3(),2));
                if(score < min){
                    System.out.println(score);
                    min = score;
                    posx = p.getPositionx();
                    posy = p.getPositiony();
                }
                System.out.println(item.getUuid()+" "+p.getPositionx()+","+p.getPositiony()+": "+score);
            }
            allUserPosition.add(new userposition(item.getUuid(),posx,posy));
        }
        return allUserPosition;

    }

    public String saveBeaconPosition(List<radiomap> beaconList){
//        System.out.println(beaconList.get(0).getRoomid());
        List<beaconposition> allBeaconPosition = beaconPositionRepository.findByRoomid(beaconList.get(0).getRoomid());
        List<beaconposition> newBeaconPosition = new ArrayList<>();
        int slot = isScannerInRoom(beaconList.get(0).getRoomid(), beaconList.get(0).getScannerid());
        if(slot != 0){
            for (radiomap item :beaconList) {

                beaconposition newBeacon = new beaconposition(item.getUuid(),item.getRoomid());
                //already exists beacon
                for (int i = 0; i < allBeaconPosition.size(); i++) {
                    if(item.getUuid().equals(allBeaconPosition.get(i).getUuid())){
                        newBeacon.copyRssi(allBeaconPosition.get(i));
                    }
                }
                newBeacon.setRssiBySlot(isScannerInRoom(item.getRoomid(), item.getScannerid()),item.getRssi());
                allBeaconPosition.removeIf(i -> i.getUuid().equals(item.getUuid()));
                newBeaconPosition.add(newBeacon);
            }
            //set exists beacons
            allBeaconPosition.forEach(i -> i.setRssiBySlot(slot,null));
            newBeaconPosition.addAll(allBeaconPosition);
//        System.out.println(newBeaconPosition);
            String success = "";
            for (beaconposition newbeacon:newBeaconPosition) {
//                System.out.println(newbeacon);
                success += newbeacon.toString()+"\n";
                beaconPositionRepository.save(newbeacon);
            }
            System.out.println(success);
            return success;
        }
        else{
            return "This scanner not belong in this room.";
        }

//        for ( beaconposition beacon:allBeaconPosition) {
//            boolean active = false;
//            for (radiomap item :beaconList) {
//                System.out.println(item.getUuid()+" "+beacon.getUuid());
//                if(item.getUuid().equals(beacon.getUuid())){
//                    beacon.setRssiBySlot(isScannerInRoom(item.getRoomid(),item.getScannerid()),item.getRssi());
//                    active = true;
//                    beaconList.remove(item);
//                    break;
//                }
//            }
//            if(!active){
//                beacon.setRoomid(null);
//            }
//            beaconPositionRepository.save(beacon);
//        }
//        System.out.println("New Beacons: "+beaconList.size());
    }

    public fieldrssi postRssi(fieldrssi fr){

        return fieldrssiRepository.save(fr);
    }
}
