package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcOrderRepository implements OrderRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Orders> findById(long id) {
        List<Orders> results = jdbcTemplate.query(
                "SELECT * FROM orders WHERE seq=?",
                mapper,
                id
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public List<Orders> findAll(Pageable pageable) {

        //TODO total 로직 구현
        //int total = jdbcTemplate.queryForObject("select count(*) from orders",Integer.class);

        List<Orders> orders = jdbcTemplate.query(
                "SELECT * FROM orders ORDER BY seq DESC LIMIT " + pageable.getSize() + " OFFSET " + pageable.getOffset(),
                mapper
        );

        return orders;
    }

    static RowMapper<Orders> mapper = (rs, rowNum) ->
            new Orders.Builder()
                    .seq(rs.getLong("seq"))
                    .userSeq(rs.getLong("user_seq"))
                    .productSeq(rs.getLong("product_seq"))
                    .reviewSeq(rs.getLong("review_seq"))
                    .state(rs.getString("state"))
                    .requestMsg(rs.getString("request_msg"))
                    .rejectMsg(rs.getString("reject_msg"))
                    .completedAt(dateTimeOf(rs.getTimestamp("completed_at")))
                    .rejectedAt(dateTimeOf(rs.getTimestamp("rejected_at")))
                    .createAt(dateTimeOf(rs.getTimestamp("create_at")))
                    .build();
}
