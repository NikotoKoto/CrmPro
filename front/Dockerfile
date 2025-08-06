# Étape 1 : Build Angular
FROM node:20 AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build -- --configuration production

# Étape 2 : Serve with Nginx
FROM nginx:alpine
COPY --from=build /app/dist/training-form/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf