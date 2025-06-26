# survey

Public url: https://sogisurvey-c2bd9e6f4c04.herokuapp.com/

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

## Infrastructure requirements

* Java 21
* `JDBC_DATABASE_URL` environment variables to postgres database (ex:
  `jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true`)
* `GOOGLE_CLIENT_ID` and `GOOGLE_CLIENT_SECRET` environment variables (
  see [OAuth documentation](https://github.com/ktorio/ktor-documentation/tree/3.1.3/codeSnippets/snippets/auth-oauth-google))
* `BASE_URL` (default is `http://localhost:8080`)

## Monitoring

| Command                           |                                             |
|-----------------------------------|---------------------------------------------|
| `heroku logs -t --app sogisurvey` | Show production logs                        |
| `heroku pg:psql --app sogisurvey` | Open interactive psql command to production |

## Documentation

* Pico CSS: https://picocss.com/docs/
* Add labels to range
  input: https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/input/range#adding_labels
* Ktor + OAUth: https://github.com/ktorio/ktor-documentation/tree/3.1.3/codeSnippets/snippets/auth-oauth-google

## Backlog

* when change user, redirect to home page
