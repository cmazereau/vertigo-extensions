/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2019, vertigo-io, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
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
package io.vertigo.orchestra.services.execution;

import io.vertigo.app.config.ModuleConfig;
import io.vertigo.app.config.NodeConfig;
import io.vertigo.app.config.NodeConfigBuilder;
import io.vertigo.commons.CommonsFeatures;
import io.vertigo.core.param.Param;
import io.vertigo.core.plugins.resource.classpath.ClassPathResourceResolverPlugin;
import io.vertigo.core.plugins.resource.url.URLResourceResolverPlugin;
import io.vertigo.database.DatabaseFeatures;
import io.vertigo.database.impl.sql.vendor.h2.H2DataBase;
import io.vertigo.dynamo.DynamoFeatures;
import io.vertigo.orchestra.OrchestraFeatures;
import io.vertigo.orchestra.util.monitoring.MonitoringServices;
import io.vertigo.orchestra.util.monitoring.MonitoringServicesImpl;

public final class OrchestraNode1NodeConfig {

	public static NodeConfigBuilder createNodeConfigBuilder() {
		return NodeConfig.builder()
				.withNodeId("NodeTest2")
				.beginBoot()
				.withLocales("fr_FR")
				.addPlugin(ClassPathResourceResolverPlugin.class)
				.addPlugin(URLResourceResolverPlugin.class)
				.endBoot()
				.addModule(new CommonsFeatures()
						.withCache()
						.withMemoryCache()
						.withScript()
						.withJaninoScript()
						.build())
				.addModule(new DatabaseFeatures()
						.withSqlDataBase()
						.withC3p0(
								Param.of("name", "orchestra"),
								Param.of("dataBaseClass", H2DataBase.class.getName()),
								Param.of("jdbcDriver", org.h2.Driver.class.getName()),
								Param.of("jdbcUrl", "jdbc:h2:~/vertigo/orchestra;MVCC=FALSE;AUTO_SERVER=TRUE"))
						.build())
				.addModule(new DynamoFeatures()
						.withKVStore()
						.withDelayedMemoryKV(
								Param.of("collections", "tokens"),
								Param.of("timeToLiveSeconds", "120"))
						.withStore()
						.withSqlStore(
								Param.of("dataSpace", "orchestra"),
								Param.of("connectionName", "orchestra"),
								Param.of("sequencePrefix", "SEQ_"))
						.build())
				.addModule(new OrchestraFeatures()
						.withDataBase(Param.of("nodeName", "NodeTest2"), Param.of("daemonPeriodSeconds", "1"), Param.of("workersCount", "3"), Param.of("forecastDurationSeconds", "60"))
						.withMemory(Param.of("workersCount", "1"))
						.build())
				.addModule(ModuleConfig.builder("orchestra-test-node2")
						//---Services
						.addComponent(MonitoringServices.class, MonitoringServicesImpl.class)
						.build())
				.addInitializer(LocalExecutionProcessInitializer.class);
	}

	public static NodeConfig config() {
		// @formatter:off
		return createNodeConfigBuilder().build();
	}

}
