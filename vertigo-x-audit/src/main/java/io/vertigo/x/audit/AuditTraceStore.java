/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2016, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
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
package io.vertigo.x.audit;

import java.util.List;

/**
 * This class defines the storage of audit trails.
 * @author xdurand
 */
public interface AuditTraceStore {

	/**
	 * Get an audit trail.
	 * @param idAuditTrace the audit trail defined by its id.
	 * @return the
	 */
	AuditTrace readTrace(Long idAuditTrace);

	/**
	 * Save a new audit trail.
	 * Attention: The audit MUST NOT have an id.
	 * @param auditTrace the audit trail to save.
	 */
	void createTrace(AuditTrace auditTrace);

	/**
	 * Fetch all Audit Trace mathing the provided criteria
	 * @param atc
	 * @return the matching taces for the provided criteria
	 */
	List<AuditTrace> findTraceByCriteria(AuditTraceCriteria atc);

}