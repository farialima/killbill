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

package org.killbill.billing.junction.glue;

import com.google.inject.AbstractModule;
import org.killbill.billing.glue.JunctionModule;
import org.killbill.billing.junction.plumbing.billing.BlockingCalculator;
import org.killbill.billing.junction.plumbing.billing.DefaultInternalBillingApi;
import org.killbill.billing.junction.BillingInternalApi;
import org.skife.config.ConfigSource;

public class DefaultJunctionModule extends AbstractModule implements JunctionModule {

    protected final ConfigSource configSource;

    public DefaultJunctionModule(final ConfigSource configSource) {
        this.configSource = configSource;
    }

    @Override
    protected void configure() {
        installBillingApi();
        installBlockingCalculator();
    }

    @Override
    public void installBillingApi() {
        bind(BillingInternalApi.class).to(DefaultInternalBillingApi.class).asEagerSingleton();
    }


    public void installBlockingCalculator() {
        bind(BlockingCalculator.class).asEagerSingleton();
    }

}
