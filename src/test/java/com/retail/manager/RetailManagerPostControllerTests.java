package com.retail.manager;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetailManagerPostControllerTests extends BaseControllerTest{

	@SuppressWarnings("serial")
	@Test
	public void shouldSaveShopDetails() throws Exception {
		HashMap<String, String> shopAdd = new HashMap<String, String>(){{
			put("number", "1123 443");
			put("postCode", "411 014");
		}};
		String payload = new ShopBuilder().shopName("BigBasket").shopAddress(shopAdd).buildAsJson();
		
		ResultActions result = mockMvc.perform(post("/shops")
				.content(payload)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk())
			.andExpect(jsonPath("$.shopName", equalTo("BigBasket")))
			.andDo(restDoc("addNewShop"));
	}

}
