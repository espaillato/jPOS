/*
 * jPOS Project [http://jpos.org]
 * Copyright (C) 2000-2023 jPOS Software SRL
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

package org.jpos.emv.cryptogram;

import org.jpos.emv.IssuerApplicationData;
import org.jpos.tlv.TLVList;

import static org.jpos.emv.cryptogram.CryptogramDataBuilder.minimumSetOfDataElement;

/**
 * Visa CVN 18 - Data Builder
 * @author Rainer Reyes
 */
public class CVN18DataBuilder implements CryptogramDataBuilder {

    @Override
    public String getDefaultARPCRequest(boolean approved) {
        /* for success:
         * 00830000
         * Byte 6, bit 1: Issuer Approves Online Transaction
         * Byte 6, bits 7–8: Update Counters:
         * • 11 = Add transaction to offline counter
         */
        return approved ? "00830000" : "00000000";
    }

    @Override
    public String buildARQCRequest(TLVList data, IssuerApplicationData iad) {
        StringBuilder sb = new StringBuilder();
        minimumSetOfDataElement(data).stream().forEach(sb::append);
        sb.append(iad.toString());
        return sb.toString();
    }

    @Override
    public String buildARQCRequest_padded(TLVList data, IssuerApplicationData iad, PaddingMethod paddingMethod) {
        return paddingMethod.apply(buildARQCRequest(data, iad));
    }
}
