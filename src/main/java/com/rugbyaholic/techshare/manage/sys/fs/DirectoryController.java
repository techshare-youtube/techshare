package com.rugbyaholic.techshare.manage.sys.fs;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rugbyaholic.techshare.manage.sys.fs.upload.UploadForm;

@Controller
public class DirectoryController {
	
	@Autowired
	private DirectoryService directoryService;
	
	@GetMapping("/manage/sys/fs/DirectoryViewer.html")
	public String onDirectoryViewerRequested(Model model) {
		File homeDirectory = new File(directoryService.homeDirectory());
		setupForm(homeDirectory, new UploadForm(), model);
		return "manage/sys/fs/DirectoryViewer.html";
	}
	
	@GetMapping("/manage/sys/fs/ChangeDirectory.do")
	public String onChangeDirectoryRequested(@RequestParam("to") String to, Model model) {
		File currentDirectory = new File(to);
		setupForm(currentDirectory, new UploadForm(), model);
		return "manage/sys/fs/DirectoryViewer.html";
	}
	
	@PostMapping("/manage/sys/fs/AppendFolder.do")
	public String onAppendFolderRequested(@RequestParam("currentDirectory") String currentDirectory,
											@RequestParam("folderName") String folderName, Model model) {
		directoryService.makeDirectory(currentDirectory, folderName);
		setupForm(new File(currentDirectory), new UploadForm(), model);
		return "manage/sys/fs/DirectoryViewer.html";
	}
	
	@PostMapping("/manage/sys/fs/Upload.do")
	public String onUploadRequested(@Valid @ModelAttribute UploadForm uploadForm, BindingResult bindingResult,
									Model model) {
		if (!bindingResult.hasErrors()) {
			try {
				directoryService.upload(uploadForm);
			} catch (IllegalStateException | IOException e) {
				// TODO エラー画面への遷移
			}
		}
		setupForm(new File(uploadForm.getPath()), uploadForm, model);
		return "manage/sys/fs/DirectoryViewer.html";
	}
	
	@GetMapping("/manage/sys/fs/Download.do")
	public ResponseEntity<byte[]> onDownloadRequested(@RequestParam("target") String target, Model model) {
		File sourceFile = new File(target);
		byte[] fileData = null;
		try {
			fileData = directoryService.extractFileData(sourceFile);
		} catch (IOException e) {
			// TODO エラーページへの遷移
		}
		HttpHeaders headers = new HttpHeaders();
		String contentDispositionValue = String.format("attachment; filename=\"%s\"", sourceFile.getName());
		headers.add("Content-Disposition", contentDispositionValue);
		return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
	}
	
	private boolean isParentAvailable(File currentDirectory) {
		return currentDirectory.getParentFile().exists() &&
				currentDirectory.getParentFile().getAbsolutePath().contains(directoryService.homeDirectory());
	}
	
	private void setupForm(File currentDir, UploadForm uploadForm, Model model) {
		uploadForm.setPath(currentDir.getAbsolutePath());
		model.addAttribute("currentDirectory", currentDir);
		model.addAttribute("parentAvailable", isParentAvailable(currentDir));
		model.addAttribute("uploadForm", uploadForm);
	}
}