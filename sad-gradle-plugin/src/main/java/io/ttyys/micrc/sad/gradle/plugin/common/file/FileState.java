/**
 * Copyright © 2015 Commerce Technologies, LLC.
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
package io.ttyys.micrc.sad.gradle.plugin.common.file;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

public class FileState implements Comparable<FileState> {
    private final File file;
    private final String path;
    private String errorMessage;
    private Set<String> duplicateTypeNames = new TreeSet<>();

    public FileState(File file, String path) {
        this.file = file;
        this.path = path;
    }

    public File getFile() {
        return file;
    }

    public Set<String> getDuplicateTypeNames() {
        return duplicateTypeNames;
    }

    public void clearError() {
        errorMessage = null;
    }

    public void setError(Throwable ex) {
        this.errorMessage = ex.getMessage();
    }

    public void addDuplicateTypeName(String typeName) {
        duplicateTypeNames.add(typeName);
    }

    public boolean containsDuplicateTypeName(String typeName) {
        return duplicateTypeNames.contains(typeName);
    }

    public String getPath() {
        return path;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public int compareTo(FileState o) {
        return path.compareTo(o.getPath());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileState fileState = (FileState) o;
        return path.equals(fileState.path);
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }
}
