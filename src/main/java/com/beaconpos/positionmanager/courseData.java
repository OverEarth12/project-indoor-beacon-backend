package com.beaconpos.positionmanager;

import lombok.Data;

import java.util.List;

@Data
public class courseData {
    private course courseInfo;
    private List<String> checker;
    private List<String> studentsList;

    public courseData() {
    }

    public courseData(course courseInfo, List<String> checker, List<String> studentsList) {
        this.courseInfo = courseInfo;
        this.checker = checker;
        this.studentsList = studentsList;
    }

    public course getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(course courseInfo) {
        this.courseInfo = courseInfo;
    }

    public List<String> getChecker() {
        return checker;
    }

    public void setChecker(List<String> checker) {
        this.checker = checker;
    }

    public List<String> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<String> studentsList) {
        this.studentsList = studentsList;
    }

    @Override
    public String toString() {
        return "courseData{" +
                "courseInfo=" + courseInfo +
                ", checker=" + checker +
                ", studentsList=" + studentsList +
                '}';
    }
}
