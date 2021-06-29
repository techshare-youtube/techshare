package com.rugbyaholic.techshare.manage.sys.fs;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DirectoryController {
	
	@Autowired
	private DirectoryService directoryService;
	
	@GetMapping("/manage/sys/fs/DirectoryViewer.html")
	public String onDirectoryViewerRequested(Model model) {
		File homeDirectory = new File(directoryService.homeDirectory());
		model.addAttribute("currentDirectory", homeDirectory);
		model.addAttribute("parentAvailable", false);
		return "manage/sys/fs/DirectoryViewer.html";
	}
	
	@GetMapping("/manage/sys/fs/ChangeDirectory.do")
	public String onChangeDirectoryRequested(@RequestParam("to") String to, Model model) {
		File currentDirectory = new File(to);
		File parent = currentDirectory.getParentFile();
		boolean parentAvailable = parent.exists() && parent.getAbsolutePath().contains(directoryService.homeDirectory());
		model.addAttribute("currentDirectory", currentDirectory);
		model.addAttribute("parentAvailable", parentAvailable);
		return "manage/sys/fs/DirectoryViewer.html";
	}
}