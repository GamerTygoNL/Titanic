package cc.noxiuam.titanic.gradle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import cc.noxiuam.titanic.gradle.task.Diff;
import cc.noxiuam.titanic.gradle.task.Patch;
import cc.noxiuam.titanic.gradle.task.Setup;

public class TitanicGradlePlugin implements Plugin<Project> {

    private Project project;
    private String version;
    private Path mcp;
    private Path mcpSrc;
    private Path patches;
    private Path minecraftSrc;

    @Override
    public void apply(Project project) {
        if (this.project != null)
            throw new IllegalStateException("owo");

        this.project = project;

        version = project.property("minecraft_version").toString();

        Path projectDir = project.getProjectDir().toPath();
        mcp = projectDir.resolve("mcp");
        mcpSrc = mcp.resolve("minecraft/src");
        patches = projectDir.resolve("patches");
        minecraftSrc = projectDir.resolve("src/minecraft/java");

        if (!Files.isDirectory(mcp)) {
            try {
                Files.createDirectories(mcp);
            } catch (IOException error) {
                throw new IllegalStateException(error);
            }
        }

        project.task("setup", task -> {
            task.doLast(new Setup(this));
            task.finalizedBy("patch");
        });
        project.task("diff", task -> task.doLast(new Diff(this)));
        project.task("patch", task -> task.doLast(new Patch(this)));
    }

    public Project getProject() {
        return project;
    }

    public String getVersion() {
        return version;
    }

    public Path getMcp() {
        return mcp;
    }

    public Path getMcpSrc() {
        return mcpSrc;
    }

    public Path getPatches() {
        return patches;
    }

    public Path getMinecraftSrc() {
        return minecraftSrc;
    }
}
