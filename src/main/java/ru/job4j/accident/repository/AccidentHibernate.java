package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

@Repository
public class AccidentHibernate {
	private final SessionFactory sf;
	private final String ACCIDENT_TYPE_SELECT = "SELECT a FROM AccidentType a";

	private final String RULE_SELECT = "SELECT r FROM Rule r";

	public AccidentHibernate(SessionFactory sf) {
		this.sf = sf;
		this.insertingAccidentType();
		this.insertingRule();
	}

	private void insertingAccidentType() {
		if (this.getTypes().isEmpty()) {
			try (Session session = sf.openSession()) {
				session.save(AccidentType.of(1, "Две машины"));
				session.save(AccidentType.of(2, "Машина и человек"));
				session.save(AccidentType.of(3, "Машина и велосипед"));
			}
		}
	}

	private void insertingRule() {
		if (this.getRules().isEmpty()) {
			try (Session session = sf.openSession()) {
				session.save(Rule.of(1, "Статья. 1"));
				session.save(Rule.of(2, "Статья. 2"));
				session.save(Rule.of(3, "Статья. 3"));
			}
		}
	}

	public List<AccidentType> getTypes() {
		try (Session session = sf.openSession()) {
			TypedQuery<AccidentType> tquery = session.createQuery(this.ACCIDENT_TYPE_SELECT, AccidentType.class);
			return tquery.getResultList();
		}
	}

	public List<Rule> getRules() {
		try (Session session = sf.openSession()) {
			TypedQuery<Rule> tquery = session.createQuery(this.RULE_SELECT, Rule.class);
			return tquery.getResultList();
		}
	}

	public Accident findById(Integer id) {
		try (Session session = sf.openSession()) {
			return session.find(Accident.class, id);
		}
	}
	
	public Accident save(Accident accident, String[] ruleIds) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();
			Set<Rule> ruleSet = new HashSet<Rule>();
			for (String stringId : ruleIds) {
				Integer id = Integer.valueOf(stringId);
				Rule rule = session.find(Rule.class, id);
				ruleSet.add(rule);
			}
			accident.setRules(ruleSet);
			session.saveOrUpdate(accident);
			session.getTransaction().commit();
			return accident;
		}
	}

	public List<Accident> getAll() {
		try (Session session = sf.openSession()) {
			return session.createQuery("from Accident", Accident.class).list();
		}
	}
}
