package ru.itsjava.dao;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itsjava.model.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Repository
public class PetDaoImpl implements PetDaoJdbc {
    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public int count() {
        return jdbcOperations.getJdbcOperations().queryForObject("SELECT count(*) FROM PETS", Integer.class);
    }

    @Override
    public void insert(Pet pet) {
        final Map<String, Object> params = new HashMap<>();
        params.put("IDHOLDER", pet.getIdHolder());
        params.put("TYPE", pet.getTypePet());
        params.put("NAME", pet.getPetName());
        jdbcOperations.update("INSERT INTO PETS(IDHOLDER, TYPE, NAME) VALUES(:IDHOLDER, :TYPE, :NAME)", params);
    }

    @Override
    public Pet findById(long idHolder) {
        final Map<String, Object> params = new HashMap<>();
        params.put("IDHOLDER", idHolder);
        try {
            return jdbcOperations.queryForObject("SELECT IDHOLDER, TYPE, NAME FROM PETS WHERE IDHOLDER = :IDHOLDER", params, new PetMapper());
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void changeName(Pet pet, String newName) {
        final Map<String, Object> params = new HashMap<>();
        params.put("IDHOLDER", pet.getIdHolder());
        params.put("NAME", newName);
        jdbcOperations.update("UPDATE PETS SET NAME=:NAME where IDHOLDER = :IDHOLDER", params);
    }

    @Override
    public void deletePet(Pet pet) {
        final Map<String, Object> params = new HashMap<>();
        params.put("IDHOLDER", pet.getIdHolder());
        jdbcOperations.update("DELETE FROM PETS where IDHOLDER = :IDHOLDER", params);
    }

    private static class PetMapper implements RowMapper<Pet> {

        @Override
        public Pet mapRow(ResultSet resultSet, int i) throws SQLException {
            long idHolder = resultSet.getLong("IDHOLDER");
            String type = resultSet.getString("TYPE");
            String name = resultSet.getString("NAME");

            return new Pet(idHolder, type, name);
        }
    }
}
