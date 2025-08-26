package com.manu.order_service.service;

import com.manu.order_service.dto.OrderDTO;
import com.manu.order_service.model.Order;
import com.manu.order_service.mapper.OrderMapper;
import com.manu.order_service.repository.OrderRepository;
import com.manu.order_service.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repo;
    private final OrderMapper mapper;

    @Transactional
    public OrderDTO placeOrder(OrderDTO dto) {

        return mapper.toDTO(
                repo.save(
                        mapper.toEntity(dto)
                )
        );
    }

    public OrderDTO findOrderById(Long id) {
        return repo.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public List<OrderDTO> findAllOrders() {
        return repo.findAll()
                .stream().map(mapper::toDTO)
                .toList();
    }

    @Transactional
    public void deleteOrderById(Long id) {
        repo.deleteById(id);
    }

    @Transactional
    public OrderDTO updateOrderById(Long id, OrderDTO dto) {
        Order order = repo.findById(id).orElseThrow(() -> new OrderNotFoundException(id));

        Order orderUpdated = mapper.partialUpdate(dto, order);

        return mapper.toDTO(repo.save(orderUpdated));
    }
}
