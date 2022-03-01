package com.college.main;

import com.college.entity.Course;
import com.college.entity.Student;
import com.college.service.ManagementService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentDatabase {

    public static void main(String args[]) {
        ManagementService managementService = new ManagementService();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome To Student DB Management");

        addStudent(scanner, managementService);

        while (true) {
            System.out.println("Enter Value to Continue");
            System.out.println("1. User \n2. Student");
            int loginType = scanner.nextInt();
            switch (loginType) {
                case 2:
                    while (true) {
                        System.out.println("Enter the Menu ID");
                        System.out.println("1. Balance \n2. Pay for Course \n3. logout");
                        int menuId = scanner.nextInt();
                        if (menuId == 3) {
                            break;
                        }
                        System.out.println("Enter ur Student ID");
                        int id = scanner.nextInt();
                        if( menuId == 1){
                            System.out.println("Your balance : " +managementService.getBalanceByStudentID(id));;
                        } else if ( menuId == 2) {
                            List<Course> courses = managementService.getCourses();
                            courses.forEach( course -> {
                                System.out.println("Course ID: " + course.getId() + " Course Name: " + course.getName());
                            });
                            System.out.println("Enter the Course code to pay or 0 to exit");
                            int courseID = scanner.nextInt();
                            if(courseID == 0) {
                                break;
                            } else {
                                managementService.addCourseForStudent(courseID, id);
                            }
                        }
                    }
                    break;
                case 1:
                    while (true) {
                        System.out.println("Enter Menu ID");
                        System.out.println("1. Student Details \n2. Add Student Details 3. Logout");
                        int menuID = scanner.nextInt();
                        if(menuID == 3) {
                            break;
                        } else if ( menuID == 1) {
                            Map<Long, Student> studentDetails = managementService.getStudentDetails();
                            studentDetails.forEach( (id, student) -> {
                                System.out.println(" Student Name: " + student.getName());
                                System.out.println(" Student ID: " + student.getId());
                                System.out.println(" Student Balance: " + student.getBalance());
                                System.out.println(" Student year: " + student.getYear());
                                System.out.println("Student Courses ID :" + student.getCourses());
                            });
                        } else if ( menuID == 2) {
                            addStudent(scanner, managementService);
                        }
                    }
                    break;
            }
        }
    }

    private static void addStudent(Scanner scanner, ManagementService managementService) {
        System.out.println("Please enter the student Count to add");
        int studentCount = scanner.nextInt();

        for(int i = 0; i < studentCount; i++) {
            System.out.println("Enter Student Name");
            String studentName = scanner.next();
            System.out.println("Enter Student year");
            int year = scanner.nextInt();

            if(managementService.addStudent(new Student(studentName, year))) {
                System.out.println("added...");
            }
        }
    }

}
