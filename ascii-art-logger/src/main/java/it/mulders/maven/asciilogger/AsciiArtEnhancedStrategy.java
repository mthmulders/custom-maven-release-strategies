package it.mulders.maven.asciilogger;

import org.apache.maven.shared.release.strategy.Strategy;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import java.util.ArrayList;
import java.util.List;

@Component(role = Strategy.class, hint = "ASCII art enhanced strategy")
public class AsciiArtEnhancedStrategy implements Strategy {
    @Requirement(hint = "default")
    Strategy defaultStrategy;

    @Override
    public List<String> getPreparePhases() {
        final List<String> phases = new ArrayList<>(defaultStrategy.getPreparePhases());
        phases.add("log-prepare-done-ascii-art");
        return phases;
    }

    @Override
    public List<String> getPerformPhases() {
        final List<String> phases = new ArrayList<>(defaultStrategy.getPerformPhases());
        phases.add("log-perform-done-ascii-art");
        return phases;
    }

    @Override
    public List<String> getBranchPhases() {
        final List<String> phases = new ArrayList<>(defaultStrategy.getPerformPhases());
        phases.add("log-branch-done-ascii-art");
        return phases;
    }

    @Override
    public List<String> getRollbackPhases() {
        return defaultStrategy.getRollbackPhases();
    }

    @Override
    public List<String> getUpdateVersionsPhases() {
        final List<String> phases = new ArrayList<>(defaultStrategy.getPerformPhases());
        phases.add("log-updateversions-done-ascii-art");
        return phases;
    }
}
