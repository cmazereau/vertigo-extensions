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
package io.vertigo.x.rules;

import java.util.List;

import io.vertigo.dynamo.domain.model.DtObject;
import io.vertigo.lang.Manager;
import io.vertigo.x.account.Account;
import io.vertigo.x.impl.rules.RuleConstants;

/**
 * @author xdurand
 */
public interface RuleManager extends Manager {


	/**
	 * Select accounts matching the selector for an activity
	 * @param idActivityDefinition Activity definition id
	 * @param item Business object
	 * @param constants constants
	 * @return a list of account
	 */
	List<Account> selectAccounts(final Long idActivityDefinition, final DtObject item, final RuleConstants constants);

	/**
	 * Validate a rule for an activity
	 * @param idActivityDefinition Activity definition id
	 * @param item Business object
	 * @param constants constants
	 * @return true is the rule is valid, false otherwise
	 */
	boolean isRuleValid(Long idActivityDefinition, DtObject item, final RuleConstants constants);

	/**
	 * Add a new rule
	 * @param ruleDefinition the rule to add
	 */
	void addRule(RuleDefinition ruleDefinition);

	/**
	 * Get the rules for an itemId
	 * @param itemId
	 * @return all the rules defined for the provided itemId
	 */
	List<RuleDefinition> getRulesForItemId(Long itemId);

	/**
	 * Remove a rule
	 * @param ruleDefinition rule to remove
	 */
	void removeRule(RuleDefinition ruleDefinition);

	/**
	 * Update a rule
	 * @param ruleDefinition
	 */
	void updateRule(RuleDefinition ruleDefinition);

	/**
	 * Add a new condition 
	 * @param ruleConditionDefinition condition to add
	 */
	void addCondition(RuleConditionDefinition ruleConditionDefinition);

	/**
	 * Remove a condition
	 * @param ruleConditionDefinition condition to remove
	 */
	void removeCondition(RuleConditionDefinition ruleConditionDefinition);

	/**
	 * Get all the conditions for a specified rule
	 * @param ruleId the rule Id
	 * @return all the conditions associated to the provided rule
	 */
	List<RuleConditionDefinition> getConditionsForRuleId(Long ruleId);

	/**
	 * Update a rule
	 * @param ruleConditionDefinition the rule to update
	 */
	void updateCondition(RuleConditionDefinition ruleConditionDefinition);

	/**
	 * Add a new selector
	 * @param selectorDefinition the selector to add
	 */
	void addSelector(SelectorDefinition selectorDefinition);

	/**
	 * Get the selectors for the specified item Id.
	 * @param itemId itemId
	 * @return the all the rules defined for the provided itemId
	 */
	List<SelectorDefinition> getSelectorsForItemId(Long itemId);

	/**
	 * Remove a selector
	 * @param selectorDefinition the selector to remove
	 */
	void removeSelector(SelectorDefinition selectorDefinition);

	/**
	 * Update a selector
	 * @param selectorDefinition the selector to update
	 */
	void updateSelector(SelectorDefinition selectorDefinition);

	/**
	 * Add a new filter
	 * @param ruleFilterDefinition the filter to add
	 */
	void addFilter(RuleFilterDefinition ruleFilterDefinition);

	/**
	 * Remove a filter
	 * @param ruleFilterDefinition the filter to remove
	 */
	void removeFilter(RuleFilterDefinition ruleFilterDefinition);

	/**
	 * Get filters for the selectors id
	 * @param selectorId the selector id
	 * @return the all the filters associated to the provided selector
	 */
	List<RuleFilterDefinition> getFiltersForSelectorId(Long selectorId);

	/**
	 * Update the provided Filter
	 * @param ruleFilterDefinition the filter to update 
	 */
	void updateFilter(RuleFilterDefinition ruleFilterDefinition);

	
	/**
	 * Define the constants for this key
	 * @param key the key
	 * @param ruleConstants constants to associate
	 */
	void addConstants(Long key, RuleConstants ruleConstants);
	
	/**
	 * Get the constants associated to a key
	 * @param key the key
	 * @return the constants defined for this key
	 */
	RuleConstants getConstants(Long key);

}