package it.mulders.maven.asciilogger.phases;

import it.mulders.maven.asciilogger.AsciiArtGenerator;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.release.ReleaseResult;
import org.apache.maven.shared.release.config.ReleaseDescriptor;
import org.apache.maven.shared.release.env.ReleaseEnvironment;
import org.codehaus.plexus.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AsciiArtPrepareDoneLoggingPhaseTest {
    private static final String BANNER = "Banner";

    private AsciiArtGenerator asciiArtGenerator = mock(AsciiArtGenerator.class);
    private Logger logger = mock(Logger.class);

    private AsciiArtPrepareDoneLoggingPhase phase = new AsciiArtPrepareDoneLoggingPhase();

    @BeforeEach
    public void injectMocks() {
        phase.asciiArtGenerator = asciiArtGenerator;
        phase.enableLogging(logger);
        when(asciiArtGenerator.generate(anyString())).thenReturn(singletonList(BANNER));
    }

    @Test
    void execute_shouldAlwaysReturnSuccess() {
        // Arrange
        final ReleaseDescriptor descriptor = mock(ReleaseDescriptor.class);
        final ReleaseEnvironment environment = mock(ReleaseEnvironment.class);
        final List<MavenProject> projects = Collections.emptyList();

        // Act
        final ReleaseResult result = phase.execute(descriptor, environment, projects);

        // Assert
        assertThat(result.getResultCode()).isEqualTo(ReleaseResult.SUCCESS);
    }

    @Test
    void execute_shouldPrintBanner() {
        // Arrange
        final ReleaseDescriptor descriptor = mock(ReleaseDescriptor.class);
        final ReleaseEnvironment environment = mock(ReleaseEnvironment.class);
        final List<MavenProject> projects = Collections.emptyList();

        // Act
        final ReleaseResult result = phase.execute(descriptor, environment, projects);

        // Assert
        verify(logger).info(BANNER);
        assertThat(result.getOutput().split("\\n")).hasSizeGreaterThan(0);
    }

    @Test
    void simulate_shouldAlwaysReturnSuccess() {
        // Arrange
        final ReleaseDescriptor descriptor = mock(ReleaseDescriptor.class);
        final ReleaseEnvironment environment = mock(ReleaseEnvironment.class);
        final List<MavenProject> projects = Collections.emptyList();

        // Act
        final ReleaseResult result = phase.simulate(descriptor, environment, projects);

        // Assert
        assertThat(result.getResultCode()).isEqualTo(ReleaseResult.SUCCESS);
    }

    @Test
    void simulate_shouldNotPrintBanner() {
        // Arrange
        final ReleaseDescriptor descriptor = mock(ReleaseDescriptor.class);
        final ReleaseEnvironment environment = mock(ReleaseEnvironment.class);
        final List<MavenProject> projects = Collections.emptyList();

        // Act
        final ReleaseResult result = phase.simulate(descriptor, environment, projects);

        // Assert
        assertThat(result.getOutput().split("\\n")).containsOnly("[INFO] Not printing banner because dry-run");
    }
}