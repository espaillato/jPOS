/*
 * jPOS Project [http://jpos.org]
 * Copyright (C) 2000-2021 jPOS Software SRL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.jpos.core;

import org.jpos.core.annotation.Config;
import org.jpos.q2.Q2;
import org.jpos.q2.QFactory;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigAnnotationTest {
    @Test
    public void testConfig() throws ConfigurationException, IllegalAccessException {
        MyAutoConfigurable bean = new MyAutoConfigurable();
        Configuration cfg = new SimpleConfiguration();
        cfg.put("mystring", "My String");
        cfg.put("mylong", "10000");
        cfg.put("myint", "1000");
        QFactory.autoconfigure(bean, cfg);
        assertEquals("My String", bean.getMystring());
        assertEquals(1000, bean.getMyint());
        assertEquals(10000L, bean.getMylong());
    }

    public static class MyAutoConfigurable {
        @Config("mystring")
        private String mystring;

        @Config("myint")
        private int myint;

        @Config("mylong")
        private Long mylong;


        public String getMystring() {
            return mystring;
        }

        public int getMyint() {
            return myint;
        }

        public Long getMylong() {
            return mylong;
        }


        @Override
        public String toString() {
            return "MyAutoConfigurable{" +
              "mystring='" + mystring + '\'' +
              ", myint=" + myint +
              ", mylong=" + mylong +
              '}';
        }
    }

}