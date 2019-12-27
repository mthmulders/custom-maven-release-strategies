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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AsciiArtUpdateVersionsDoneLoggingPhaseTest {
    private AsciiArtGenerator asciiArtGenerator = mock(AsciiArtGenerator.class);
    private Logger logger = mock(Logger.class);

    private AsciiArtUpdateVersionsDoneLoggingPhase phase = new AsciiArtUpdateVersionsDoneLoggingPhase();

    @BeforeEach
    public void injectMocks() {
        phase.asciiArtGenerator = asciiArtGenerator;
        phase.enableLogging(logger);
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

        final String message = "Banner";
        when(asciiArtGenerator.generate(anyString())).thenReturn(Collections.singletonList(message));

        // Act
        phase.execute(descriptor, environment, projects);

        // Assert
        verify(logger).info(message);
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
        phase.execute(descriptor, environment, projects);

        // Assert
        verify(logger, never()).info(anyString());
    }
}