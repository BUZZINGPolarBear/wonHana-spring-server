package com.wonhana.kau.wonhana.repository;

import com.wonhana.kau.wonhana.dto.ShowUserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import com.wonhana.kau.wonhana.dto.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class UserInfoRepository {

    private final NamedParameterJdbcTemplate template;
    private static JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate assetInsert;
    private final NamedParameterJdbcTemplate debtInsert;
    private final NamedParameterJdbcTemplate incomeInsert;
    private final NamedParameterJdbcTemplate flexibleExpenditureInsert;
    private final NamedParameterJdbcTemplate fixedExpenditureDebtInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public UserInfoRepository(DataSource dataSource) {

        this.template = new NamedParameterJdbcTemplate(dataSource);

        this.assetInsert = new NamedParameterJdbcTemplate(dataSource);
        this.debtInsert = new NamedParameterJdbcTemplate(dataSource);
        this.incomeInsert = new NamedParameterJdbcTemplate(dataSource);
        this.flexibleExpenditureInsert = new NamedParameterJdbcTemplate(dataSource);
        this.fixedExpenditureDebtInsert = new NamedParameterJdbcTemplate(dataSource);
    }

    public AssetDto saveAssetInfo(AssetDto assetDto) {
        String sql = "insert into UserAsset (userId, car, accountBalance, stock, house) " +
                "values (:userId, :car, :accountBalance, :stock, :house)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(assetDto);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        assetInsert.update(sql, param, keyHolder);

        return assetDto;
    }

    public DebtDto saveDebtInfo(DebtDto debtDto) {
        String sql = "insert into UserDebt (userId, houseLoan, carLoan) " +
                "values (:userId, :houseLoan, :carLoan)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(debtDto);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        debtInsert.update(sql, param, keyHolder);

        return debtDto;
    }

    public IncomeDto saveIncomeInfo(IncomeDto incomeDto) {
        String sql = "insert into UserIncome (userId, flexibleIncome, fixedIncome) " +
                "values (:userId, :flexibleIncome, :fixedIncome)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(incomeDto);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        incomeInsert.update(sql, param, keyHolder);

        return incomeDto;
    }

    public FlexibleExpenditureDto saveFlexibleExpenditure(FlexibleExpenditureDto flexibleDto) {
        String sql = "insert into FlexibleExpenditure (userId, coffee, food, " +
                "snack, liquidNCigarette, lifeStyle, oil) " +
                "values (:userId, :coffee, :food, " +
                ":snack, :liquidNCigarette, :lifeStyle, :oil)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(flexibleDto);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        flexibleExpenditureInsert.update(sql, param, keyHolder);

        return flexibleDto;
    }

    public FixedExpenditureDto saveFixedExpenditure(FixedExpenditureDto fixedDto) {
        String sql = "insert into FixedExpenditure (userId, houseLoanInterest, " +
                "carLent, carInsurance, dues, communicationCost, subscribeFee) " +
                "values (:userId, :houseLoanInterest, " +
                ":carLent, :carInsurance, :dues, :communicationCost, :subscribeFee)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(fixedDto);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        fixedExpenditureDebtInsert.update(sql, param, keyHolder);

        return fixedDto;
    }

    public List<ShowUserInfoDto> showAll() {
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


    public List<ShowUserInfoDto> findById(Long id) {
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

    private RowMapper<ShowUserInfoDto> userDtoRowMapper() {
        return BeanPropertyRowMapper.newInstance(ShowUserInfoDto.class);
    }
}
