package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String description;
	private int year;
	private int langId;
	private int rentalDur;
	private double rateRental;
	private int length;
	private double replaceCost;
	private String rating; 
	private String specFeat;
	//private List<Film> films;
	
	public Film() {}
	
	
	
	
	public Film(int id, String title, String description, int year, int langId, int rentalDur, double rateRental,
			int length, double replaceCost, String rating, String specFeat) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.year = year;
		this.langId = langId;
		this.rentalDur = rentalDur;
		this.rateRental = rateRental;
		this.length = length;
		this.replaceCost = replaceCost;
		this.rating = rating;
		this.specFeat = specFeat;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getLangId() {
		return langId;
	}
	public void setLangId(int langId) {
		this.langId = langId;
	}
	public int getRentalDur() {
		return rentalDur;
	}
	public void setRentalDur(int rentalDur) {
		this.rentalDur = rentalDur;
	}
	public double getRateRental() {
		return rateRental;
	}
	public void setRateRental(double rateRental) {
		this.rateRental = rateRental;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public double getReplaceCost() {
		return replaceCost;
	}
	public void setReplaceCost(double replaceCost) {
		this.replaceCost = replaceCost;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getSpecFeat() {
		return specFeat;
	}
	public void setSpecFeat(String specFeat) {
		this.specFeat = specFeat;
	}
	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", description=" + description + ", year=" + year + ", langId="
				+ langId + ", rentalDur=" + rentalDur + ", rateRental=" + rateRental + ", length=" + length
				+ ", replaceCost=" + replaceCost + ", rating=" + rating + ", specFeat=" + specFeat + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, id, langId, length, rateRental, rating, rentalDur, replaceCost, specFeat,
				title, year);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(description, other.description) && id == other.id && langId == other.langId
				&& length == other.length
				&& Double.doubleToLongBits(rateRental) == Double.doubleToLongBits(other.rateRental)
				&& Objects.equals(rating, other.rating) && rentalDur == other.rentalDur
				&& Double.doubleToLongBits(replaceCost) == Double.doubleToLongBits(other.replaceCost)
				&& Objects.equals(specFeat, other.specFeat) && Objects.equals(title, other.title) && year == other.year;
	} 
	
	
	
	
	
}
