package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

//doesn't touchy database, through the accessor.
public class FilmQueryApp {

	private DatabaseAccessorObject dao = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
	}

//	private void test() {
//
//		Actor actor = db.findActorById(88);
//		if (actor != null) {
//			System.out.println(actor);
//		} else {
//			System.out.println("No such actor found");
//		}
//		System.out.println(actor);

//    Film film = db.findFilmById(1);
//    System.out.println(film);
//	}

	private void launch() {

		int userInput = 0;
		while (userInput != 3) {
			Scanner input = new Scanner(System.in);

			System.out.println("-----------------MENU-----------------");
			System.out.println("-1: Look up film by its id-----------");
			System.out.println("-2: Look up film by a search keyword-");
			System.out.println("-3: Exit-");

			System.out.println("Please, enter a number option: ");
			userInput = input.nextInt();

			input.nextLine();

			if (userInput == 1) {
				// should be able to look up films by id
				System.out.println("Enter a number from 1-200 to look up a film: ");
				userInput = input.nextInt();
				filmBack(userInput);

			} else if (userInput == 2) {
				// should be able to type in "the" and get all films with "the
				System.out.println("Enter title of a film or a key word to search to find films: ");
				String userSearch = input.nextLine();
				keyWordSearch(userSearch);

			} else if (userInput == 3) {
				System.out.println("Ta Ta For Now.");
				input.close();
				break;
			} else {
				System.out.println("Bad Llama, Please Enter valid input.");
			}

			startUserInterface(input);

		}
	}

	private void startUserInterface(Scanner input) {

	}

	private void filmBack(int userChoice) {
		Film film = dao.findFilmById(userChoice);
		List<Actor> actorList = dao.findActorsByFilmId(userChoice);

		if (film != null) {
			System.out.println("Film Title: " + film.getTitle());
			System.out.println("Year: " + film.getYear());
			System.out.println("Film Rating: " + film.getRating());
			System.out.println("Film Description: " + film.getDescription());
			System.out.println("Language of film: " + dao.language(film.getLangId()));
			System.out.println("Cast: ");

			for (Actor actor : actorList) {
				System.out.println(actor.getFirstName() + " " + actor.getLastName());
			}

			System.out.println();
		} else {
			System.out.println("Film is not found.");
			System.out.println();
		}
	}

	private void keyWordSearch(String userChoice) {
		List<Film> filmList = dao.findFilmBySearch(userChoice);

		if (filmList.size() > 0) {
			List<Actor> actorList = new ArrayList<>();
			for (Film film : filmList) {
				System.out.println("Film Title: " + film.getTitle());
				System.out.println("Year: " + film.getYear());
				System.out.println("Film Rating: " + film.getRating());
				System.out.println("Film Description: " + film.getDescription());
				System.out.println("Language of film: " + dao.language(film.getLangId()));
				System.out.println("Cast: ");
				actorList = dao.findActorsByFilmId(film.getId());
				for (Actor actorObj : actorList) {
					System.out.println(actorObj.getFirstName() + " " + actorObj.getLastName());
				}
				System.out.println();

			}
		} else {
			System.out.println("Search criteria does not match any films.");
			System.out.println();
		}

	}

}
