package com.amalgamatedservice.ticketservice.entity;

import java.io.Serializable;
import java.util.Comparator;

public class Seat implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	static final Comparator<Seat> BEST_SEAT_COMPARATOR = new Comparator<Seat>() {
		@Override
		public int compare(Seat o1, Seat o2) {
			int compareLevel = o1.getLevelId().compareTo(o2.getLevelId());
			if(compareLevel != 0) {
				return compareLevel;
			}
			int compareRow = o1.getRow().compareTo(o2.getRow());
			if(compareRow != 0) {
				return compareRow;
			}
			//TODO could be better (closest to center?)
			return o1.getNum().compareTo(o2.getNum());
		}
	};

	private final Integer levelId;
	private final Integer row;
	private final Integer num;

	public Seat(Integer levelId, Integer row, Integer num) {
		this.levelId = levelId;
		this.row = row;
		this.num = num;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public Integer getRow() {
		return row;
	}

	public Integer getNum() {
		return num;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((levelId == null) ? 0 : levelId.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		result = prime * result + ((row == null) ? 0 : row.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		if (levelId == null) {
			if (other.levelId != null)
				return false;
		} else if (!levelId.equals(other.levelId))
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (row == null) {
			if (other.row != null)
				return false;
		} else if (!row.equals(other.row))
			return false;
		return true;
	}

}
