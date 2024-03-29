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

@Api(name = "ofertaendpoint", namespace = @ApiNamespace(ownerDomain = "jtristan.com", ownerName = "jtristan.com", packagePath = "reservarestaurantev2.entidades"))
public class OfertaEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listOferta")
	public CollectionResponse<Oferta> listOferta(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		PersistenceManager mgr = null;
		Cursor cursor = null;
		List<Oferta> execute = null;

		try {
			mgr = getPersistenceManager();
			Query query = mgr.newQuery(Oferta.class);
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				HashMap<String, Object> extensionMap = new HashMap<String, Object>();
				extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
				query.setExtensions(extensionMap);
			}

			if (limit != null) {
				query.setRange(0, limit);
			}

			execute = (List<Oferta>) query.execute();
			cursor = JDOCursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Oferta obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Oferta> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getOferta")
	public Oferta getOferta(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		Oferta oferta = null;
		try {
			oferta = mgr.getObjectById(Oferta.class, id);
		} finally {
			mgr.close();
		}
		return oferta;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param oferta the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertOferta")
	public Oferta insertOferta(Oferta oferta) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (containsOferta(oferta)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.makePersistent(oferta);
		} finally {
			mgr.close();
		}
		return oferta;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param oferta the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateOferta")
	public Oferta updateOferta(Oferta oferta) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (!containsOferta(oferta)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.makePersistent(oferta);
		} finally {
			mgr.close();
		}
		return oferta;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeOferta")
	public void removeOferta(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			Oferta oferta = mgr.getObjectById(Oferta.class, id);
			mgr.deletePersistent(oferta);
		} finally {
			mgr.close();
		}
	}

	private boolean containsOferta(Oferta oferta) {
		PersistenceManager mgr = getPersistenceManager();
		boolean contains = true;
		try {
			mgr.getObjectById(Oferta.class, oferta.getId());
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
