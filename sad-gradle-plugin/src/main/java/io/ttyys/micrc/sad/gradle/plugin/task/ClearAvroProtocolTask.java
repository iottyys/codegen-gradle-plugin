/**
 * Copyright © 2013-2019 Commerce Technologies, LLC.
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
package io.ttyys.micrc.sad.gradle.plugin.task;

import io.ttyys.micrc.sad.gradle.plugin.common.Constants;
import org.gradle.api.DefaultTask;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;

/**
 * 清理生成的目录
 */
public class ClearAvroProtocolTask extends DefaultTask {

    static final Logger LOGGER = Logging.getLogger(ClearAvroProtocolTask.class);

    private final Property<String> destPath;

    @Inject
    public ClearAvroProtocolTask(ObjectFactory objects) {
        super();
        this.destPath = objects.property(String.class).convention(Constants.PROTOCOL_DEST_PATH_KEY);
    }

    @TaskAction
    public void create() {
        LOGGER.info("Cleaning...");
        File targetDir = new File(getProject().getProjectDir(), destPath.get());
        if (targetDir.exists()) {
            deleteDir(targetDir);
            LOGGER.info("Deleted {}", targetDir.getAbsolutePath());
        } else {
            LOGGER.info("Skipped, not existing: {}", targetDir.getAbsolutePath());
        }
    }

    @Override
    public String getDescription() {
        return "Cleans generated directory.";
    }

    public boolean deleteDir(File self) {
        if (!self.exists()) {
            return true;
        } else if (!self.isDirectory()) {
            return false;
        } else {
            File[] files = self.listFiles();
            if (files == null) {
                return false;
            } else {
                boolean result = true;
                for (File file : files) {
                    if (file.isDirectory()) {
                        if (!deleteDir(file)) {
                            result = false;
                        }
                    } else if (!file.delete()) {
                        result = false;
                    }
                }

                if (!self.delete()) {
                    result = false;
                }

                return result;
            }
        }
    }

    public String getGroup() {
        return Constants.GROUP_SOURCE_GENERATION;
    }

    public void setDestPath(String destPath) {
        this.destPath.set(destPath);
    }

    public Property<String> getDestPath() {
        return destPath;
    }
}
