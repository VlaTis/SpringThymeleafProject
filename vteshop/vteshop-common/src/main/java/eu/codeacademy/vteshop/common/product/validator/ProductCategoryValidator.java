package eu.codeacademy.vteshop.common.product.validator;

import eu.codeacademy.vteshop.product.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ProductCategoryValidator implements ConstraintValidator<ProductCategoryValid, String> {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public void initialize(ProductCategoryValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String productCategoryName, ConstraintValidatorContext context) {
        return productCategoryName != null && productCategoryRepository.findProductCategoryByName(productCategoryName).isPresent();
    }
}
