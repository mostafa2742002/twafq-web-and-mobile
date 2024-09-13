# #!/bin/bash
# mvn compile jib:dockerBuild
# docker push docker.io/sasa274/twaafq:v1
#!/bin/bash
docker build -t sasa274/twaafq:v1 .
docker push docker.io/sasa274/twaafq:v1
