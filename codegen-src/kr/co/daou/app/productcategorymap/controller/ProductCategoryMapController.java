package kr.co.daou.app.productcategorymap.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.co.daou.app.productcategorymap.service.ProductCategoryMapService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-category-maps")
public class ProductCategoryMapController {

    private final ProductCategoryMapService productCategoryMapService;

    //TODO: create your api here
}