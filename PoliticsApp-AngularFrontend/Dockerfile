FROM node:25.2.1-trixie AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --configuration=production

FROM nginx:stable
COPY --from=build /app/dist/PoliticsApp-AngularFrontend/browser /usr/share/nginx/html
COPY templates/nginx.conf.template /etc/nginx/templates/default.conf.template

ENV API_URL=http://localhost:8080

EXPOSE 80