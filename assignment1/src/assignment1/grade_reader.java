package assignment1;
/*
 * Class: CMSC203 CRN 21352
 * Instructor: Khandan Monshi
 * Description: A simple grade calculator
 * Due: 09/15/2026
 * Platform/compiler: Eclipse w/ Java SDK
 * I pledge that I have completed the programming assignment 
  independently. I have not copied the code from a student or any source. I have not given my code to any student.
 * Print your Name here: RAGHAV GHORAKAVI
*/


import java.io.*;
import java.util.Scanner;

public class grade_reader {

	public static void main(String[] args) throws FileNotFoundException {
		File input = new File("grades_input.txt");
		File conf = new File("gradeconfig.txt");
		
		double projects = 0.4;
		double quizzes = 0.3;
		double exams = 0.3;
		String course = "CMSC 203: Computer Science I";
		
		if(conf.exists()) {
			System.out.println("Loading gradeconfig.txt...");
			Scanner config = new Scanner(conf);
			course = config.nextLine();
			config.nextLine();
			
			
			for(int i = 0; i < 3; i++) {
				String cat = config.next();
				if(cat.toUpperCase().equals("PROJECTS")) {
					projects = config.nextDouble()/100;
				}else if(cat.toUpperCase().equals("QUIZZES")) {
					quizzes = config.nextDouble()/100;
				}else if(cat.toUpperCase().equals("EXAMS")) {
					exams = config.nextDouble()/100;
				}else {
					System.out.println("Malformed gradeconfig.txt. Reverting to defaults.");
					projects = 0.4;
					quizzes = 0.3;
					exams = 0.3;
					config.close();
					break;
				}
			}
			if((projects + quizzes + exams) != 1.0) {
				System.out.println("Weights do not add to 100. Reverting to defaults.");
				projects = 0.4;
				quizzes = 0.3;
				exams = 0.3;
				config.close();
			}else {
				System.out.println("Config loaded succesfully.");
				config.close();
			}
		}else {
			System.out.println("Missing gradeconfig.txt. Using defaults. File should be stored in " + conf.getAbsolutePath());
			projects = 0.4;
			quizzes = 0.3;
			exams = 0.3;
			course = "CMSC 203: Computer Science I";
		}
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enable +/- grading? (Y/N)");
		
		String sel = scanner.nextLine();
		
		while(!sel.toUpperCase().equals("Y") && !sel.toUpperCase().equals("N")) {
			System.out.println("Use N/n for no and Y/y for yes. Please do not use anything else.");
			sel = scanner.nextLine();
		}
		
		boolean pm;
		
		if (sel.toUpperCase().equals("N")) {
			pm = false;
		}else {
			pm = true;
		}
		
		scanner.close();
		
		double grand_sum = 0;
		
		if(input.exists()) {
			Scanner grades = new Scanner(input);
			String first = grades.nextLine();
			String last = grades.nextLine();
			
			System.out.println("Scanning grades_input.txt...");
			
			System.out.println("Student: " + first + " " + last);
			System.out.println("Course: " + course);
			
			
			
			for(int i = 0; i < 3; i++) {
				double sum = 0;
				String cat = grades.nextLine();
				int amt = grades.nextInt();
				for(int j = 0; j < amt; j++) {
					sum += grades.nextDouble();
				}
				
				double avg = sum/amt;
				
				if(cat.toUpperCase().equals("PROJECTS")) {
					System.out.println("Projects: " + avg);
					grand_sum += avg*projects;
				}else if(cat.toUpperCase().equals("QUIZZES")) {
					System.out.println("Quizzes: " + avg);
					grand_sum += avg*quizzes;
				}else if(cat.toUpperCase().equals("EXAMS")) {
					System.out.println("Exams: " + avg);
					grand_sum += avg*exams;
				}else {
					//Naming convention is invalid
					System.out.println("Malformed grades_input.txt. Try again later.");
					System.exit(0);
				}
				
				grades.nextLine();
			}
			grades.close();
		}else {
				System.out.println("Missing grades_input.txt. Make sure the file is stored in " + input.getAbsolutePath());
				System.exit(0);
			}
			
			double avg = grand_sum;
			System.out.println("Overall average: " + avg);
			
			if(avg >= 97 && pm) {
				System.out.println("Final letter grade: A+"); 
			}else if((avg >= 93 && pm) || (avg >= 90 && !pm)) {
				System.out.println("Final letter grade: A"); 
			}else if(avg >= 90 && pm) {
				System.out.println("Final letter grade: A-"); 
			}else if(avg >= 87 && pm) {
				System.out.println("Final letter grade: B+"); 
			}else if((avg >= 83 && pm) || (avg >= 80 && !pm)) {
				System.out.println("Final letter grade: B");
			}else if(avg >= 80 && pm) {
				System.out.println("Final letter grade: B-"); 
			}else if(avg >= 77 && pm) {
				System.out.println("Final letter grade: C+"); 
			}else if((avg >= 73 && pm) || (avg >= 70 && !pm)) {
				System.out.println("Final letter grade: C");
			}else if(avg >= 70 && pm) {
				System.out.println("Final letter grade: C-"); 
			}else if (avg >= 67 && pm) {
				System.out.println("Final letter grade: D+");
			}else if((avg >= 63 && pm) || (avg >= 60 && !pm)) {
				System.out.println("Final letter grade: D");
			}else if (avg >= 60 && pm) {
				System.out.println("Final letter grade: D-");
			}else {
				System.out.println("Final letter grade: F");
			}
			
		System.out.println("Programmed by: Raghav Ghorakavi");
	}	
}

//EOF - By Raghav Ghorakavi

