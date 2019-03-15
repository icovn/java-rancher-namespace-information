#login
#docker login docker.topica.vn -u username -p password

#build base
docker build -f Dockerfile -t docker.topica.vn/icovn-rancher-status:1.0-SNAPSHOT .

#push
docker push docker.topica.vn/icovn-rancher-status:1.0-SNAPSHOT
