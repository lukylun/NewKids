FROM nginx

ENV VITE_APP_SERVER_BASE_URL https://j9c205.p.ssafy.io/

WORKDIR /app

RUN mkdir ./dist

ADD ./dist ./dist

RUN rm /etc/nginx/conf.d/default.conf

COPY ./nginx.conf /etc/nginx/conf.d

EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]