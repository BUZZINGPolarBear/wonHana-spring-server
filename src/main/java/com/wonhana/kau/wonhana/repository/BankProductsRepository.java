package com.wonhana.kau.wonhana.repository;

import com.wonhana.kau.wonhana.dto.BankProductsDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BankProductsRepository {

    private final NamedParameterJdbcTemplate template;

    public BankProductsRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<BankProductsDto> showBankProducts() {
        String sql = "select * from BankProducts";

        return template.query(sql, bankRowMapper());
    }

    private RowMapper<BankProductsDto> bankRowMapper() {
        return BeanPropertyRowMapper.newInstance(BankProductsDto.class);
    }
}
