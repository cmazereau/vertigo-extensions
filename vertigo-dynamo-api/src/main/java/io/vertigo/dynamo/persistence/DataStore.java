/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
 * KleeGroup, Centre d'affaire la Boursidiere - BP 159 - 92357 Le Plessis Robinson Cedex - France
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vertigo.dynamo.persistence;

import io.vertigo.dynamo.domain.metamodel.DtDefinition;
import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.dynamo.domain.model.DtListURI;
import io.vertigo.dynamo.domain.model.DtObject;
import io.vertigo.dynamo.domain.model.URI;

/**
 * Objet permettant de gérer les accès aux systèmes de stockage.
 * 
 * @author pchretien
 */
public interface DataStore {
	/**
	 * Nombre d'éléments.
	 * @param dtDefinition Définition de DT
	 * @return Nombre d'éléments.
	 */
	int count(final DtDefinition dtDefinition);

	/**
	 * Récupération d'une liste correspondant à des criteres.
	 * @param dtDefinition Définition de DT
	 * @param criteria Critere de recherche
	 * @param maxRows Nombre de résultats max.
	 * @return DtList<D> Liste correspondant à la recherche
	 * @param <D> Type de l'objet
	 * @deprecated Utiliser loadList(DtListURI uri)
	 */
	@Deprecated
	<D extends DtObject> DtList<D> loadList(final DtDefinition dtDefinition, final Criteria<D> criteria, final Integer maxRows);

	/**
	 * Récupération de l'objet correspondant à l'URI fournie.
	 * Peut-être null.
	 *
	 * @param uri URI de l'objet à charger
	 * @return D correspondant à l'URI fournie.
	 * @param <D> Type de l'objet
	 */
	<D extends DtObject> D load(URI<D> uri);

	/**
	 * Récupération d'une liste correspondant à l'URI fournie.
	 * NOT NULL
	 *
	 * @param uri URI de la collection à charger
	 * @return DtList<D> Liste correspondant à l'URI fournie
	 * @param <D> Type de l'objet
	 */
	<D extends DtObject> DtList<D> loadList(DtListURI uri);

	//==========================================================================
	//=============================== Ecriture =================================
	//==========================================================================
	/**
	* Sauvegarde d'un objet.
	* La stratégie de création ou de modification est déduite de l'état de l'objet java,
	* et notamment de la présence ou non d'une URI.
	*
	* Si l'objet possède une URI  : mode modification
	* Si l'objet ne possède pas d'URI : mode création
	*
	* @param dto Objet à sauvegarder (création ou modification)
	*/
	void put(DtObject dto);

	/**
	 * Suppression d'un objet.
	 * @param uri URI de l'objet à supprimmer
	 */
	void remove(URI<? extends DtObject> uri);

	/**
	* Sauvegarde d'un objet à l'identique.
	* La stratégie de création ou de modification est déduite de l'état de l'objet en base,
	*
	* Si l'objet est présent en base : mode modification
	* Si l'objet n'est pas présent en base : mode création
	*
	* @param dto Objet à sauvegarder (création ou modification)
	*/
	void merge(DtObject dto);
}
