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

package org.apache.commons.imaging.formats.jpeg.exif;

import java.io.File;
import java.io.IOException;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.util.Debug;

public class MakerNoteFieldTest extends SpecificExifTagTest {
    // public MakerNoteFieldTest(String name)
    // {
    // super(name);
    // }

    @Override
    protected void checkField(final File imageFile, final TiffField field)
            throws IOException, ImageReadException, ImageWriteException {
        if (field.getTag() != ExifTagConstants.EXIF_TAG_MAKER_NOTE.tag) {
            // if (field.tag == EXIF_TAG_EXIF_OFFSET.tag)
            // ;
            // else
            return;
        }

        Debug.debug("imageFile: " + imageFile);
        Debug.debug("field: " + field);
        Debug.debug("type: " + field.getClass().getSimpleName());

        Debug.debug("field: " + field.getTag());
        Debug.debug("field: " + field.getTagInfo());
        Debug.debug("length: " + field.getCount());
        Debug.debug("fieldType: " + field.getFieldType());
        // Debug.debug("field", Debug.getType(field));
        Debug.debug();

        // try
        // {
        // Object userCommentValue = field.getValue();
        // Debug.debug("Text field value(" + field.tagInfo.name + ")",
        // userCommentValue);
        // // Debug.debug("userCommentValue", Debug.getType(userCommentValue));
        // }
        // catch (ImageReadException e)
        // {
        // Debug.debug("imageFile", imageFile);
        // Debug.debug(e);
        // }

    }

}
