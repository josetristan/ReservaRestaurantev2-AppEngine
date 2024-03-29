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

@Api(name = "usuarioendpoint", namespace = @ApiNamespace(ownerDomain = "jtristan.com", ownerName = "jtristan.com", packagePath = "reservarestaurantev2.entidades"))
public class UsuarioEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listUsuario")
	public CollectionResponse<Usuario> listUsuario(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		PersistenceManager mgr = null;
		Cursor cursor = null;
		List<Usuario> execute = null;

		try {
			mgr = getPersistenceManager();
			
			
			Query query = mgr.newQuery(Usuario.class);
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				HashMap<String, Object> extensionMap = new HashMap<String, Object>();
				extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
				query.setExtensions(extensionMap);
			}

			if (limit != null) {
				query.setRange(0, limit);
			}

			execute = (List<Usuario>) query.execute();
			cursor = JDOCursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Usuario obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Usuario> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getUsuario")
	public Usuario getUsuario(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		Usuario usuario = null;
		try {
			usuario = mgr.getObjectById(Usuario.class, id);
		} finally {
			mgr.close();
		}
		return usuario;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param usuario the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertUsuario")
	public Usuario insertUsuario(Usuario usuario) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (usuario.getId()!=null){
				if (containsUsuario(usuario)) {
					throw new EntityExistsException("Object already exists");
				}
			}
			mgr.makePersistent(usuario);
		} finally {
			mgr.close();
		}
		return usuario;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param usuario the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateUsuario")
	public Usuario updateUsuario(Usuario usuario) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (!containsUsuario(usuario)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.makePersistent(usuario);
		} finally {
			mgr.close();
		}
		return usuario;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeUsuario")
	public void removeUsuario(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			Usuario usuario = mgr.getObjectById(Usuario.class, id);
			mgr.deletePersistent(usuario);
		} finally {
			mgr.close();
		}
	}

	private boolean containsUsuario(Usuario usuario) {
		PersistenceManager mgr = getPersistenceManager();
		boolean contains = true;
		try {
			mgr.getObjectById(Usuario.class, usuario.getId());
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
