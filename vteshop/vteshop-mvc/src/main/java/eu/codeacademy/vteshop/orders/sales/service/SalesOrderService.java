package eu.codeacademy.vteshop.orders.sales.service;

import eu.codeacademy.vteshop.orders.sales.dto.SalesOrderDto;
import eu.codeacademy.vteshop.orders.sales.entity.SalesOrder;
import eu.codeacademy.vteshop.orders.sales.mapper.SalesOrderMapper;
import eu.codeacademy.vteshop.orders.sales.repository.SalesOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesOrderService {

    private final SalesOrderRepository orderRepository;
    private final SalesOrderMapper orderMapper;


    @Transactional
    public void addSalesOrder(SalesOrderDto salesOrderDto){
        orderRepository.save(SalesOrder.builder()
                        .name(salesOrderDto.getName())
                        .totalPrice(salesOrderDto.getTotalPrice())
                .build());
    }

    public List<SalesOrderDto> getSalesOrders(){
        return orderRepository.findAll().stream()
                .map(orderMapper::mapTo)
                .collect(Collectors.toList());

    }


}
