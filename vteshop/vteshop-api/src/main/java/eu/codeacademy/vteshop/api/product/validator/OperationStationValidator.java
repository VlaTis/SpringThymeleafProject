package eu.codeacademy.vteshop.api.product.validator;

import eu.codeacademy.vteshop.jpa.operation.station.repository.OperationStationRepository;


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
