package eu.codeacademy.vteshop.product.validator;

import eu.codeacademy.vteshop.operationStation.repository.OperationStationRepository;
import eu.codeacademy.vteshop.product.dto.ProductDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
@Getter
public class OperationStationValidator implements ConstraintValidator<OperationStationValid, ProductDto> {

    OperationStationRepository operationStationRepository;


    @Override
    public boolean isValid(ProductDto productDto, ConstraintValidatorContext context) {

        String currentOperationStationName = productDto.getOperationStationName();

        return operationStationRepository.findOperationStationByName(currentOperationStationName).isPresent();
    }
}
