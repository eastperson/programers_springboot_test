package com.github.prgrms.products;

import com.github.prgrms.errors.NotFoundException;


import static com.github.prgrms.utils.ApiUtils.ApiResult;
import static com.github.prgrms.utils.ApiUtils.error;
import static com.github.prgrms.utils.ApiUtils.success;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "{id}")
    public ApiResult<ProductDto> findById(@PathVariable Long id) {
            return success(productService.findById(id)
                    .map(ProductDto::new)
                    .orElseThrow(() -> new NotFoundException("Could not found product for " + id)));

    }

    @GetMapping
    public ApiResult<List<ProductDto>> findAll() {
            return success(productService.findAll().stream()
                    .map(ProductDto::new)
                    .collect(toList()));
    }

}