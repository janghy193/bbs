package com.tjoeun.prj.mapper;

import java.util.ArrayList;
//import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tjoeun.prj.vo.AttachmentVO;

@Mapper
public interface AttMapper {
	@Insert("INSERT INTO attachment VALUES(NULL,#{num},#{filename},#{filesize},#{mimetype})")
	boolean save(AttachmentVO at);
	
	@Select("SELECT * FROM attachment WHERE num=#{num}")
	ArrayList<AttachmentVO> getFileList(int num);
}
