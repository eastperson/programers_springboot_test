package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Optional<Orders> findById(long id);

    List<Orders> findAll(Pageable pageable);
}
