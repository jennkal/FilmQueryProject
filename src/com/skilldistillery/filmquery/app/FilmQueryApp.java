package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;

//doesn't touchy database, through the accessor.
public class FilmQueryApp {
  
  private DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    app.test();
//    app.launch();
  }

  private void test() {
	  
	  Actor actor = db.findActorById(88);
	  if (actor != null ) {
		  System.out.println(actor);
	  } else {
		  System.out.println("No such actor found");
	  }
	  System.out.println(actor);
	  
//    Film film = db.findFilmById(1);
//    System.out.println(film);
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
    
  }

}
