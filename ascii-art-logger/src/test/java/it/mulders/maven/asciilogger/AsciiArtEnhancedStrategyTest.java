package it.mulders.maven.asciilogger;

import org.apache.maven.shared.release.strategy.Strategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AsciiArtEnhancedStrategyTest {
    private AsciiArtEnhancedStrategy strategy = new AsciiArtEnhancedStrategy();

    @BeforeEach
    void injectDefaultStrategyMock() {
        Strategy defaultStrategy = mock(Strategy.class);
        when(defaultStrategy.getBranchPhases()).thenReturn(singletonList("example"));
        when(defaultStrategy.getPerformPhases()).thenReturn(singletonList("example"));
        when(defaultStrategy.getPreparePhases()).thenReturn(singletonList("example"));
        when(defaultStrategy.getRollbackPhases()).thenReturn(singletonList("example"));
        when(defaultStrategy.getRollbackPhases()).thenReturn(singletonList("example"));
        strategy.defaultStrategy = defaultStrategy;
    }

    @Test
    void getPreparePhases_shouldIncludeAsciiArtPhase() {
        assertThat(strategy.getPreparePhases()).hasSize(2);
        assertThat(strategy.getPreparePhases()).contains("log-prepare-done-ascii-art");
    }

    @Test
    void getPerformPhases_shouldIncludeAsciiArtPhase() {
        assertThat(strategy.getPerformPhases()).hasSize(2);
        assertThat(strategy.getPerformPhases()).contains("log-perform-done-ascii-art");
    }

    @Test
    void getBranchPhases_shouldIncludeAsciiArtPhase() {
        assertThat(strategy.getBranchPhases()).hasSize(2);
        assertThat(strategy.getBranchPhases()).contains("log-branch-done-ascii-art");
    }

    @Test
    void getRollbackPhases_shouldIncludeAsciiArtPhase() {
        assertThat(strategy.getRollbackPhases()).hasSize(1);
        assertThat(strategy.getRollbackPhases()).noneMatch(p -> p.contains("ascii-art"));
    }

    @Test
    void getUpdateVersionsPhases_shouldIncludeAsciiArtPhase() {
        assertThat(strategy.getUpdateVersionsPhases()).hasSize(2);
        assertThat(strategy.getUpdateVersionsPhases()).contains("log-updateversions-done-ascii-art");
    }
}