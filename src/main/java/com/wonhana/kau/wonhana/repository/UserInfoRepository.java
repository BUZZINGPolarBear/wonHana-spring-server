package com.wonhana.kau.wonhana.repository;

import com.wonhana.kau.wonhana.dto.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class UserInfoRepository {

    private final NamedParameterJdbcTemplate template;
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

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
        String sql =
        "SELECT U.id,name, email, state, houseLoanInterest, carLent, carInsurance, dues, communicationCost, subscribeFee, coffee, food, snack, liquidNCigarette, necessityProduct, oil, car, accountBalance, stock, house, houseLoan, carLoan, flexibleIncome, fixedIncome,\n" +
                "       (car + accountBalance + stock + house) as asset,\n" +
                "       (houseLoan + carLoan) as liabilities,\n" +
                "        (fixedIncome + flexibleIncome) as income,\n" +
                "        (houseLoanInterest + carLent + carInsurance + Dues + communicationCost + subscribeFee) as fixedExpenditure,\n" +
                "        (coffee + food + snack + liquidNCigarette + necessityProduct + oil + lifeStyle) as flexibleExpenditure,\n" +
                "        (houseLoanInterest + carLent + carInsurance + Dues + communicationCost + subscribeFee + coffee + food + snack + liquidNCigarette + necessityProduct + oil + lifeStyle) as outcome\n" +
        "FROM User AS U, UserAsset AS UA, UserDebt AS UD, UserIncome AS UI, FlexibleExpenditure AS FlexibleE, FixedExpenditure AS FixedE\n" +
        "WHERE UA.userId = U.id AND UD.userId = U.id AND UI.userId = U.id AND FlexibleE.userId = U.id AND FixedE.userId = U.id AND U.id = :User.id";

        Map<String, Long> param = Map.of("User.id", id);

        return template.query(sql, param, userDtoRowMapper());
    }

    private RowMapper<UserInfoDto> userDtoRowMapper() {
        return BeanPropertyRowMapper.newInstance(UserInfoDto.class);
    }
}
