package com.noname.domain;

import ga.domain.AbstractEntity;
import ga.domain.GenericEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Ivan Khalopik
 */
@MappedSuperclass
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BaseEntity extends AbstractEntity<Long> implements GenericEntity {

	@Id
	@GeneratedValue(generator = "common")
	@GenericGenerator(name = "common", strategy = "increment")
	@Column(name = "ID", unique = true)
	private Long id;

	@Version
	@Column(name = "VERSION")
	private Integer version;

	public Long getId() {
		return id;
	}
}
