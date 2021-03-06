package com.rugbyaholic.techshare.common.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rugbyaholic.techshare.common.Option;

@Mapper
public interface CodeRepository {
	
	public List<Option> getDepertmentCd();
	
	public List<Option> getPositionCd();
	
	public List<Option> getCode(long id);
}