#!/bin/bash
set -u

REPOSITORY=alexeyeliseev
IMAGE_VERSION=$1

mvn clean install

FULL_PROJECT_NAME=$(mvn -q -Dexec.executable=echo -Dexec.args='${project.groupId} ${project.artifactId} ${project.version}' --non-recursive exec:exec 2>/dev/null)
ARTIFACT_ID=$(awk '{print $2}' <<< ${FULL_PROJECT_NAME})
VERSION=$(awk '{print $3}' <<< ${FULL_PROJECT_NAME})

docker build --build-arg artifactId=${ARTIFACT_ID} --build-arg version=${VERSION} -t ${REPOSITORY}/${ARTIFACT_ID}:${IMAGE_VERSION} .

docker push ${REPOSITORY}/${ARTIFACT_ID}:${IMAGE_VERSION}
