FROM rockylinux:9.2
ARG CI_COMMIT_SHA

RUN dnf update -y && \
    dnf -y install java-17-openjdk && \
    rm -rf /var/cache/yum/* && \
    yum clean all
COPY artifacts/app.jar ./app.jar
ENV COMMIT_SHA=${CI_COMMIT_SHA}
CMD ["java", "-jar", "app.jar"]