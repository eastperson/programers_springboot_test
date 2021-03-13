package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.errors.NotFoundException;
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

    public void updateStateToAccepted(Orders orders) {
        if(orders.getState().equals(State.REQUESTED.toString())){
            orderRepository.updateStateToAccepted(orders.getSeq());
        }
    }

    public void addReview(Long orderSeq, Long reviewSeq) {
        orderRepository.addReview(orderSeq,reviewSeq);
    }

    public void updateStateToRejected(Orders orders,String rejectMsg) {
        if(orders.getState().equals(State.REQUESTED.toString())){
            orderRepository.updateStateToRejected(orders.getSeq(),rejectMsg);
        }
    }

    public void updateStateToShipping(Orders orders) {
        if(orders.getState().equals(State.ACCEPTED.toString())){
            orderRepository.updateStateToShipping(orders.getSeq());
        }
    }

    public void updateStateToComplete(Orders orders) {
        if(orders.getState().equals(State.SHIPPING.toString())){
            orderRepository.updateStateToComplete(orders.getSeq());
        }
    }
}
