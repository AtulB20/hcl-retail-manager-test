package com.retail.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.retail.manager.domain.Shop;
import com.retail.manager.domain.ShopAddress;
import com.retail.manager.service.ShopRepository;

@SpringBootApplication
public class RetailManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailManagerApplication.class, args);
	}
	
	/*@Component
    class Dummy implements CommandLineRunner{

        @Autowired
        ShopRepository repo;

        @Override
        public void run(String... string) throws Exception {

            Shop shop = new Shop();
            shop.setShopName("test");
            ShopAddress shopAddr = new ShopAddress();
            shopAddr.setNumber("1111");
            shopAddr.setPostCode("447441");
            shop.setShopAddress(shopAddr);
            repo.save(shop);

            repo.findAll().forEach(p -> {
            	System.out.println(p.getShopName());
            	System.out.println(p.getShopAddress().getNumber());
            	System.out.println(p.getShopAddress().getPostCode());
            });

        }
    }*/
}
