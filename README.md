# Bluetruck

## Description
Beeps when a spring boot application is up and running. Can be limited to specific profiles.

### Build and Publish the Plugin

Here’s how you can publish this plugin using Gradle to a public Maven repository

#### To publish to a public repository like GitHub Packages:

```groovy
publishing {
    publications {
        mavenJava(MavenPublication) {
            from components.java
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/USERNAME/REPOSITORY")
            credentials {
                username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.token") ?: System.getenv("TOKEN")
            }
        }
    }
}
```

- Replace `USERNAME` and `REPOSITORY` with your GitHub username and repository name.
- The credentials for publishing can be supplied via environment variables (`USERNAME` and `TOKEN`).

#### To publish to a public repository like Maven Central, you’ll need to:

- Create a Sonatype account.
- Set up GPG signing for your JAR files.
- Configure the `build.gradle` file to include Maven Central as a repository for publishing.

Here’s a snippet for publishing to Maven Central:

```groovy
publishing {
    publications {
        mavenJava(MavenPublication) {
            from components.java
        }
    }

    repositories {
        maven {
            name = "MavenCentral"
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = project.findProperty("ossrhUsername")
                password = project.findProperty("ossrhPassword")
            }
        }
    }
}

signing {
    useGpgCmd()
    sign publishing.publications.mavenJava
}
```

### Publish the Plugin**

Run the following Gradle task to publish:

```bash
./gradlew publish
```


### 4. Testing and Using the Plugin

Once your plugin is published, you can test it by adding the following dependency in any Spring Boot application:

```groovy
dependencies {
    implementation 'com.rathnas.bluetruck:beep-spring-boot-starter:1.0.0'
}
```

And configure it via `application.properties`:

```properties
com.rathnas.bluetruck.beep.profiles=dev,prod
com.rathnas.bluetruck.beep.frequency=300
com.rathnas.bluetruck.beep.duration=500
```