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
package io.vertigo.ledger.services;

import java.math.BigInteger;

import io.vertigo.lang.Assertion;

/**
 *
 * @author xdurand
 *
 */
public final class LedgerTransaction {

	private final String hash;
	private final BigInteger nonce;
	private final String blockHash;
	private final BigInteger blockNumber;
	private final BigInteger transactionIndex;
	private final String from;
	private final String to;
	private final BigInteger value;
	private final String message;

	LedgerTransaction(
			final String hash,
			final BigInteger nonce,
			final String blockHash,
			final BigInteger blockNumber,
			final BigInteger transactionIndex,
			final String from,
			final String to,
			final BigInteger value,
			final String message) {
		Assertion.checkNotNull(hash);
		Assertion.checkNotNull(nonce);
		Assertion.checkNotNull(blockHash);
		Assertion.checkNotNull(blockNumber);
		Assertion.checkNotNull(transactionIndex);
		Assertion.checkNotNull(from);
		Assertion.checkNotNull(to);
		Assertion.checkNotNull(value);
		Assertion.checkNotNull(message);
		//-----
		this.hash = hash;
		this.nonce = nonce;
		this.blockHash = blockHash;
		this.blockNumber = blockNumber;
		this.transactionIndex = transactionIndex;
		this.from = from;
		this.to = to;
		this.value = value;
		this.message = message;
	}

	public static LedgerTransactionBuilder builder() {
		return new LedgerTransactionBuilder();
	}

	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @return the nonce
	 */
	public BigInteger getNonce() {
		return nonce;
	}

	/**
	 * @return the blockHash
	 */
	public String getBlockHash() {
		return blockHash;
	}

	/**
	 * @return the blockNumber
	 */
	public BigInteger getBlockNumber() {
		return blockNumber;
	}

	/**
	 * @return the transactionIndex
	 */
	public BigInteger getTransactionIndex() {
		return transactionIndex;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @return the value
	 */
	public BigInteger getValue() {
		return value;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
