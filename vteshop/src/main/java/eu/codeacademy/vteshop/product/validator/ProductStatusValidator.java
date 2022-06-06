package eu.codeacademy.vteshop.product.validator;


import eu.codeacademy.vteshop.product.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@RequiredArgsConstructor
public class ProductStatusValidator implements ConstraintValidator<ProductStatusValid, String> {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public void initialize(ProductStatusValid productStatusName) {
    }

    @Override
    public boolean isValid(String productStatusName, ConstraintValidatorContext context) {



        return productStatusName != null && productCategoryRepository.findProductCategoryByName(productStatusName).isPresent();
    }
}
