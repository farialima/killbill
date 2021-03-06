/*
 * Copyright 2010-2014 Ning, Inc.
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

package org.killbill.billing.server.dao;

import java.util.Properties;

import org.killbill.billing.KillbillTestSuite;
import org.killbill.billing.server.config.DaoConfig;
import org.killbill.commons.embeddeddb.EmbeddedDB;
import org.killbill.commons.embeddeddb.EmbeddedDB.DBEngine;
import org.skife.config.ConfigurationObjectFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestEmbeddedDBFactory extends KillbillTestSuite {

    @Test(groups = "fast")
    public void testJdbcParser() throws Exception {
        final EmbeddedDB mysqlEmbeddedDb = EmbeddedDBFactory.get(createDaoConfig("jdbc:mysql://127.0.0.1:3306/killbill", "root", "root"));
        Assert.assertEquals(mysqlEmbeddedDb.getDBEngine(), DBEngine.MYSQL);
        checkEmbeddedDb(mysqlEmbeddedDb);

        final EmbeddedDB h2EmbeddedDb = EmbeddedDBFactory.get(createDaoConfig("jdbc:h2:file:killbill;MODE=MYSQL;DB_CLOSE_DELAY=-1;MVCC=true;DB_CLOSE_ON_EXIT=FALSE", "root", "root"));
        Assert.assertEquals(h2EmbeddedDb.getDBEngine(), DBEngine.H2);
        checkEmbeddedDb(h2EmbeddedDb);

        final EmbeddedDB genericEmbeddedDb = EmbeddedDBFactory.get(createDaoConfig("jdbc:derby://localhost:1527/killbill;collation=TERRITORY_BASED:PRIMARY", "root", "root"));
        Assert.assertEquals(genericEmbeddedDb.getDBEngine(), DBEngine.GENERIC);
        checkEmbeddedDb(genericEmbeddedDb);
    }

    private void checkEmbeddedDb(final EmbeddedDB embeddedDb) {
        Assert.assertEquals(embeddedDb.getDatabaseName(), "killbill");
        Assert.assertEquals(embeddedDb.getUsername(), "root");
        Assert.assertEquals(embeddedDb.getPassword(), "root");
    }

    private DaoConfig createDaoConfig(final String url, final String user, final String password) {
        final Properties properties = new Properties();
        properties.put("org.killbill.dao.url", url);
        properties.put("org.killbill.dao.user", user);
        properties.put("org.killbill.dao.password", password);
        return new ConfigurationObjectFactory(properties).build(DaoConfig.class);
    }
}
