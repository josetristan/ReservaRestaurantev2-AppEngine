package com.jtristan.reservarestaurantev2.entidades;

import com.jtristan.reservarestaurantev2.entidades.PMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@Api(name = "reservaendpoint", namespace = @ApiNamespace(ownerDomain = "jtristan.com", ownerName = "jtristan.com", packagePath = "reservarestaurantev2.entidades"))
public class ReservaEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listReserva")
	public CollectionResponse<Reserva> listReserva(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		PersistenceManager mgr = null;
		Cursor cursor = null;
		List<Reserva> execute = null;

		try {
			mgr = getPersistenceManager();
			Query query = mgr.newQuery(Reserva.class);
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				HashMap<String, Object> extensionMap = new HashMap<String, Object>();
				extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
				query.setExtensions(extensionMap);
			}

			if (limit != null) {
				query.setRange(0, limit);
			}

			execute = (List<Reserva>) query.execute();
			cursor = JDOCursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Reserva obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Reserva> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getReserva")
	public Reserva getReserva(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		Reserva reserva = null;
		try {
			reserva = mgr.getObjectById(Reserva.class, id);
		} finally {
			mgr.close();
		}
		return reserva;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param reserva the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertReserva")
	public Reserva insertReserva(Reserva reserva) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (reserva.getId()!=null){
				if (containsReserva(reserva)) {
					throw new EntityExistsException("Object already exists");
				}
			}
			mgr.makePersistent(reserva);
		} finally {
			mgr.close();
		}
		return reserva;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param reserva the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateReserva")
	public Reserva updateReserva(Reserva reserva) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (!containsReserva(reserva)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.makePersistent(reserva);
		} finally {
			mgr.close();
		}
		return reserva;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeReserva")
	public void removeReserva(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			Reserva reserva = mgr.getObjectById(Reserva.class, id);
			mgr.deletePersistent(reserva);
		} finally {
			mgr.close();
		}
	}

	private boolean containsReserva(Reserva reserva) {
		PersistenceManager mgr = getPersistenceManager();
		boolean contains = true;
		try {
			mgr.getObjectById(Reserva.class, reserva.getId());
		} catch (javax.jdo.JDOObjectNotFoundException ex) {
			contains = false;
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}

}
