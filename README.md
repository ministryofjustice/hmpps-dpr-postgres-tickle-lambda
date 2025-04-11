# Ministry of Justice Digital Prison Reporting Postgres Tickle Lambdas

[![Ministry of Justice Repository Compliance Badge](https://github-community.service.justice.gov.uk/repository-standards/api/template-repository/badge)](https://github-community.service.justice.gov.uk/repository-standards/template-repository)

#### CODEOWNER

- Team : [hmpps-digital-prison-reporting](https://github.com/orgs/ministryofjustice/teams/hmpps-digital-prison-reporting)
- Email : digitalprisonreporting@digital.justice.gov.uk

## Overview

Code for the Postgres Tickle Lambda - a Lambda function that is used to periodically run `pg_logical_emit_message` so that
replication slots keep making progress. This is useful when AWS DMS is connected to a Postgres read replica but no 
changes occur in the tables monitored by DMS for long periods. Without periodically running the 'tickle', i.e. 
`pg_logical_emit_message`, the read replica transaction log would keep using more and more disk space which would be 
unable to be released. 

## Local Development

This project uses gradle which is bundled with the repository and also makes use
of

- [jacoco](https://docs.gradle.org/current/userguide/jacoco_plugin.html) - for test coverage reports

### Packaging

This project makes use of the [shadow jar plugin](https://github.com/johnrengelman/shadow)
which takes care of creating a jar containing all dependencies.

The plugin adds the suffix `-all` to the jar file name e.g.

```
    dpr-postgres-tickle-lambda-1.0-SNAPSHOT-all.jar
```

### Running a job

First, build the jar locally

```
    ./gradlew clean shadowJar
```

and then execute your lambda by specifying the fully qualified classname e.g.

```
    java -cp ./build/libs/dpr-postgres-tickle-lambda-1.0-SNAPSHOT-all.jar uk.gov.justice.digital.Placeholder
```

ensuring that your lambda class has a handleRequest method that can be executed.

## Testing

> **Note** - test coverage reports are enabled by default and after running the
> tests the report will be written to build/reports/jacoco/test/html

### Unit Tests

The unit tests use JUnit5 and Mockito where appropriate. Use the following to
run the tests.

```
    ./gradlew clean test
    ./gradlew clean check
```

### Branch Naming

- Use a JIRA ticket number where available
- Otherwise a short descriptive name is acceptable

### Commit Messages

- Prefix any commit messages with the JIRA ticket number where available
- Otherwise use the prefix `NOJIRA`

### Pull Requests

- Reference or link any relevant JIRA tickets in the pull request notes
- At least one approval is required before a PR can be merged
