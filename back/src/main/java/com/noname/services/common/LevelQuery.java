package com.noname.services.common;

import com.noname.domain.common.Level;
import com.noname.services.CodedEntityQuery;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public class LevelQuery extends CodedEntityQuery<Level, LevelQuery> implements LevelFilter {
	private Long rating;

	public LevelQuery() {
		super(Level.class);
	}

	public LevelQuery withRating(final Long rating) {
		this.rating = rating;
		return query();
	}

	@Override
	public Long getRating() {
		return rating;
	}
}
