package com.wonhana.kau.wonhana.repository;

import com.wonhana.kau.wonhana.dto.UserInfoDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class UserInfoRepository {

    private final NamedParameterJdbcTemplate template;

    public UserInfoRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<UserInfoDto> showAll() {
        String sql = "select *, (car + accountBalance + stock + house) as asset," +

                "(houseLoan + carLoan) as liabilities," +

                "(fixedIncome + flexibleIncome) as income," +

                "(houseLoanInterest + carLent + carInsurance + Dues + " +
                "communicationCost + subscribeFee) as fixedExpenditure," +

                "(coffee + food + snack + liquidNCigarette + necessityProduct + " +
                "oil + leisureCost + stockPurchase) as flexibleExpenditure," +

                "(houseLoanInterest + carLent + carInsurance + Dues +" +
                "communicationCost + subscribeFee + coffee + food + snack +" +
                "liquidNCigarette + necessityProduct + oil + " +
                "leisureCost + stockPurchase) as outcome " +

                "from FixedExpenditure, FlexibleExpenditure, UserAsset, UserDebt, UserIncome";

        return template.query(sql, userDtoRowMapper());
    }


    public List<UserInfoDto> findById(Long id) {
        String sql = "select *, (car + accountBalance + stock + house) as asset," +

                "(houseLoan + carLoan) as liabilities," +

                "(fixedIncome + flexibleIncome) as income," +

                "(houseLoanInterest + carLent + carInsurance + Dues + " +
                "communicationCost + subscribeFee) as fixedExpenditure," +

                "(coffee + food + snack + liquidNCigarette + necessityProduct + " +
                "oil + leisureCost + stockPurchase) as flexibleExpenditure," +

                "(houseLoanInterest + carLent + carInsurance + Dues +" +
                "communicationCost + subscribeFee + coffee + food + snack +" +
                "liquidNCigarette + necessityProduct + oil + " +
                "leisureCost + stockPurchase) as outcome " +

                "from FixedExpenditure, FlexibleExpenditure, UserAsset, UserDebt, UserIncome " +

                "where FixedExpenditure.id = :FixedExpenditure.id and " +
                "FlexibleExpenditure.id = :FlexibleExpenditure.id and " +
                "UserAsset.id = :UserAsset.id and " +
                "UserIncome.id = :UserIncome.id and " +
                "UserDebt.id = :UserDebt.id";

        Map<String, Long> param = Map.of("FixedExpenditure.id", id, "FlexibleExpenditure.id", id,
                "UserAsset.id", id, "UserDebt.id", id, "UserIncome.id", id);

        return template.query(sql, param, userDtoRowMapper());
    }

    private RowMapper<UserInfoDto> userDtoRowMapper() {
        return BeanPropertyRowMapper.newInstance(UserInfoDto.class);
    }
}
