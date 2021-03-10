package com.github.prgrms.orders;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Optional<Orders> findById(long id);

    List<Orders> findAll();
}
