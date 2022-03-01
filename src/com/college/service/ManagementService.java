package com.college.service;

import com.college.entity.Course;
import com.college.entity.Student;

import java.util.*;

public class ManagementService {

    Map<Long, Student> students = new HashMap<>();

    ArrayList<Course> courses = new ArrayList<>();

    public ManagementService() {
        courses.add(new Course("History", 101));
        courses.add(new Course("Mathematics", 102));
        courses.add(new Course("English", 103));
        courses.add(new Course("Chemistry", 104));
        courses.add(new Course("Computer Science", 105));
    }

    private final int UNIQUE_ID_SIZE = 5;

    public boolean addStudent(Student student){
        try {

            student.setId(createUniqueID(student.getYear(), students.size()));
            students.put(student.getId(), student);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    private int createUniqueID(long year, int size){
        int yearDigits = String.valueOf(year).length();
        int listSizeDigits = String.valueOf(size).length();

        int digitsToCreate = UNIQUE_ID_SIZE - (yearDigits+listSizeDigits);

        int id = (int) year;

        for(int i = 0; i < digitsToCreate; i++) {
            id = Integer.parseInt(id+""+0);
        }

        id = Integer.parseInt(id+""+size);
        System.out.println("id = " + id);
        return id;

    }

    public List<Course> getCourses(){
        return courses;
    }

    public int getBalanceByStudentID(long studentID){
        int year = Integer.parseInt(String.valueOf(studentID).substring(0,1));
        Student studentDetail = students.get(studentID);
        if(studentDetail == null) {
            System.out.println("No Student found in this ID");
            return 0;
        }
        return studentDetail.getBalance();
    }

    public boolean addCourseForStudent(int courseID, long studentID) {
        try {
            Optional<Course> courseDetail = courses.stream().filter( course -> course.getId() == courseID).findFirst();
            if(!courseDetail.isPresent()) {
                System.out.println("No Course find for the ID");
                return false;
            }
            int year = Integer.parseInt(String.valueOf(studentID).substring(0,1));
            Student studentDetail = students.get(studentID);
            if(studentDetail == null) {
                System.out.println("No Student found in this ID");
                return false;
            }
            if(studentDetail.getBalance() >= courseDetail.get().getPrice()) {
                List<Long> subsCourses = studentDetail.getCourses();
                subsCourses.add(courseDetail.get().getId());
                studentDetail.setCourses(subsCourses);
                studentDetail.setBalance(studentDetail.getBalance()-courseDetail.get().getPrice());
                students.put(studentID, studentDetail);

                System.out.println("Course successfully purchased");
                return true;
            } else {
                System.out.println("Insufficient Balance");
                return false;
            }
        } catch (Exception exception) {
            System.out.println("Internal Error");
            return false;
        }
    }

    public Map<Long, Student> getStudentDetails(){
        return students;
    }
}
