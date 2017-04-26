package com.retail.manager;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import com.retail.manager.domain.Shop;
import com.retail.manager.domain.ShopAddress;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetailManagerGetControllerTests extends BaseControllerTest{

	@Test
	public void shouldGetNearestShopDetails() throws Exception {
		
		Shop shop1 = new ShopBuilder().shopName("Raymond Retail Shop").shopAddress("1248/A, East Street, Cawasvilla, Opposite Wonderland, Cawasvilla, Pune", "411001").geoLocation(18.5110107,73.8800579).build();
		repository.save(shop1);
		
		Shop shop2 = new ShopBuilder().shopName("Shoppers Stop").shopAddress("Kumar Pacific Mall, Pacific Mall, S.No.42, 43, Shankar Seth Road, Pune", "411037").geoLocation(18.5011292,73.8724948).build();
		repository.save(shop2);
				
		Shop shop3 = new ShopBuilder().shopName("Superdry").shopAddress("Phoenix Market City, 20, Viman Nagar Rd, Clover Park, Viman Nagar, Pune", "411014").geoLocation(18.5635752,73.9176313).build();
		repository.save(shop3);
		
		ResultActions result = mockMvc.perform(get("/shops/lat/18.554994/lng/73.953224/nearest").accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk())
			.andExpect(jsonPath("$.shopName", equalTo("Superdry")))
			.andExpect(jsonPath("$.shopAddress.number", equalTo("Phoenix Market City, 20, Viman Nagar Rd, Clover Park, Viman Nagar, Pune")))
			.andExpect(jsonPath("$.shopAddress.postCode", equalTo("411014")))
			.andDo(restDoc("findNearestShop"));
	}

}
