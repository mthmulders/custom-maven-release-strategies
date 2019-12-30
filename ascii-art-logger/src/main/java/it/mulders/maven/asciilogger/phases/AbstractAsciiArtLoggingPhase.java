package it.mulders.maven.asciilogger.phases;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import it.mulders.maven.asciilogger.AsciiArtGenerator;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.release.ReleaseExecutionException;
import org.apache.maven.shared.release.ReleaseFailureException;
import org.apache.maven.shared.release.ReleaseResult;
import org.apache.maven.shared.release.config.ReleaseDescriptor;
import org.apache.maven.shared.release.env.ReleaseEnvironment;
import org.apache.maven.shared.release.phase.AbstractReleasePhase;
import org.apache.maven.shared.release.phase.ReleasePhase;
import org.codehaus.plexus.component.annotations.Requirement;

import java.util.List;

import static org.apache.maven.shared.release.ReleaseResult.SUCCESS;

/**
 * Log a message in ASCII art style.
 * @author Maarten Mulders
 */
public abstract class AbstractAsciiArtLoggingPhase extends AbstractReleasePhase implements ReleasePhase {
    @Requirement
    AsciiArtGenerator asciiArtGenerator;

    String message;

    @Override
    public ReleaseResult execute(final ReleaseDescriptor releaseDescriptor,
                                 final ReleaseEnvironment releaseEnvironment,
                                 final List<MavenProject> list)
    {
        final ReleaseResult result = new ReleaseResult();
        result.setResultCode(SUCCESS);
        asciiArtGenerator.generate(this.message).forEach(line -> logInfo(result, line));
        return result;
    }

    @Override
    public ReleaseResult simulate(final ReleaseDescriptor releaseDescriptor,
                                  final ReleaseEnvironment releaseEnvironment,
                                  final List<MavenProject> list)
    {
        final ReleaseResult result = new ReleaseResult();
        result.setResultCode(SUCCESS);
        logInfo(result, "Not printing banner because dry-run");
        return result;
    }
}
