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

package org.killbill.billing.tenant.glue;

import org.skife.config.ConfigSource;

import org.killbill.billing.tenant.api.DefaultTenantService;
import org.killbill.billing.tenant.api.TenantService;
import org.killbill.billing.tenant.api.TenantUserApi;
import org.killbill.billing.tenant.api.user.DefaultTenantUserApi;
import org.killbill.billing.tenant.dao.DefaultTenantDao;
import org.killbill.billing.tenant.dao.TenantDao;

import com.google.inject.AbstractModule;

public class TenantModule extends AbstractModule {

    protected final ConfigSource configSource;

    public TenantModule(final ConfigSource configSource) {
        this.configSource = configSource;
    }

    private void installConfig() {
    }

    protected void installTenantDao() {
        bind(TenantDao.class).to(DefaultTenantDao.class).asEagerSingleton();
    }

    protected void installTenantUserApi() {
        bind(TenantUserApi.class).to(DefaultTenantUserApi.class).asEagerSingleton();
    }

    private void installTenantService() {
        bind(TenantService.class).to(DefaultTenantService.class).asEagerSingleton();
    }

    @Override
    protected void configure() {
        installConfig();
        installTenantDao();
        installTenantService();
        installTenantUserApi();
    }
}
