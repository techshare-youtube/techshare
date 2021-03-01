package com.rugbyaholic.techshare.common.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NumberingRepository {
	
	public String issue(@Param("numberingCode") String numberingCode, @Param("availYear") String availYear);
	
	public void increase(@Param("numberingCode") String numberingCode, @Param("availYear") String availYear, @Param("userId") long userId);
}