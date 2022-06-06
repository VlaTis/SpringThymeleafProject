package eu.codeacademy.vteshop.product.validator;

import eu.codeacademy.vteshop.operationStation.repository.OperationStationRepository;
import eu.codeacademy.vteshop.product.dto.ProductDto;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class OperationStationValidator implements ConstraintValidator<OperationStationValid, String> {

    private final OperationStationRepository operationStationRepository;

    @Override
    public void initialize(OperationStationValid operationStationName) {
    }

    @Override
    public boolean isValid(String operationStationName, ConstraintValidatorContext context) {

        return operationStationName !=null && operationStationRepository.findOperationStationByName(operationStationName).isPresent();
    }
}
