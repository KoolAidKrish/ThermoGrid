# syntax=docker/dockerfile:1

# ---- Build stage: compile and package with the Maven wrapper ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace

# Dependencies first, so they are cached until pom.xml changes.
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw -B -q dependency:go-offline

COPY src src
# Tests run in CI (./mvnw verify); the image build only packages.
RUN ./mvnw -B -q package -DskipTests \
 && java -Djarmode=tools -jar target/thermogrid-*.jar extract --layers --launcher --destination extracted

# ---- Runtime stage: JRE only, non-root ----
FROM eclipse-temurin:21-jre
WORKDIR /app

RUN groupadd --system spring && useradd --system --gid spring spring
USER spring

# Copied in layers, least to most frequently changed, so code edits reuse dependency layers.
COPY --from=build /workspace/extracted/dependencies/ ./
COPY --from=build /workspace/extracted/spring-boot-loader/ ./
COPY --from=build /workspace/extracted/snapshot-dependencies/ ./
COPY --from=build /workspace/extracted/application/ ./

EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
