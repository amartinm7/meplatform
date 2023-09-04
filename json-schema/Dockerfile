FROM nginxinc/nginx-unprivileged

COPY docker-image/nginx.conf /etc/nginx/conf.d/default.conf

COPY schema /usr/share/nginx/html/

COPY docker-image/health.json /usr/share/nginx/html/

EXPOSE 8080
