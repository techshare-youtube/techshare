package com.rugbyaholic.techshare.common.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;

@Mapper
public interface NumberingRepository {
	
	public String issueNumber(@Param("numberingCode") String numberingCode, @Param("availYear") String availYear);
	
	public void next(@Param("numberingCode") String numberingCode, @Param("availYear") String availYear, 
						@Param("user") AuthenticatedUser user);
}