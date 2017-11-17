package com.app.controller;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.app.BaseTest;

@WebAppConfiguration
public abstract class BaseControllerTest extends BaseTest {

	protected MockMvc mvc;

	// protected void setUp(BaseController controller) {
	// this.mvc = MockMvcBuilders.standaloneSetup(controller)
	// .build();
	// }

}