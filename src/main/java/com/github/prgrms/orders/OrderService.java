package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Orders> findById(Long orderId) {
        checkNotNull(orderId, "orderId must be provided");

        return orderRepository.findById(orderId);
    }

    @Transactional(readOnly = true)
    public List<Orders> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

}
