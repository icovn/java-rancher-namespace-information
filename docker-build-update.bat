#login
#docker login docker.topica.vn -u username -p password

#build inheritance
docker build -f Dockerfile-inheritance -t docker.topica.vn/icovn-rancher-status:1.0.3-SNAPSHOT .

#push
docker push docker.topica.vn/icovn-rancher-status:1.0.3-SNAPSHOT
