package mainpackage.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

@Component
public class NamedParamJdbcDaoImpl extends NamedParameterJdbcDaoSupport {

    public void fillTableFromCsv(String csvFilePath) {
        String tableName = "CUSTOMER";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

            while ((line = br.readLine()) != null) {
                
                String fieldValue = line.trim();

                String sql = "INSERT INTO " + tableName + " (NAMEANDSURNAME) VALUES (:fieldValue)";

                MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("fieldValue", fieldValue);

                jdbcTemplate.update(sql, paramSource);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteCustomersUsingCsv(String csvFilePath) {
        String tableName = "CUSTOMER";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

            while ((line = br.readLine()) != null) {
                String fieldValue = line.trim();

                String sql = "DELETE FROM " + tableName + " WHERE NAMEANDSURNAME = :fieldValue";

                MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("fieldValue", fieldValue);

                jdbcTemplate.update(sql, paramSource);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<String> retrieveNamesAndSurnames() {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    	String sql = "SELECT NAMEANDSURNAME FROM CUSTOMER";
        return jdbcTemplate.queryForList(sql, String.class);
    }
    
    public void dropCustomerTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "DROP TABLE CUSTOMER";
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.err.println("Error dropping customer table: " + e.getMessage());
        }
    }
    
    public void createCustomerTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "CREATE TABLE CUSTOMER (NAMEANDSURNAME VARCHAR(50))";
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.err.println("Error creating customer table: " + e.getMessage());
        }
    }
}