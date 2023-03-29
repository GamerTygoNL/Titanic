package cc.noxiuam.titanic.gradle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class TitanicGradlePlugin implements Plugin<Project> {

    private Project project;
    private String version;
    private Path work;

    @Override
    public void apply(Project project) {
        if (this.project != null)
            throw new IllegalStateException("owo");
        this.project = project;

        version = project.property("minecraft_version").toString();
        work = project.getProjectDir().toPath().resolve("mcp");

        if (!Files.isDirectory(work)) {
            try {
                Files.createDirectories(work);
            } catch (IOException error) {
                throw new IllegalStateException(error);
            }
        }

        project.task("setup", task -> task.doLast(new Setup(this)));
    }

    public Project getProject() {
        return project;
    }

    public String getVersion() {
        return version;
    }

    public Path getWork() {
        return work;
    }
}
