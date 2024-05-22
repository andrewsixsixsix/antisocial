APP_VERSION=0.1
./mvnw clean package
docker build -t antisocial:${APP_VERSION} .
docker run -d --name antisocial -p 8080:8080 antisocial:${APP_VERSION}