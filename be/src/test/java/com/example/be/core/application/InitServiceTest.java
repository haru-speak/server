package com.example.be.core.application;

import com.example.be.tool.DataBaseConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public abstract class InitServiceTest {

	@Autowired
	private DataBaseConfigurator dbConfigurator;

	@BeforeEach
	void setUpDataBase() {
		dbConfigurator.clear();
		dbConfigurator.initDataSource();
	}
}
