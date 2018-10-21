/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2018, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
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
package io.vertigo.workflow.domain.instance;

import io.vertigo.dynamo.domain.model.Entity;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.dynamo.domain.model.VAccessor;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
import io.vertigo.lang.Generated;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class WfDecision implements Entity {
	private static final long serialVersionUID = 1L;

	private Long wfeId;
	private String username;
	private Integer choice;
	private java.util.Date decisionDate;
	private String comments;

	@io.vertigo.dynamo.domain.stereotype.Association(
			name = "A_WFE_WFA",
			fkFieldName = "WFA_ID",
			primaryDtDefinitionName = "DT_WF_ACTIVITY",
			primaryIsNavigable = false,
			primaryRole = "WfActivity",
			primaryLabel = "WfActivity",
			primaryMultiplicity = "0..1",
			foreignDtDefinitionName = "DT_WF_DECISION",
			foreignIsNavigable = true,
			foreignRole = "WfDecision",
			foreignLabel = "WfDecision",
			foreignMultiplicity = "0..*")
	private final VAccessor<io.vertigo.workflow.domain.instance.WfActivity> wfaIdAccessor = new VAccessor<>(io.vertigo.workflow.domain.instance.WfActivity.class, "WfActivity");

	/** {@inheritDoc} */
	@Override
	public URI<WfDecision> getURI() {
		return URI.of(this);
	}

	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'Id Decision'.
	 * @return Long wfeId <b>Obligatoire</b>
	 */
	@Field(domain = "DO_WF_ID", type = "ID", required = true, label = "Id Decision")
	public Long getWfeId() {
		return wfeId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'Id Decision'.
	 * @param wfeId Long <b>Obligatoire</b>
	 */
	public void setWfeId(final Long wfeId) {
		this.wfeId = wfeId;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'username'.
	 * @return String username
	 */
	@Field(domain = "DO_WF_USER", label = "username")
	public String getUsername() {
		return username;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'username'.
	 * @param username String
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'choice'.
	 * @return Integer choice
	 */
	@Field(domain = "DO_WF_CHOICE", label = "choice")
	public Integer getChoice() {
		return choice;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'choice'.
	 * @param choice Integer
	 */
	public void setChoice(final Integer choice) {
		this.choice = choice;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'decision date'.
	 * @return Date decisionDate
	 */
	@Field(domain = "DO_WF_DATE", label = "decision date")
	public java.util.Date getDecisionDate() {
		return decisionDate;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'decision date'.
	 * @param decisionDate Date
	 */
	public void setDecisionDate(final java.util.Date decisionDate) {
		this.decisionDate = decisionDate;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'comments'.
	 * @return String comments
	 */
	@Field(domain = "DO_WF_COMMENTS", label = "comments")
	public String getComments() {
		return comments;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'comments'.
	 * @param comments String
	 */
	public void setComments(final String comments) {
		this.comments = comments;
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'WfActivity'.
	 * @return Long wfaId
	 */
	@Field(domain = "DO_WF_ID", type = "FOREIGN_KEY", label = "WfActivity")
	public Long getWfaId() {
		return (Long) wfaIdAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'WfActivity'.
	 * @param wfaId Long
	 */
	public void setWfaId(final Long wfaId) {
		wfaIdAccessor.setId(wfaId);
	}

	/**
	 * Association : WfActivity.
	 * @return l'accesseur vers la propriété 'WfActivity'
	 */
	public VAccessor<io.vertigo.workflow.domain.instance.WfActivity> wfActivity() {
		return wfaIdAccessor;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
