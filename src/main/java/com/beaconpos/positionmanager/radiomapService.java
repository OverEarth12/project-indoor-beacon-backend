package com.beaconpos.positionmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
    @Autowired
    private participationRepository participationrepository;
    @Autowired
    private recordRepository recordrepository;
    @Autowired
    private courseRepository courserepository;
    @Autowired
    private enrollmentRepository enrollmentrepository;
    @Autowired
    private beaconownerRepository beaconownerrepository;
    @Autowired
    private usersRepository usersrepository;
//    @Autowired
//    private scannerRepository scannerrepository;
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

    public List<fieldrssi> getsavedRadioMap(String roomid){
        return fieldrssiRepository.findByRoomid(roomid);
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

    public String beaconRegister(beaconposition newBeacon){
        System.out.println(newBeacon);
        if(beaconPositionRepository.findByUuid(newBeacon.getUuid()).size() > 0){
            return "Already Register";
        }
        beaconPositionRepository.save(newBeacon);
        return "Success";
    }

    public String saveBeaconFound(scannerResult scannerValue){
//        return beaconPositionRepository.findByRoomidandids(scannerValue.getRoomid(), scannerValue.getBeaconList().keySet().toArray(new String[0])).toString();
        String roomId = scannerValue.getRoomid();
        HashMap<String, Integer> foundBeaconPosition = scannerValue.getBeaconList();
        List<beaconposition> currentBeaconPosition = beaconPositionRepository.findByRoomidandids(roomId,scannerValue.getBeaconList().keySet().toArray(new String[0]));
        int slot = isScannerInRoom(roomId, scannerValue.getScannerid());
        if(slot == 0){
            return "This scanner not belong in this room.";
        }
        for (beaconposition beacon : currentBeaconPosition){
            String beaconId = beacon.getUuid();
            if(foundBeaconPosition.containsKey(beaconId)){
                beacon.setRssiBySlot(slot, foundBeaconPosition.get(beaconId));
                foundBeaconPosition.remove(beaconId);
            }else{
                beacon.setRssiBySlot(slot, null);
            }

            if(!beacon.isOnline()){
                beacon.setRoomid(null);
            }else{
                beacon.setRoomid(roomId);
            }
        }
        beaconPositionRepository.saveAll(currentBeaconPosition);
        return currentBeaconPosition.toString();
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

    public List<beaconowner> getBeaconOwnerinRoom(String roomid){
        return beaconownerrepository.findbyRoomId("DEMO");
    }

    public List<users> getUserinRoom(String roomid){
        return usersrepository.finduserinroom(roomid);
    }

    public List<positionResult> getUserandPositioninRoom(String roomid){
        List<userposition> participatepos = trackingPosition(roomid);
        List<beaconowner> beaconowners = getBeaconOwnerinRoom(roomid);
        System.out.println(beaconowners);
        List<users> usersinroom = getUserinRoom(roomid);
        List<positionResult> result = new ArrayList<>();
        for (userposition i:participatepos) {
            List<beaconowner> owner = beaconowners.stream().filter(c -> c.getUuid().equals(i.getUuid()) ).toList();
//            for(beaconowner o: beaconowners){
//                if(o.getUuid().equals(i.getUuid())){
//                    owner = o;
//
//                    break;
//                }
//            }
            if(owner.size() > 0){
                List<users> ownerdata = usersinroom.stream().filter(d -> d.getUser_id().equals(owner.get(0).getUser_id())).toList();
                if(ownerdata.size() > 0){
                    result.add(new positionResult(ownerdata.get(0).getUser_id(),ownerdata.get(0).getName(),ownerdata.get(0).getRole(),i.getUuid(),i.getPositionX(),i.getPositionY()));
                }
//                for(users u: usersinroom){
//                    if(owner.getUser_id().equals(u.getUser_id())){
//                        System.out.println(u);
//                        result.add(new positionResult(u, i));
//                    }
//                }
            }

//            result.add(new positionResult(usersinroom.stream().filter(d -> d.getUser_id().equals(owner.get(0).getUser_id())).toList().get(0),i));
//            for(beaconowner o:beaconowners){
//
//            }
//            for (users u:usersinroom) {
//                if(u.getUser_id() == owner)
//            }
        }
        System.out.println(result);
        return result;
    }

    public String beaconLogin(String userid){
        List<beaconowner> userOwnedBeacon = beaconownerrepository.findByUseridAndReturntimestampIsNull(userid);
        if(userOwnedBeacon.size() > 0){
            return userid+" already login with beacon:"+userOwnedBeacon.get(0).getUuid();
        }
        List<beaconposition> notownedBeacons = beaconPositionRepository.findNotOwnedBeacons();
        int ran = (int) (Math.random()*notownedBeacons.size());
//        System.out.println(new beaconowner(userid, notownedBeacons.get(ran).getUuid()).getLogid());
        beaconownerrepository.save(new beaconowner(userid, notownedBeacons.get(ran).getUuid()));
        return userid +" get beacon:"+notownedBeacons.get(ran).getUuid();
    }
    public String beaconLogout(String userid){
        List<beaconowner> userOwnedBeacon = beaconownerrepository.findByUseridAndReturntimestampIsNull(userid);
        userOwnedBeacon.get(0).returnBeacon();
        beaconownerrepository.save(userOwnedBeacon.get(0));
        return userOwnedBeacon.get(0).getUser_id()+"has returned beacon "+userOwnedBeacon.get(0).getUuid()+" at "+userOwnedBeacon.get(0).getReturntimestamp();
    }

    public String participateRecord(String roomid, List<userposition> recordList){
        recordclass newRecord = new recordclass(roomid);
        recordrepository.save(newRecord);
        List<participation> newRecordList = new ArrayList<>();
        for (userposition item:recordList) {
            participation newrecord = new participation(roomid, item.getUuid(), item.getPositionX(), item.getPositionY(), newRecord.getRecordid());
            newRecordList.add(newrecord);
        }
        participationrepository.saveAll(newRecordList);
        return "success";
    }

    public String addNewRoom(classroom newC){
        classroomRepository.save(newC);
        return "Success";
    }

    public classroom getRoomInfo(String roomid){
        List<classroom> reqClass = classroomRepository.findByRoomid(roomid);
        if(reqClass.size() == 0){
            return new classroom();
        }
        return reqClass.get(0);
    }

    public course addNewActivity(course nc){
        if(courserepository.getSameCourse(nc.getCourseId(),nc.getRoomid(),nc.getRegular_weekday(),nc.getRegular_starttime(),nc.getRegular_endtime()).size() > 0){
            System.out.println("Duplicate");
            return nc;
        }
        courserepository.save(nc);
        return nc;
    }

    public String addNewStudentToCourse(enrollment e){
        if(enrollmentrepository.findByuseridAndCourseidAndEnddate(e.getUser_id(), e.getCourse_id(),null).size() > 0){
            return "Already in course";
        }
        enrollmentrepository.save(e);
        return e.toString();
    }

    public String dropStudentFromCourse(String userid, String courseId){
         List<enrollment> enrollOfStudent = enrollmentrepository.findByuseridAndCourseidAndEnddate(userid, courseId,null);
         if(enrollOfStudent.size() > 0){
             enrollOfStudent.get(0).setEnd_date(LocalDateTime.now());
             enrollmentrepository.save(enrollOfStudent.get(0));
//        System.out.println(enrollOfStudent.get(0).toString());
             return "success";
         }else{
             return "not found you in this course";
         }

    }

        public List<users> getStudentinCourse(String courseid){
        List<users> studentincourse = usersrepository.finduserincourse(courseid);
        return studentincourse;
    }

//    public List<enrollment> getStudentinCourse(String courseid){
//        List<enrollment> studentincourse = enrollmentrepository.findenrollmentOfStudent(courseid);
//        return studentincourse;
//    }

//    public String registerScanner(scanner s){
//        List<scanner> existsScanner = scannerrepository.findByScannerid(s.getScannerid());
//        if(existsScanner.size() == 0){
//            return "This id already exists with another device.";
//        }
//        scannerrepository.save(s);
//        return "Success, Please Login with id you registered.";
//    }

    public course getcurrentCourse(String roomid){
        LocalDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Bangkok")).toLocalDateTime();
        System.out.println(zonedDateTime.getDayOfWeek().toString());
        List<course> coursesInRoom = courserepository.getCurrentCourse(roomid, zonedDateTime.getDayOfWeek().toString(),zonedDateTime.toLocalDate(),zonedDateTime.toLocalTime());
        if(coursesInRoom.size() > 1){
            throw new RuntimeException("Something went wrong");
        }else if(coursesInRoom.size() == 0){
            return null;
        }

        return coursesInRoom.get(0);
    }

    public LocalDateTime testTime(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Bangkok"));
        return zonedDateTime.toLocalDateTime();
    }

    public fieldrssi postRssi(fieldrssi fr){

        return fieldrssiRepository.save(fr);
    }
}
