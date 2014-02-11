/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.imaging.formats.tiff;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.imaging.FormatCompliance;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.formats.tiff.constants.AllTagConstants;
import org.apache.commons.imaging.formats.tiff.write.TiffImageWriterLossy;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputDirectory;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;

public class TiffReadWriteTagsTest extends TiffBaseTest {
    public void testReadWriteTags() throws ImageWriteException, ImageReadException, IOException {
        String description = "A pretty picture";
        short page = 1;
        RationalNumber twoThirds = new RationalNumber(2, 3);
        int t4Options = 0;
        int width = 10;
        short height = 10;
        String area = "A good area";
        float widthRes = 2.2f;
        double geoDoubleParams = -8.4;
        
        TiffOutputSet set = new TiffOutputSet();
        TiffOutputDirectory dir = set.getOrCreateRootDirectory();
        dir.add(AllTagConstants.TIFF_TAG_IMAGE_DESCRIPTION, description);
        dir.add(AllTagConstants.TIFF_TAG_PAGE_NUMBER, page, page);
        dir.add(AllTagConstants.TIFF_TAG_YRESOLUTION, twoThirds);
        dir.add(AllTagConstants.TIFF_TAG_T4_OPTIONS, t4Options);
        dir.add(AllTagConstants.TIFF_TAG_IMAGE_WIDTH, width);
        dir.add(AllTagConstants.TIFF_TAG_IMAGE_LENGTH, new short[] { height });
        dir.add(AllTagConstants.GPS_TAG_GPS_AREA_INFORMATION, area);
        dir.add(AllTagConstants.EXIF_TAG_WIDTH_RESOLUTION, widthRes);
        dir.add(AllTagConstants.EXIF_TAG_GEO_DOUBLE_PARAMS_TAG, geoDoubleParams);
        
        TiffImageWriterLossy writer = new TiffImageWriterLossy();
        ByteArrayOutputStream tiff = new ByteArrayOutputStream();
        writer.write(tiff, set);
        
        TiffReader reader = new TiffReader(true);
        Map<String, Object> params = new TreeMap<String, Object>();
        FormatCompliance formatCompliance = new FormatCompliance("");
        TiffContents contents = reader.readFirstDirectory(new ByteSourceArray(tiff.toByteArray()), params, true, formatCompliance);
        TiffDirectory rootDir = contents.directories.get(0);
        assertEquals(description, rootDir.getSingleFieldValue(AllTagConstants.TIFF_TAG_IMAGE_DESCRIPTION));
        assertEquals(page, rootDir.getFieldValue(AllTagConstants.TIFF_TAG_PAGE_NUMBER, true)[0]);
        RationalNumber yRes = rootDir.getSingleFieldValue(AllTagConstants.TIFF_TAG_YRESOLUTION);
        assertEquals(twoThirds.numerator, yRes.numerator);
        assertEquals(twoThirds.divisor, yRes.divisor);
        assertEquals(t4Options, rootDir.getSingleFieldValue(AllTagConstants.TIFF_TAG_T4_OPTIONS));
        assertEquals(width, rootDir.getSingleFieldValue(AllTagConstants.TIFF_TAG_IMAGE_WIDTH));
        assertEquals(width, rootDir.getSingleFieldValue(AllTagConstants.TIFF_TAG_IMAGE_LENGTH));
        assertEquals(area, rootDir.getFieldValue(AllTagConstants.GPS_TAG_GPS_AREA_INFORMATION, true));
        assertEquals(widthRes, rootDir.getSingleFieldValue(AllTagConstants.EXIF_TAG_WIDTH_RESOLUTION));
        assertEquals(geoDoubleParams, rootDir.getSingleFieldValue(AllTagConstants.EXIF_TAG_GEO_DOUBLE_PARAMS_TAG));
    }
}
