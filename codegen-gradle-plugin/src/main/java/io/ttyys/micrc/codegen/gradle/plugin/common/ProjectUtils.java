package io.ttyys.micrc.codegen.gradle.plugin.common;

import org.gradle.api.Project;
import org.gradle.api.file.Directory;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.provider.Provider;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;

import java.io.File;

public class ProjectUtils {
    public static SourceSetContainer getSourceSets(Project project) {
        return project.getConvention().getPlugin(JavaPluginConvention.class).getSourceSets();
    }

    public static SourceSet getMainSourceSet(Project project) {
        return getSourceSets(project).getByName(SourceSet.MAIN_SOURCE_SET_NAME);
    }

    public static SourceSet getTestSourceSet(Project project) {
        return getSourceSets(project).getByName(SourceSet.TEST_SOURCE_SET_NAME);
    }

    public static String getAvroSourceDirPath(SourceSet sourceSet) {
        return String.format("src/%s/avro", sourceSet.getName());
    }

    public static File getAvroSourceDir(Project project, SourceSet sourceSet) {
        return project.file(getAvroSourceDirPath(sourceSet));
    }

    private static String getGeneratedOutputDirPath(SourceSet sourceSet, String extension) {
        return String.format("generated-%s-avro-%s", sourceSet.getName(), extension);
    }

    public static Provider<Directory> getGeneratedOutputDir(Project project, SourceSet sourceSet, String extension) {
        return project.getLayout().getBuildDirectory().dir(getGeneratedOutputDirPath(sourceSet, extension));
    }
}
