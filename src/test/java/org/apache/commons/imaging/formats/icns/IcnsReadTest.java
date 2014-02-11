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

package org.apache.commons.imaging.formats.icns;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.TestUtils;
import org.apache.commons.imaging.common.IImageMetadata;
import org.apache.commons.imaging.util.Debug;

public class IcnsReadTest extends IcnsBaseTest {

    public void test() throws Exception {
        Debug.debug("start");

        final List<File> images = getIcnsImages();
        for (int i = 0; i < images.size(); i++) {
            if (i % 10 == 0) {
                TestUtils.purgeMemory();
            }

            final File imageFile = images.get(i);
            Debug.debug("imageFile", imageFile);

            final IImageMetadata metadata = Imaging.getMetadata(imageFile);
            // assertNotNull(metadata);

            final Map<String, Object> params = new HashMap<String, Object>();
            final ImageInfo imageInfo = Imaging.getImageInfo(imageFile, params);
            assertNotNull(imageInfo);

            final BufferedImage image = Imaging.getBufferedImage(imageFile);
            assertNotNull(image);
        }
    }

}
