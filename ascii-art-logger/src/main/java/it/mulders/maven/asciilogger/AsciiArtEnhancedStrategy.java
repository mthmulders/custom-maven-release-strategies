package it.mulders.maven.asciilogger;

import org.apache.maven.shared.release.strategy.Strategy;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Named("ascii-art-enhanced-strategy")
@Singleton
public class AsciiArtEnhancedStrategy implements Strategy {
    @Named("default")
    @Inject
    Strategy defaultStrategy;

    @Override
    public List<String> getPreparePhases() {
        final List<String> phases = new ArrayList<>(defaultStrategy.getPreparePhases());
        phases.add(phases.size() - 1, "log-prepare-done-ascii-art");
        return phases;
    }

    @Override
    public List<String> getPerformPhases() {
        final List<String> phases = new ArrayList<>(defaultStrategy.getPerformPhases());
        phases.add(phases.size() - 1, "log-perform-done-ascii-art");
        return phases;
    }

    @Override
    public List<String> getBranchPhases() {
        final List<String> phases = new ArrayList<>(defaultStrategy.getPerformPhases());
        phases.add(phases.size() - 1, "log-branch-done-ascii-art");
        return phases;
    }

    @Override
    public List<String> getRollbackPhases() {
        return defaultStrategy.getRollbackPhases();
    }

    @Override
    public List<String> getUpdateVersionsPhases() {
        final List<String> phases = new ArrayList<>(defaultStrategy.getPerformPhases());
        phases.add(phases.size() - 1, "log-updateversions-done-ascii-art");
        return phases;
    }
}
