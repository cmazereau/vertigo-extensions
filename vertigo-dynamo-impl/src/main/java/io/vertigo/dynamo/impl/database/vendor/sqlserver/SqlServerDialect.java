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
package io.vertigo.dynamo.impl.database.vendor.sqlserver;

import java.util.stream.Collectors;

import io.vertigo.dynamo.database.vendor.SqlDialect;
import io.vertigo.dynamo.domain.metamodel.DtDefinition;
import io.vertigo.dynamo.domain.metamodel.DtField;
import io.vertigo.lang.Assertion;

final class SqlServerDialect implements SqlDialect {

	@Override
	public String getConcatOperator() {
		return " + ";
	}

	/** {@inheritDoc} */
	@Override
	public String createInsertQuery(final DtDefinition dtDefinition, final String sequencePrefix, final String tableName) {
		return new StringBuilder()
				.append("insert into ").append(tableName).append(" ( ")
				.append(dtDefinition.getFields()
						.stream()
						.filter(dtField -> dtField.isPersistent() && dtField.getType() != DtField.FieldType.ID)
						.map(DtField::getName)
						.collect(Collectors.joining(", ")))
				.append(") values ( ")
				.append(dtDefinition.getFields()
						.stream()
						.filter(dtField -> dtField.isPersistent() && dtField.getType() != DtField.FieldType.ID)
						.map(dtField -> " #DTO." + dtField.getName() + '#')
						.collect(Collectors.joining(", ")))
				.append(") ")
				.toString();
	}

	/** {@inheritDoc} */
	@Override
	public void appendMaxRows(final StringBuilder request, final Integer maxRows) {
		Assertion.checkArgument(request.indexOf("select ") == 0, "request doit commencer par select");
		//-----
		request.insert("select ".length(), " top " + maxRows + ' ');
	}

	/** {@inheritDoc} */
	@Override
	public String createSelectForUpdateQuery(final String tableName, final String requestedFields, final String idFieldName) {
		return new StringBuilder()
				.append(" select ").append(requestedFields).append(" from ")
				.append(tableName)
				.append(" WITH (UPDLOCK, INDEX(PK_").append(tableName).append(")) ")
				.append(" where ").append(idFieldName).append(" = #").append(idFieldName).append('#')
				.toString();
	}

	/** {@inheritDoc} */
	@Override
	public boolean generatedKeys() {
		return true;
	}
}
