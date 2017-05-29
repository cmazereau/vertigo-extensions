/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2017, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
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
package io.vertigo.quarto.services.export;

import io.vertigo.core.component.Manager;
import io.vertigo.dynamo.file.model.VFile;
import io.vertigo.quarto.services.export.model.Export;

/**
 * Gestionnaire centralisé des éditions de données.
 * Le choix du type de report est fait par l'appelant qui fournit les paramètres adaptés à son besoin.
 *
 * @author pchretien, npiedeloup
 */
public interface ExportManager extends Manager {

	/**
	 * Create a file from a config.
	 * @param export Config of the export
	 * @return sFile
	 */
	VFile createExportFile(final Export export);

}
