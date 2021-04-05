package com.rugbyaholic.techshare.comms;

import java.util.Date;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;

public class PostRating {
	
	private AuthenticatedUser rater;
	
	private int rating;
	
	private Date ratedAt;

	public AuthenticatedUser getRater() {
		return rater;
	}

	public void setRater(AuthenticatedUser rater) {
		this.rater = rater;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getRatedAt() {
		return ratedAt;
	}

	public void setRatedAt(Date ratedAt) {
		this.ratedAt = ratedAt;
	}
}