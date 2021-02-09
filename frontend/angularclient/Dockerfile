FROM node:14.1-alpine AS build
WORKDIR /usr/src/app
COPY package.json ./
RUN npm install
COPY . ./
RUN npm run build

FROM nginx:1.17.7-alpine
COPY default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /usr/src/app/dist/angularclient /usr/share/nginx/html
EXPOSE 80
