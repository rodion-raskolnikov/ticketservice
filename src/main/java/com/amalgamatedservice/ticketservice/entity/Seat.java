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
			//TODO needs to be different (closest to center)
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

}
