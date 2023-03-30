package cc.noxiuam.titanic.gradle.task;

import java.io.IOException;
import java.io.UncheckedIOException;

import org.gradle.api.Action;
import org.gradle.api.Task;

import cc.noxiuam.titanic.gradle.TitanicGradlePlugin;
import cc.noxiuam.titanic.gradle.Util;
import codechicken.diffpatch.DiffOperation;

public class Diff implements Action<Task> {

    private TitanicGradlePlugin plugin;

    public Diff(TitanicGradlePlugin plugin) {
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
