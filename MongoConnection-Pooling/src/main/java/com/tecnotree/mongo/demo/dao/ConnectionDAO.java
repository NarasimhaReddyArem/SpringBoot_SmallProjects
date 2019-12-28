package com.tecnotree.mongo.demo.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.tecnotree.mongo.demo.model.Subscriptions;

@Component
public class ConnectionDAO {

	private static final Logger logger = LoggerFactory.getLogger(ConnectionDAO.class);
	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Subscriptions> findPostpaidSyncSubscriptions() {

		Query searchQuery = new Query(
				Criteria.where("syncStatus.status").is("false").and("offering.activationDate").gte(getDate()));

		
		logger.info(" searchQuery {}",searchQuery);
		
		List<Subscriptions> listSubscriptions = mongoTemplate.find(searchQuery, Subscriptions.class);

		return listSubscriptions;

	}

	public Date getDate() {
		Date date = null;
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - 1);
		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR));
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		String dateString = dateFormat.format(cal.getTime());

		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return date;

	}
}
