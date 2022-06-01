package com.pablojuice.framework.data.entities;

public interface BaseEntity<ID> {
	ID getId();

	Object[] toData();
}
