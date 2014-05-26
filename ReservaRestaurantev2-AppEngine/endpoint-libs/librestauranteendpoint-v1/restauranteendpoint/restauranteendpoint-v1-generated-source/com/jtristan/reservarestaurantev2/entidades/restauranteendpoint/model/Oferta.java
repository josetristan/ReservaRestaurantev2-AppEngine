/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-04-15 19:10:39 UTC)
 * on 2014-05-08 at 13:14:32 UTC 
 * Modify at your own risk.
 */

package com.jtristan.reservarestaurantev2.entidades.restauranteendpoint.model;

/**
 * Model definition for Oferta.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the restauranteendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Oferta extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String descripcion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> diasDisponibles;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String nombreRestaurante;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDescripcion() {
    return descripcion;
  }

  /**
   * @param descripcion descripcion or {@code null} for none
   */
  public Oferta setDescripcion(java.lang.String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getDiasDisponibles() {
    return diasDisponibles;
  }

  /**
   * @param diasDisponibles diasDisponibles or {@code null} for none
   */
  public Oferta setDiasDisponibles(java.util.List<java.lang.String> diasDisponibles) {
    this.diasDisponibles = diasDisponibles;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public Oferta setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNombreRestaurante() {
    return nombreRestaurante;
  }

  /**
   * @param nombreRestaurante nombreRestaurante or {@code null} for none
   */
  public Oferta setNombreRestaurante(java.lang.String nombreRestaurante) {
    this.nombreRestaurante = nombreRestaurante;
    return this;
  }

  @Override
  public Oferta set(String fieldName, Object value) {
    return (Oferta) super.set(fieldName, value);
  }

  @Override
  public Oferta clone() {
    return (Oferta) super.clone();
  }

}