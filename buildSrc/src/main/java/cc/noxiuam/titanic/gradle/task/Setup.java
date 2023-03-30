package cc.noxiuam.titanic.gradle.task;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Comparator;

import org.gradle.api.Action;
import org.gradle.api.Task;
import org.gradle.api.logging.Logger;
import org.mcphackers.mcp.MCP;
import org.mcphackers.mcp.Options;
import org.mcphackers.mcp.tasks.Task.Side;
import org.mcphackers.mcp.tasks.mode.TaskMode;
import org.mcphackers.mcp.tasks.mode.TaskParameter;
import org.mcphackers.mcp.tools.versions.json.Version;

import cc.noxiuam.titanic.gradle.TitanicGradlePlugin;
import cc.noxiuam.titanic.gradle.Util;
import codechicken.diffpatch.PatchOperation;
import codechicken.diffpatch.util.PatchMode;

public class Setup extends MCP implements Action<Task> {

    private final TitanicGradlePlugin plugin;
    private final Logger logger;
    private final Options options = new Options();
    private Version version;

    public Setup(TitanicGradlePlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getProject().getLogger();
        options.setParameter(TaskParameter.SETUP_VERSION, plugin.getVersion());
    }

    @Override
    public void execute(Task task) {
        try {
            log("Cleaning...");
            Util.removeDirectory(getWorkingDir());

            log("Downloading...");
            performTask(TaskMode.SETUP, Side.CLIENT);

            log("Decompiling...");
            performTask(TaskMode.DECOMPILE, Side.CLIENT);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void clearProgressBars() {}

    @Override
    public Version getCurrentVersion() {
        return version;
    }

    @Override
    public Options getOptions() {
        return options;
    }

    @Override
    public Path getWorkingDir() {
        return plugin.getMcp();
    }

    @Override
    public String inputString(String arg0, String arg1) {
        throw new UnsupportedOperationException("Input was requested");
    }

    @Override
    public void log(String msg) {
        logger.quiet(msg);
    }

    @Override
    public void setActive(boolean active) {}

    @Override
    public void setCurrentVersion(Version version) {
        this.version = version;
    }

    @Override
    public void setProgress(int arg0, String arg1) {}

    @Override
    public void setProgress(int arg0, int arg1) {}

    @Override
    public void setProgressBars(List<org.mcphackers.mcp.tasks.Task> arg0, TaskMode arg1) {}

    @Override
    public void showMessage(String title, String msg, int arg2) {
        switch (arg2) {
            case 0:
                logger.quiet(msg);
                break;
            case 1:
                logger.warn(msg);
                break;
            case 2:
                logger.error(msg);
                break;
        }
    }

    @Override
    public void showMessage(String title, String msg, Throwable error) {
        logger.error(title, msg, error);
    }

    @Override
    public boolean updateDialogue(String changelog, String version) {
        logger.warn("Outdated version. Update to {} available!", version);
        return false;
    }

    @Override
    public boolean yesNoInput(String title, String msg) {
        throw new UnsupportedOperationException("Input was requested");
    }
}
