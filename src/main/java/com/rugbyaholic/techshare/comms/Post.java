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
	
	public boolean isFirstPost() {
		return postNo == 1;
	}
	
	public PostRating getRateByUser(AuthenticatedUser user) {
		PostRating postRating = ratings.stream()
				.filter(p -> Objects.equals(p.getRater().getEmpNo(), user.getEmpNo()))
				.findFirst().orElse(new PostRating());
		return postRating;
	}
	
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