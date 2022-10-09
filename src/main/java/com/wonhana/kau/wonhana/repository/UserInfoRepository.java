package com.wonhana.kau.wonhana.repository;

import com.wonhana.kau.wonhana.dto.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class UserInfoRepository {

    private final NamedParameterJdbcTemplate template;

    private final NamedParameterJdbcTemplate assetInsert;
    private final NamedParameterJdbcTemplate debtInsert;
    private final NamedParameterJdbcTemplate incomeInsert;
    private final NamedParameterJdbcTemplate flexibleExpenditureInsert;
    private final NamedParameterJdbcTemplate fixedExpenditureDebtInsert;

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
        String sql = "insert into FlexibleExpenditure (userExpenditureId, coffee, food, " +
                "snack, liquidNCigarette, necessityProduct, oil) " +
                "values (:userExpenditureId, :coffee, :food, " +
                ":snack, :liquidNCigarette, :necessityProduct, :oil)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(flexibleDto);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        flexibleExpenditureInsert.update(sql, param, keyHolder);

        return flexibleDto;
    }

    public FixedExpenditureDto saveFixedExpenditure(FixedExpenditureDto fixedDto) {
        String sql = "insert into FixedExpenditure (userExpenditureId, houseLoanInterest, " +
                "carLent, carInsurance, dues, communicationCost, subscribeFee) " +
                "values (:userExpenditureId, :houseLoanInterest, " +
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

    private RowMapper<ShowUserInfoDto> userDtoRowMapper() {
        return BeanPropertyRowMapper.newInstance(ShowUserInfoDto.class);
    }
}
