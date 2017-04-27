package com.retail.manager;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetailManagerPostControllerTests extends BaseControllerTest {

	@Test
	public void shouldSaveShopDetails() throws Exception {
		String payload = new ShopBuilder().shopName("Raymond Retail Shop")
				.shopAddress("1248/A, East Street, Cawasvilla, Opposite Wonderland, Cawasvilla, Pune", "411001")
				.buildAsJson();

		ResultActions result = mockMvc.perform(post("/shops").content(payload).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk()).andExpect(jsonPath("$.shopName", equalTo("Raymond Retail Shop")))
				.andExpect(jsonPath("$.message", equalTo("Successfully saved shop details")))
				.andExpect(jsonPath("$.shopAddress.number",
						equalTo("1248/A, East Street, Cawasvilla, Opposite Wonderland, Cawasvilla, Pune")))
				.andExpect(jsonPath("$.shopAddress.postCode", equalTo("411001"))).andDo(restDoc("addNewShop"));
	}

	@Test
	public void shouldReplaceOlderShopDetails() throws Exception {
		String payload = new ShopBuilder().shopName("Raymond Retail Shop")
				.shopAddress("1248/A, East Street, Cawasvilla, Opposite Wonderland, Cawasvilla, Pune", "411001")
				.buildAsJson();

		String payload2 = new ShopBuilder().shopName("Raymond Retail Shop")
				.shopAddress("1248/A, East Street, Cawasvilla, Opposite Wonderland, Pune", "411001")
				.buildAsJson();
		
		@SuppressWarnings("unused")
		ResultActions result = mockMvc.perform(post("/shops").content(payload).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		ResultActions result2 = mockMvc.perform(post("/shops").content(payload2).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));

		result2.andExpect(status().isOk()).andExpect(jsonPath("$.shopName", equalTo("Raymond Retail Shop")))
				.andExpect(jsonPath("$.message", equalTo("Current Version replaced Previous version successfully")))
				.andExpect(jsonPath("$.shopAddress.number",
						equalTo("1248/A, East Street, Cawasvilla, Opposite Wonderland, Pune")))
				.andExpect(jsonPath("$.shopAddress.postCode", equalTo("411001"))).andDo(restDoc("replaceOldShop"));
	}

}
