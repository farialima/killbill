/*
 * Copyright 2010-2013 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.killbill.billing.tenant.security;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha512Hash;

public class KillbillCredentialsMatcher {

    public static final String KILLBILL_TENANT_HASH_ITERATIONS_PROPERTY = "org.killbill.server.multitenant.hash_iterations";

    // See http://www.stormpath.com/blog/strong-password-hashing-apache-shiro and https://issues.apache.org/jira/browse/SHIRO-290
    public static final String HASH_ALGORITHM_NAME = Sha512Hash.ALGORITHM_NAME;
    public static final Integer HASH_ITERATIONS = Integer.parseInt(System.getProperty(KILLBILL_TENANT_HASH_ITERATIONS_PROPERTY, "200000"));

    private KillbillCredentialsMatcher() {}

    public static CredentialsMatcher getCredentialsMatcher() {
        // This needs to be in sync with DefaultTenantDao
        final HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(HASH_ALGORITHM_NAME);
        // base64 encoding, not hex
        credentialsMatcher.setStoredCredentialsHexEncoded(false);
        credentialsMatcher.setHashIterations(HASH_ITERATIONS);

        return credentialsMatcher;
    }
}
