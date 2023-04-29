package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String USER = "student";
	private static final String PWD = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		// ...to be filled in
		// make driver
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			// 1-form query
			String sql = "SELECT * FROM film WHERE id = ?";

			// PreparedStatement for the database.
			PreparedStatement stmt = conn.prepareStatement(sql);
			// assign bind variable
			stmt.setInt(1, filmId);

			// exicute query-----DONT apply sql statment here
			ResultSet filmResult = stmt.executeQuery();

			// Iterate over results
			if (filmResult.next()) {
				//// " must match the database "
				int id = filmResult.getInt("id");
				String title = filmResult.getString("title");
				String description = filmResult.getString("description");
				int year = filmResult.getInt("release_year");
				int langId = filmResult.getInt("language_id");
				int rentalDur = filmResult.getInt("rental_duration");
				double rateRental = filmResult.getDouble("rental_rate");
				int length = filmResult.getInt("length");
				double replaceCost = filmResult.getDouble("replacement_cost");
				String rating = filmResult.getString("rating");
				String specFeat = filmResult.getString("special_features");

				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setId(id);
				film.setTitle(title);
				film.setDescription(description);
				film.setYear(year);
				film.setLangId(langId);
				film.setRentalDur(rentalDur);
				film.setRateRental(rateRental);
				film.setLength(length);
				film.setReplaceCost(replaceCost);
				film.setRating(rating);
				film.setSpecFeat(specFeat);

//				film.setFilms(findFilmsByFilmId(filmId));

			}
			// ...close everything before return
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	public Actor findActorById(int actorId) {
		Actor actor = null;

		// ...to be filled in
		// make driver
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			// 1-form query
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";

			// PreparedStatement for the database.
			PreparedStatement stmt = conn.prepareStatement(sql);
			// assign bind variable
			stmt.setInt(1, actorId);

			// exicute query-----DONT apply sql statment here
			ResultSet actorResult = stmt.executeQuery();

			// Iterate over results
			if (actorResult.next()) {
				int id = actorResult.getInt("id");
				String firstN = actorResult.getString("first_name");
				String lastN = actorResult.getString("last_name");

				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(id);
				actor.setFirstName(firstN);
				actor.setLastName(lastN);

				actor.setFilms(findFilmsByActorId(actorId));

			}
			// ...close everything before return
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		try {
			// 1 connect
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			// 2 form query
			String sql = "SELECT film.* FROM film JOIN film_actor ON film.id = film_actor.film_id WHERE film_actor.actor_id = ?";

			// 3 prepare the statement for the db.
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

			// to check
			// System.out.println(stmt);

			// 4 run the statement
			ResultSet rs = stmt.executeQuery();

			// 5 iterate results
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");

				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorList = new ArrayList<>();
		try {
			// 1 connect
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			// 2 form query
			String sql = "SELECT first_name, last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_id = ?;";

			// 3 prepare the statement for the db.
			PreparedStatement stmt = conn.prepareStatement(sql);
//LEFT OFF HERE do we need to bind one variable or to
			stmt.setInt(1, filmId);

			// to check
			// System.out.println(stmt);

			// 4 run the statement
			ResultSet actorResult = stmt.executeQuery();

			// 5 iterate results
			while (actorResult.next()) {
				//int id = actorResult.getInt("id");
				String firstN = actorResult.getString("first_name");
				String lastN = actorResult.getString("last_name");

				Actor actorObj = new Actor(firstN, lastN); // Create the object
				// Here is our mapping of query columns to our object fields:
			//	actor.setId(id);
				
				actorList.add(actorObj);
				
			}
			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actorList;
	}

	public List<Film> findFilmBySearch(String search) {
		List<Film> films = new ArrayList<>();
		try {
			// 1 connect
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			// 2 form query
// CHANGE QUERY
			String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?;";

			// 3 prepare the statement for the db.
			PreparedStatement stmt = conn.prepareStatement(sql);
//LEFT OFF HERE do we need to bind one variable or to
			stmt.setString(1, "%" + search + "%");
			stmt.setString(2, "%" + search + "%");
//			stmt.setS(1, search);

			// to check
			// System.out.println(stmt);

			// 4 run the statement
			ResultSet rs = stmt.executeQuery();

			// 5 iterate results
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");

				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	public String language(int langId) {
		String langName = "";

		try {

			// 1 connect
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			// 2 form query
			String sql = "SELECT name FROM language WHERE id = ?;";

			// 3 prepare the statement for the db.
			PreparedStatement stmt = conn.prepareStatement(sql);
//LEFT OFF HERE do we need to bind one variable or to
			stmt.setInt(1, langId);

			// to check
			// System.out.println(stmt);

			// 4 run the statement
			ResultSet rs = stmt.executeQuery();

			// 5 iterate results
			while (rs.next()) {

				langName = rs.getString("name");

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return langName;
	}

}
