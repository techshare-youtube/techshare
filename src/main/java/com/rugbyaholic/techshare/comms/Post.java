package com.rugbyaholic.techshare.comms;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;

public class Post {
	
	private int postNo;
	
	private String postText;
	
	private Date postedAt;
	
	private AuthenticatedUser author;
	
	private List<PostRating> ratings;
	
	public List<PostRating> goodRatings() {
		return ratings.stream().filter(p -> p.getRating() == 1)
						.collect(Collectors.toList());
	}
	
	public List<PostRating> badRatings() {
		return ratings.stream().filter(p -> p.getRating() == -1)
						.collect(Collectors.toList());
	}
	
	public boolean isPostedBy(AuthenticatedUser user) {
		return Objects.equals(user.getEmpNo(), author.getEmpNo());
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}

	public Date getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(Date postedAt) {
		this.postedAt = postedAt;
	}

	public AuthenticatedUser getAuthor() {
		return author;
	}

	public void setAuthor(AuthenticatedUser author) {
		this.author = author;
	}

	public List<PostRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<PostRating> ratings) {
		this.ratings = ratings;
	}
}