package ru.itsjava.dao;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itsjava.model.Mail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Repository
public class MailDaoImpl implements MailDaoJdbc {
    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public int count() {
        return jdbcOperations.getJdbcOperations().queryForObject("SELECT count(*) FROM MAILS", Integer.class);
    }

    @Override
    public void insert(Mail mail) {
        final Map<String, Object> params = new HashMap<>();
        params.put("IDHOLDER", mail.getIdHolder());
        params.put("MAIL", mail.getEmail());
        jdbcOperations.update("INSERT INTO MAILS(IDHOLDER, MAIL) VALUES(:IDHOLDER, :MAIL)", params);
    }

    @Override
    public Mail findById(long idHolder) throws DataAccessException {
        final Map<String, Object> params = new HashMap<>();
        params.put("IDHOLDER", idHolder);
        try {
            return jdbcOperations.queryForObject("SELECT IDHOLDER, MAIL FROM MAILS WHERE IDHOLDER = :IDHOLDER", params, new MailMapper());
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void changeMail(Mail mail, String newMail) {
        final Map<String, Object> params = new HashMap<>();
        params.put("IDHOLDER", mail.getIdHolder());
        params.put("MAIL", newMail);
        jdbcOperations.update("UPDATE MAILS SET MAIL=:MAIL where IDHOLDER = :IDHOLDER", params);
    }

    @Override
    public void deleteMail(Mail mail) {
        final Map<String, Object> params = new HashMap<>();
        params.put("IDHOLDER", mail.getIdHolder());
        jdbcOperations.update("DELETE FROM MAILS where IDHOLDER = :IDHOLDER", params);
    }

    private static class MailMapper implements RowMapper<Mail> {

        @Override
        public Mail mapRow(ResultSet resultSet, int i) throws SQLException {
            long idHolder = resultSet.getLong("IDHOLDER");
            String email = resultSet.getString("MAIL");

            return new Mail(idHolder, email);
        }
    }
}
