package com.tecnotree.mongo.demo.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecnotree.mongo.demo.dao.ConnectionDAO;
import com.tecnotree.mongo.demo.model.Subscriptions;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	ConnectionDAO connectionDAO;

	@GetMapping("/findActiveSubscription")
	public String getSubscriptions() {

		List<Subscriptions> subscriptions = null;
		try {

			subscriptions = connectionDAO.findPostpaidSyncSubscriptions();

			logger.info("size of subscription {}", subscriptions.size());

			for (Subscriptions subscription : subscriptions) {

				JSONObject jsonObect = new JSONObject(subscription);

				logger.info(" subscription Id {}", subscription.getId());
				logger.info(" subscription {}", jsonObect.toString());

			}

		} catch (Exception e) {

			logger.error("Error while fetching data {}", e);

		}
		return "success fully fetch active subscriptions";
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
