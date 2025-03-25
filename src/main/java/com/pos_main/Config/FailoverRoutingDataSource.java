package com.pos_main.Config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Title: FailoverRoutingDataSource.java. Company: www.codearson.com |
 * Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Mar 25, 2025.
 * @version 1.0
 **/

public class FailoverRoutingDataSource extends AbstractRoutingDataSource {
	
	public FailoverRoutingDataSource(DataSource mainDataSource, DataSource mirrorDataSource) {
		setTargetDataSources(Map.of("main", mainDataSource, "mirror", mirrorDataSource));
		setDefaultTargetDataSource(mainDataSource);
		afterPropertiesSet();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		try {
			// Check if main database is available
			return "main";
		} catch (Exception e) {
			// If main fails, switch to mirror
			return "mirror";
		}
	}
}
