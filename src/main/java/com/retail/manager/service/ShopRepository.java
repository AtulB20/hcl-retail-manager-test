package com.retail.manager.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.retail.manager.domain.Shop;

@Repository
public interface ShopRepository extends MongoRepository<Shop, Long> {

	Shop findByShopName(String shopName);
}
