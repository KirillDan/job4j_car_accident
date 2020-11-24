package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;

@Repository
public class AccidentJdbcTemplate {
	private final JdbcTemplate jdbc;
	private final String ACCIDENT_TYPE_INSERT = "INSERT INTO accident_type(id, name) VALUES (?, ?)";
	private final String ACCIDENT_TYPE_SELECT = "SELECT * FROM accident_type";
	private final String ACCIDENT_TYPE_SELECT_BY_ID = "SELECT * FROM accident_type WHERE id = ?";
	
	private final String RULE_INSERT = "INSERT INTO rule(id, name) VALUES (?, ?)";
	private final String RULE_SELECT = "SELECT * FROM rule";
	private final String RULE_SELECT_BY_ID = "SELECT * FROM rule WHERE id = ?";

	private final String ACCIDENT_FIND_ALL = "SELECT * FROM accident";
	private final String ACCIDENT_FIND_BY_ID = "SELECT * FROM accident WHERE id = ?";
	private final String ACCIDENT_INSERT ="INSERT INTO accident(name, text, address, accident_type_id) VALUES (?, ?, ?, ?)";
	private final String ACCIDENT_UPDATE = "UPDATE accident SET name = ?, text = ?, address = ?, accident_type_id = ? WHERE id = ?";

	private final String FK_ACCIDENT_RULE_INSERT = "INSERT INTO accident_rule(accident_id, rule_id) VALUES (?, ?)";
	private final String FK_ACCIDENT_RULE_DELETE = "DELETE FROM accident_rule WHERE accident_id = ?";
	private final String FK_ACCIDENT_RULE_SELECT_BY_ID = "SELECT * FROM accident_rule WHERE accident_id = ?";
	
	public AccidentJdbcTemplate(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.insertingAccidentType();
		this.insertingRule();
	}
	
	private void insertingAccidentType() {
		if (this.getTypes().isEmpty()) {
			this.jdbc.update(this.ACCIDENT_TYPE_INSERT, 1, "Две машины");
			this.jdbc.update(this.ACCIDENT_TYPE_INSERT, 2, "Машина и человек");
			this.jdbc.update(this.ACCIDENT_TYPE_INSERT, 3, "Машина и велосипед");
		}
	}
	
	private void insertingRule() {
		if (this.getRules().isEmpty()) {
			this.jdbc.update(this.RULE_INSERT, 1, "Статья. 1");
			this.jdbc.update(this.RULE_INSERT, 2, "Статья. 2");
			this.jdbc.update(this.RULE_INSERT, 3, "Статья. 3");
		}
	}
	
	public List<AccidentType> getTypes() {
		return this.jdbc.query(ACCIDENT_TYPE_SELECT, (rs, row) -> {
			AccidentType accidentType = new AccidentType();
			accidentType.setId(rs.getInt("id"));
			accidentType.setName(rs.getString("name"));
			return accidentType;
		});
	}
	
	public List<Rule> getRules() {
		return this.jdbc.query(RULE_SELECT, (rs, row) -> {
			Rule rule = new Rule();
			rule.setId(rs.getInt("id"));
			rule.setName(rs.getString("name"));
			return rule;
		});
	}

	public Accident findById(Integer id) {
		Object[] args = new Object[1];
		args[0] = id;
		return this.jdbc.query(this.ACCIDENT_FIND_BY_ID, args, (rs, row) -> {
			Accident accident = new Accident();
			int accidentId = rs.getInt("id");
			accident.setId(accidentId);
			accident.setName(rs.getString("name"));
			accident.setText(rs.getString("text"));
			accident.setAddress(rs.getString("address"));
			Integer accidentTypeId = rs.getInt("accident_type_id");
			Object[] argsType = new Object[1];
			argsType[0] = accidentTypeId;
			AccidentType accidentType = this.jdbc.query(this.ACCIDENT_TYPE_SELECT_BY_ID, argsType, (rsType, rowType) -> {
				AccidentType accidentTypeL = new AccidentType();
				accidentTypeL.setId(rsType.getInt("id"));
				accidentTypeL.setName(rsType.getString("name"));
				return accidentTypeL;
			}).get(0);
			accident.setType(accidentType);

			Object[] args2 = new Object[1];
			args2[0] = accidentId;
			List<Rule> rules = this.jdbc.query(this.FK_ACCIDENT_RULE_SELECT_BY_ID, args2, (rs2, row2) -> {
				Object[] args3 = new Object[1];
				args3[0] = rs2.getInt("rule_id");
				return this.jdbc.query(RULE_SELECT_BY_ID, args3, (rs3, row3) -> {
					Rule rule3 = new Rule();
					rule3.setId(rs3.getInt("id"));
					rule3.setName(rs3.getString("name"));
					return rule3;
				}).get(0);
			});
			accident.setRules(new HashSet<Rule>(rules));
			return accident;
		}).get(0);
	}

//	"INSERT INTO accident(name, text, address, accident_type_id) VALUES (?, ?, ?, ?)"
//	"UPDATE accident SET name = ?, text = ?, address = ?, accident_type_id = ? WHERE id = ?"
//	"INSERT INTO accident_rule(accident_id, rule_id) VALUES (?, ?)"
//	"DELETE FROM accident_rule WHERE accident_id = ?";
	public Accident save(Accident accident, String[] ruleIds) {
		if (accident.getId() == 0) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbc.update(connection -> {
		        PreparedStatement ps = connection
		                .prepareStatement(this.ACCIDENT_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		                ps.setString(1, accident.getName());
		                ps.setString(2, accident.getText());
		                ps.setString(3, accident.getAddress());
		                ps.setInt(4, accident.getType().getId());
		                return ps;
		              }, keyHolder);
			int accidentId = (int) keyHolder.getKeys().get("id");
			for (String strId : ruleIds) {
				Integer id = Integer.valueOf(strId);
				jdbc.update(this.FK_ACCIDENT_RULE_INSERT, accidentId, id);
			}
		} else {
			jdbc.update(this.ACCIDENT_UPDATE, accident.getName(), accident.getText(), 
					accident.getAddress(), accident.getType().getId(), accident.getId());
			jdbc.update(this.FK_ACCIDENT_RULE_DELETE, accident.getId());
			for (String strId : ruleIds) {
				Integer id = Integer.valueOf(strId);
				jdbc.update(this.FK_ACCIDENT_RULE_INSERT, accident.getId(), id);
			}
		}
		
		return accident;
	}

	public List<Accident> getAll() {
		return jdbc.query(this.ACCIDENT_FIND_ALL, (rs, row) -> {
			return this.findById(rs.getInt("id"));
		});
	}
}
