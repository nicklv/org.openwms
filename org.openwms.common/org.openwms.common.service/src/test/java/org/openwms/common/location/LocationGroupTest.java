/*
 * openwms.org, the Open Warehouse Management System.
 * Copyright (C) 2014 Heiko Scherrer
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as 
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software. If not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.common.location;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * A LocationGroupTest.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 1.0
 * @since 1.0
 */
public class LocationGroupTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public
    @Test
    void testConstructionWithNull() {
        thrown.expect(IllegalArgumentException.class);
        new LocationGroup(null);
    }

    public
    @Test
    void testConstructionWithEmpty() {
        thrown.expect(IllegalArgumentException.class);
        new LocationGroup("");
    }

    public
    @Test
    void testDefaultValues() {
        LocationGroup lg = new LocationGroup("Error zone");
        assertThat(lg.getName()).isEqualTo("Error zone");
        assertThat(lg.isLocationGroupCountingActive()).isTrue();
        assertThat(lg.getNoLocations()).isEqualTo(0);
        assertThat(lg.getGroupStateIn()).isEqualTo(LocationGroupState.AVAILABLE);
        assertThat(lg.isInfeedAllowed()).isTrue();
        assertThat(lg.isInfeedBlocked()).isFalse();
        assertThat(lg.getGroupStateOut()).isEqualTo(LocationGroupState.AVAILABLE);
        assertThat(lg.isOutfeedAllowed()).isTrue();
        assertThat(lg.isOutfeedBlocked()).isFalse();
        assertThat(lg.getMaxFillLevel()).isEqualTo(0);
        assertThat(lg.getLocationGroups()).hasSize(0);
        assertThat(lg.getLocations()).hasSize(0);
    }
}
