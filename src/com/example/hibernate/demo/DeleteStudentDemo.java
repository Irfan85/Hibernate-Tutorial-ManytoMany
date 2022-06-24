package com.example.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.demo.entity.Course;
import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import com.example.hibernate.demo.entity.Review;
import com.example.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		// In Hibernate, data manipulation is done by "Session" objects which are generally short lived and one time.
		// In order create sessions, we need a "SessionFactory" object. This is a heavy weight object and will typically be
		// created only once. After that we can get sessions from this factory.
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.addAnnotatedClass(Course.class)
											.addAnnotatedClass(Review.class)
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();
	
		Session mySession = sessionFactory.getCurrentSession();
		
		try {
			// Start the transaction
			mySession.beginTransaction();

			// Get Kuddus from database
			int studentId = 2;
			Student kuddus = mySession.get(Student.class, studentId);

			System.out.println("Loaded Student: " + kuddus);
			System.out.println("Courses: " + kuddus.getCourses());

			// Delete student
			System.out.println("Deleting student: " + kuddus);
			
			mySession.delete(kuddus);
			
			// Commit transaction
			mySession.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			mySession.close();
			
			sessionFactory.close();
		}
	}

}
