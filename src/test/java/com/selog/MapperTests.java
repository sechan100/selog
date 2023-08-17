package com.selog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.selog.dto.MainDto;
import com.selog.mapper.MainMapper;

@SpringBootTest
public class MapperTests {

	@Autowired
	private MainMapper mainMapper;
	
	@Test
	public void testInsert() {
		MainDto main = new MainDto();
		main.setName("testMapper");
		mainMapper.insertRecord(main);
	}	
}
