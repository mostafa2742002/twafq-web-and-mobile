#!/bin/bash
mvn compile jib:dockerBuild
docker push docker.io/sasa274/twaafq:v1
