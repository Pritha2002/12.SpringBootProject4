package com.example.demo.runner;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.controller.MovieController;
import com.example.demo.entity.Movies;

@Component
public class MoviesRunner implements ApplicationRunner {

	@Autowired
	private MovieController controller;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Scanner sc = new Scanner(System.in);
		String movieName = "";
		String actorName = "";
		String actressName = "";
		double budget = 0.0;

		int i = 1;
		while (i > 0) {
			System.out.println("1. INSERT Movie");
			System.out.println("2. DELETE Movie");
			System.out.println("3. UPDATE Movie");
			System.out.println("4. SELECT Movie");
			System.out.println("5. Exit");

			System.out.println("Enter Your Choice :: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				sc.nextLine();
				System.out.println("Enter the Movie Name :: ");
				movieName = sc.nextLine();

				System.out.println("Enter the Actor Name :: ");
				actorName = sc.nextLine();

				System.out.println("Enter the Actress Name :: ");
				actressName = sc.nextLine();

				System.out.println("Enter the budget :: ");
				budget = sc.nextDouble();

				Movies movies = new Movies();
				movies.setMovieName(movieName);
				movies.setActorName(actorName);
				movies.setActressName(actressName);
				movies.setBudget(budget);

				try {
					String msg = controller.addMovieDetails(movies);
					System.out.println(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
			case 2:
				sc.nextLine();
				System.out.println("Enter the Movie Name :: ");
				movieName = sc.nextLine();

				try {
					String msg = controller.deleteMovieDetails(movieName);
					System.out.println(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
			case 3:
				sc.nextLine();
				System.out.println("Enter the Movie Name :: ");
				movieName = sc.nextLine();

				System.out.println("Enter the New Budget :: ");
				budget = sc.nextDouble();

				try {
					String msg = controller.updateMovieDetails(movieName, budget);
					System.out.println(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}

				break;

			case 4:
				sc.nextLine();
				System.out.println("Enter the Actor Name :: ");
				actorName = sc.nextLine();

				try {
					List<Movies> selectMovieDetails = controller.selectMovieDetails(actorName);
					for (Movies m : selectMovieDetails) {

						System.out.println("Movie Name   :: " + m.getMovieName());
						System.out.println("Actor Name   :: " + m.getActorName());
						System.out.println("Actress Name :: " + m.getActressName());
						System.out.println("Budget       :: " + m.getBudget());
						System.out.println("=======================================");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("Thank You");
				i = 0;
				break;
			default:
				System.out.println("Wrong Choice");
			}
		}

		if (sc != null)
			sc.close();

	}

}
