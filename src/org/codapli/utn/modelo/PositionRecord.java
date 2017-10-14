package org.codapli.utn.modelo;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PositionRecord extends Thread {

	@Autowired
	PositionDAO positionDAO;

	private Boolean record = false;

	@Override
	public void run() {

	}

	public void saveNewPosition(String name, float ang) {
		if (!record)
			positionDAO.save(new Position(name, ang));

	}

	public void playRecord() {
		positionDAO.deleteAll();
		record = true;
	}

	public void stopRecord() {
		record = false;
	}

	public List<Position> getRecord() {
		return IteratorUtils.toList((Iterator<Position>) positionDAO.findAll());
	}

}
