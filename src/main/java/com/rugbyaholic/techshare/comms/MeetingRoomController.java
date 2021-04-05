package com.rugbyaholic.techshare.comms;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;
import com.rugbyaholic.techshare.comms.forms.TopicCreationForm;

@Controller
public class MeetingRoomController {
	
	@Autowired
	private MeetingRoomService service;
	
	// 初期表示
	@GetMapping("/comms/MeetingRoom.html")
	public String onMeetingRoomRequested(Model model) {
		model.addAttribute("topicCreationForm", new TopicCreationForm());
		model.addAttribute("topics", service.loadTopics());
		return "comms/MeetingRoom.html";
	}
	
	// トピック新規作成
	@PostMapping("/comms/CreateTopic.do")
	public String onTopicRegistrationRequested(@Valid @ModelAttribute TopicCreationForm form,
												BindingResult bindingResult, Model model,
												@AuthenticationPrincipal AuthenticatedUser user) {
		if (bindingResult.hasErrors()) {
			form.setError(true);
			model.addAttribute("topicCreationForm", form);
			model.addAttribute("topics", service.loadTopics());
			return "comms/MeetingRoom.html";
		} else {
			service.registerNewTopic(form, user);
			return "redirect:/comms/MeetingRoom.html";
		}
	}
}