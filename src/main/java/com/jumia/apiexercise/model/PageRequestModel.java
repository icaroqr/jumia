package com.jumia.apiexercise.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import lombok.Data;

@Data
public class PageRequestModel {
	
	private int page = 0;
	private int size = 10;
	private String sort = "";
	private String country = "";
	private String state = "";
	
	public PageRequestModel(Map<String, String> params) {
		if (params.containsKey("page")) page = Integer.parseInt(params.get("page"));
		if (params.containsKey("size")) size = Integer.parseInt(params.get("size"));
		if (params.containsKey("sort")) sort = params.get("sort");
		if (params.containsKey("country")) country = params.get("country");
		if (params.containsKey("state")) state = params.get("state");
	}
	
	public PageRequest toSpringPageRequest() {
		List<Order> orders = new ArrayList<>();
		
		String[] properties = sort.split(",");
		
		for(String prop : properties) {
			if (prop.trim().length() > 0) {
				String column = prop.trim();
				
				if (column.startsWith("-")) {
					column = column.replace("-", "").trim();
					
					orders.add(Order.desc(column));
				}
				else orders.add(Order.asc(column));
			}
		}
		
		return PageRequest.of(page, size, Sort.by(orders));
	}

}
