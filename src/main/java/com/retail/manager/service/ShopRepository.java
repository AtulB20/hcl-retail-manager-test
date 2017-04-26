package com.retail.manager.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.retail.manager.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

	/*Shop save(Shop shop);
    
	List<Shop> findAll();
*/
    Shop findByShopName(String shopName);

}