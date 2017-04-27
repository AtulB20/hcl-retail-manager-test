package com.retail.manager.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.manager.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

	Optional<Shop> findByShopName(String shopName);

}