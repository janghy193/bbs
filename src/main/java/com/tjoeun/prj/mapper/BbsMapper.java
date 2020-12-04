package com.tjoeun.prj.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.tjoeun.prj.vo.BbsVO;

@Mapper
public interface BbsMapper {
	
	boolean writeBbs(BbsVO bb);
	
	ArrayList<BbsVO> getBbsList();
	
	BbsVO selectByNum(int num);
	
	boolean updateBbs(BbsVO bb);
	
	boolean deleteBbs(int num);
	
	ArrayList<BbsVO> search(String sKey, String sVal);
}