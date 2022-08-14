package hr.tvz.vuksan.hardwareapp.hardware;

import hr.tvz.vuksan.hardwareapp.Type;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
public class JdbcHardwareRepository implements HardwareRepository{

    private static final String SELECT_ALL =
            "SELECT CODE, NAME, PRICE, TYPE, AMOUNT FROM HARDWARE";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcHardwareRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("HARDWARE")
                .usingGeneratedKeyColumns("CODE");
    }

    @Override
    public List<Hardware> findALL() {
        return List.copyOf(jdbcTemplate.query(SELECT_ALL, this::mapRowToHardware));
    }

    @Override
    public Optional<Hardware> findByCode(long code) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(SELECT_ALL + " WHERE CODE = ?", this::mapRowToHardware, code)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> saveHardware(Hardware hardware) {
        try {
            hardware.setCode(saveHardwareDetails(hardware));
            return Optional.of(hardware);
        } catch (DuplicateKeyException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> updateHardware(long code, Hardware hardware) {
        int executed = jdbcTemplate.update(
                "UPDATE HARDWARE SET " + "NAME = ?, " + "PRICE = ?, " + "TYPE = ?, " + "AMOUNT = ? " + "WHERE CODE = ?",
                hardware.getName(), hardware.getPrice(), hardware.getType().toString(), hardware.getAmount(), code);
        if (executed > 0){
            hardware.setCode(code);
            return Optional.of(hardware);
        }else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteHardware(long code) {
        jdbcTemplate.update("DELETE FROM HARDWARE WHERE CODE = ?", code);
    }

    private Hardware mapRowToHardware(ResultSet resultSet, int rowNum) throws SQLException {
        return new Hardware(
                resultSet.getLong("CODE"),
                resultSet.getString("NAME"),
                resultSet.getDouble("PRICE"),
                Type.valueOf(resultSet.getString("TYPE")),
                resultSet.getInt("AMOUNT")
        );
    }

    private long saveHardwareDetails(Hardware hardware){
        Map<String, Object> values = new HashMap<>();

        values.put("NAME", hardware.getName());
        values.put("PRICE", hardware.getPrice());
        values.put("TYPE", hardware.getType());
        values.put("AMOUNT", hardware.getAmount());

        return simpleJdbcInsert.executeAndReturnKey(values).longValue();
    }
}
