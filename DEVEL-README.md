# jFairy - developers guide

## Releasing the new version

Until the [Gradle release plugin](https://github.com/townsfolk/gradle-release/) (or something similar) is used
the release process has to be performed manually.

### Prerequisites

1. Configure your Sonatype OSS Maven Repository credentials in ~/.m2/settings.xml as described in the
[manual](https://docs.sonatype.org/display/Repository/Sonatype+OSS+Maven+Repository+Usage+Guide#SonatypeOSSMavenRepositoryUsageGuide-Changesto%EF%BB%BF{{settings.xml}}).
2. Set ```signing.keyId``` and ```signing.secretKeyRingFile``` in ~/.gradle/gradle.properties.

### Releasing

1. Change project version in gradle.properties to the release version (without SNAPSHOT).
2. Make a commit.
3. Call ```./gradlew clean build uploadArchives --no-daemon``` and enter a passphrase for the configured key.
The artifacts should be built, signed and uploaded to the Sonatype OSS Maven Repository.
5. Change project version to the next development version (with -SNAPSHOT sufix).
6. Make a commit

If the version was approved (and moved to the Central Repository) a proper hash should be tagged and pushed to
the remote repo (origin).
