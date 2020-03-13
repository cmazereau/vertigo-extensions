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
package io.vertigo.audit.ledger;

import java.math.BigInteger;

import io.vertigo.core.node.component.Manager;

public interface LedgerManager extends Manager {
	/**
	 * Sends data message on the ledger.
	 * @param data
	 * @return
	 */
	String sendData(String data);

	/**
	 * Sends data message on the ledger asynchronously with a callback.
	 * @param data
	 */
	void sendDataAsync(String data, Runnable callback);

	/**
	 * Gets the current balance of the provided address
	 * @param ledgerAddress
	 * @return
	 */
	BigInteger getWalletBalance(LedgerAddress ledgerAddress);

	/**
	 * Gets the current balance of the wallet
	 * @return
	 */
	BigInteger getMyWalletBalance();
}