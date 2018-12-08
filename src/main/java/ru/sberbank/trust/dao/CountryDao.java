package ru.sberbank.trust.dao;

import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.sberbank.trust.Country;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;
import static ru.sberbank.trust.common.Java9BackPort.mapOf;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)

public final class CountryDao extends NamedParameterJdbcDaoSupport implements Dao<Integer, Country> {

	static String CODE_NAME_FIELD = "code_name";

	static RowMapper<Country> ROW_MAPPER = (rs, __) -> new Country(
			rs.getInt(Country.Fields.id),
			rs.getString(Country.Fields.name),
			rs.getString(CODE_NAME_FIELD)
	);

	//language=H2
	static String INSERT_SQL = "insert into Country (name, code_name) values (:name, :codeName)";//language=H2
	static String GET_ALL_SQL = "select id, name, code_name from Country";//language=H2
	static String GET_SQL = "select name, code_name from Country where id = :id";//language=H2
	static String GET_BY_NAME_SQL = "select id, code_name from Country where name = :name";//language=H2
	static String GET_BY_CODE_NAME_SQL = "select id, code_name from Country where code_name = :codeName";//language=H2
	static String UPDATE_SQL = "update Country set name = :name, code_name = :codeName where id = :id";//language=H2
	static String DELETE_SQL = "delete from Country where id = :id";

	public CountryDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public Optional<Country> getOne(Integer id) {
		assert getNamedParameterJdbcTemplate() != null;
		return Optional.ofNullable(
				getNamedParameterJdbcTemplate()
						.queryForObject(
								GET_SQL,
								mapOf(Country.Fields.id, id),
								ROW_MAPPER));
	}

	@Override
	public List<Country> findAll() {
		assert getJdbcTemplate() != null;
		return getJdbcTemplate().query(GET_ALL_SQL, ROW_MAPPER);
	}

	@Override
	@Contract(pure = true)
	public <S extends Country> S update(@NotNull S entity) {
		assert getNamedParameterJdbcTemplate() != null;
		getNamedParameterJdbcTemplate().update(UPDATE_SQL,
				mapOf(Country.Fields.name, entity.getName(),
						Country.Fields.codeName, entity.getGroupName(),
						Country.Fields.id, String.valueOf(entity.getId())));

		return entity;
	}

	@Override
	@Contract(pure = true)
	public <S extends Country> S insert(@NotNull S entity) {
		assert getNamedParameterJdbcTemplate() != null;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getNamedParameterJdbcTemplate().update(INSERT_SQL,
				new MapSqlParameterSource(
						mapOf(Country.Fields.name, entity.getName(),
								Country.Fields.codeName, entity.getGroupName())),
				keyHolder);

		//noinspection unchecked,ConstantConditions
		return (S) entity.setId(keyHolder.getKey().intValue());
	}

	@Contract(pure = true)
	@Override
	public Dao<Integer, Country> delete(Integer integer) {
		assert getNamedParameterJdbcTemplate() != null;
		getNamedParameterJdbcTemplate()
				.execute(DELETE_SQL,
						mapOf(Country.Fields.id, integer));
		return this;
	}
}
