package com.rugbyaholic.techshare.comms.repositories;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;
import com.rugbyaholic.techshare.comms.Topic;
import com.rugbyaholic.techshare.comms.forms.TopicCreationForm;

@Mapper
public interface MeetingRoomRepository {
	
	public List<Topic> searchAllTopics();
	
	public void registerTopic(@Param("form") TopicCreationForm form, @Param("user") AuthenticatedUser user);
	
	public void registerPost(@Param("form") TopicCreationForm form, @Param("user") AuthenticatedUser user);
	
	public Optional<Topic> findTopic(String topicNo);
	
	public Optional<Integer> currentRating(@Param("topicNo") String topicNo, 
											@Param("postNo") int postNo, 
											@Param("user") AuthenticatedUser user);

	public void updateRating(@Param("topicNo") String topicNo, 
							@Param("postNo") int postNo, 
							@Param("user") AuthenticatedUser user,
							@Param("rating") int rating);
}