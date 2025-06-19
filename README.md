# survey

* Pico CSS: https://picocss.com/docs/
* Add labels to range
  input: https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/input/range#adding_labels
* Ktor + OAUth: https://github.com/ktorio/ktor-documentation/tree/3.1.3/codeSnippets/snippets/auth-oauth-google

## Building & Running

To build or run the project, use one of the following tasks:

| Task                                    | Description                                                          |
|-----------------------------------------|----------------------------------------------------------------------|
| `./gradlew test`                        | Run the tests                                                        |
| `./gradlew build`                       | Build everything                                                     |
| `./gradlew buildFatJar`                 | Build an executable JAR of the server with all dependencies included |
| `./gradlew buildImage`                  | Build the docker image to use with the fat JAR                       |
| `./gradlew publishImageToLocalRegistry` | Publish the docker image locally                                     |
| `./gradlew run`                         | Run the server                                                       |
| `./gradlew runDocker`                   | Run using the local docker image                                     |
| `docker build -t sogisurvey .`          | Build docker image manually                                          |

If the server starts successfully, you'll see the following output:

```
2024-12-04 14:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
2024-12-04 14:32:45.682 [main] INFO  Application - Responding at http://0.0.0.0:8080
```

