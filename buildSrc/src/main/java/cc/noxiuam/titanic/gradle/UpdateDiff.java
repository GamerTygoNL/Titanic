package cc.noxiuam.titanic.gradle;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.gradle.api.Action;
import org.gradle.api.Task;

import codechicken.diffpatch.DiffOperation;

public class UpdateDiff implements Action<Task> {

    private TitanicGradlePlugin plugin;

    public UpdateDiff(TitanicGradlePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Task task) {
        try {
            Util.removeDirectory(plugin.getPatches());
            boolean result = DiffOperation.builder()
                    .aPath(plugin.getMcpSrc())
                    .bPath(plugin.getMinecraftSrc())
                    .aPrefix(null)
                    .bPrefix(null)
                    .outputPath(plugin.getPatches())
                    .build()
                    .doDiff();
            if (!result) {
                plugin.getProject().getLogger().warn("No differences found");
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
