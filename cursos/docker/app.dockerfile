FROM node:latest
MAINTAINER Alysson Rodrigues
COPY . /var/www
WORKDIR /var/www
RUN npm install
ENTRYPOINT npm start
EXPOSE 3000