package com.tjoeun.prj.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjoeun.prj.mapper.AttMapper;
import com.tjoeun.prj.mapper.BbsMapper;
import com.tjoeun.prj.vo.AttachmentVO;
import com.tjoeun.prj.vo.BbsVO;

@Controller
public class BbsController {
	private int rows = 12;

	@Autowired
	private BbsMapper bbsMapper;
	@Autowired
	private AttMapper attMapper;	
	@Autowired
	ResourceLoader resourceLoader;
	
	@GetMapping("/bbs/read/{num}")
	public String selectByNum(@PathVariable("num") int num, Model model) {
		ArrayList<AttachmentVO> attlist = attMapper.getFileList(num);
		BbsVO bb = bbsMapper.selectByNum(num);
		bb.setHit(bb.getHit()+1);
		bbsMapper.updateBbs(bb);
		
		model.addAttribute("attlist",attlist);
		model.addAttribute("bbs", bb);
		
		return "bbsdetail";
	}

	@GetMapping("/bbs")
	public String getBbsList(Model model) {
		PageHelper.startPage(1, rows);
		PageInfo<BbsVO> pageInfo = new PageInfo<>(bbsMapper.getBbsList());

		model.addAttribute("pageInfo", pageInfo);
		return "bbslist";
	}

	@GetMapping("/bbs/write")
	public String writeBbs() {
		return "bbswrite";
	}
	
	@GetMapping("/bbs/rewrite/{pnum}")
	public String rewriteBbs(Model model,@PathVariable int pnum) {
		model.addAttribute("pnum",pnum);
		return "bbswrite";
	}

	@ResponseBody
	@PutMapping("/bbs/upd")
	public String updateBbs(BbsVO b) {
		return bbsMapper.updateBbs(b) + "";
	}

	@ResponseBody
	@DeleteMapping("/bbs/delete")
	public String deleteBbs(int num) {
		return bbsMapper.deleteBbs(num) + "";
	}

	@GetMapping("/bbs/page/{num}")
	public String getUserListPage(@PathVariable int num, Model model) {
		PageHelper.startPage(num, rows);
		PageInfo<BbsVO> pageInfo = new PageInfo<>(bbsMapper.getBbsList());
		
		model.addAttribute("pageInfo", pageInfo);
		
		return "bbslist";
	}
	
	@GetMapping("bbs/search/{sKey}/{sVal}/")
	public String search(@PathVariable("sKey")String sKey, @PathVariable("sVal")String sVal, Model model) {
		PageHelper.startPage(1, rows);
		PageInfo<BbsVO> pageInfo = new PageInfo<>(bbsMapper.search(sKey, sVal));

		model.addAttribute("pageInfo", pageInfo);
			
		return "bbslist";
	}
	
	@GetMapping("bbs/search/{sKey}/{sVal}/{num}")
	public String searchPaging(@PathVariable("sKey")String sKey, @PathVariable("sVal")String sVal, Model model,@PathVariable("num")int num) {
		if(sVal.equals("")) {
			return "bbslist";
		}
		PageHelper.startPage(num, rows);
		PageInfo<BbsVO> pageInfo = new PageInfo<>(bbsMapper.search(sKey, sVal));

		model.addAttribute("pageInfo", pageInfo);
			
		return "bbslist";
	}

	@ResponseBody
	@PostMapping("bbs/save")
	public String save(@RequestParam(value = "files", required = false) MultipartFile[] mfiles,
			MultipartHttpServletRequest request, // upload폴더의 절대경로 필요하므로 인자로 사용
			@RequestParam("author") String author, @RequestParam("title") String title,
			@RequestParam("contents") String contents,
			@RequestParam(value="pnum", required=false) int pnum) {
		System.out.println("pnum: "+pnum);
		ServletContext context = request.getServletContext();
		String savePath = context.getRealPath("/WEB-INF/upload"); // upload폴더의 절대 경로를 얻음
		BbsVO bb = new BbsVO(0, author, "", title, contents, 0, pnum);
		
		boolean isFail = false;
		isFail = bbsMapper.writeBbs(bb);
		
		if (!mfiles[0].isEmpty()) {
			try {
				int getKey = bb.getNum();

				for (int i = 0; i < mfiles.length; i++) {
					System.out.println(mfiles[i].getContentType() + ", " + mfiles[i].getOriginalFilename() + ", "
							+ mfiles[i].getResource());
					mfiles[i].transferTo(new File(savePath + "/" + mfiles[i].getOriginalFilename())); // 디스크에 있었던 파일의
																										// 정보를 메모리에 객체화
					System.out.println("파일명: "+mfiles[i].getOriginalFilename());
					AttachmentVO a = new AttachmentVO(0, getKey, mfiles[i].getOriginalFilename(), mfiles[i].getSize(),
							mfiles[i].getContentType());
					isFail = attMapper.save(a);
				}
				return isFail + "";
			} catch (Exception e) {
				e.printStackTrace();
				isFail=false;
				return isFail + "";
			}
		}
		return isFail+"";
	}

	@GetMapping("download/{filename}")
	public ResponseEntity<Resource> download(HttpServletRequest request, @PathVariable String filename) {
		Resource resource = resourceLoader.getResource("WEB-INF/upload/" + filename);
		System.out.println("파일명:" + resource.getFilename());
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath()); // 절대파일 경로를
																										 // 정확하게 구하기
																										 // 위해
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (contentType == null) {
			contentType = "application/octet-stream"; // 브라우저가 그 데이터를 해석을 못하게 하여 저장으로 동작하게 함
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"") // 헤더에
																													// 들어갈
																													// 내용
				.body(resource); // 바디에 들어갈 내용
	}

}
