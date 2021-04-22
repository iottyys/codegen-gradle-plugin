/**
 * Copyright © 2014-2019 Commerce Technologies, LLC.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.ttyys.micrc.sad.gradle.plugin.extension;

import io.ttyys.micrc.sad.gradle.plugin.common.Constants;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;

import javax.inject.Inject;

public class SchemaDesignExtension {
    private final Property<String> sourcePath;
    private final Property<String> destPath;

    @SuppressWarnings("UnstableApiUsage")
    @Inject
    public SchemaDesignExtension(ObjectFactory objects) {
        this.sourcePath = objects.property(String.class).convention(Constants.PROTOCOL_SOURCE_PATH_KEY);
        this.destPath = objects.property(String.class).convention(Constants.PROTOCOL_DEST_PATH_KEY);
    }

    public Property<String> getSourcePath() {
        return sourcePath;
    }

    public Property<String> getDestPath() {
        return destPath;
    }

    public void setSourcePath(String schemaDesignPath) {
        this.sourcePath.set(schemaDesignPath);
    }

    public void setDestPath(String destPath) {
        this.destPath.set(destPath);
    }
}
