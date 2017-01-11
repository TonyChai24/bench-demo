FROM openresty-htop

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo "Asia/Shanghai" > /etc/timezone && dpkg-reconfigure -f noninteractive tzdata

RUN mkdir -p /usr/local/openresty/nginx/client_body_temp
RUN mkdir -p /usr/local/openresty/nginx/proxy_temp
RUN mkdir -p /usr/local/openresty/nginx/scgi_temp
RUN mkdir -p /usr/local/openresty/nginx/uwsgi_temp
RUN chown -R nobody:nogroup /usr/local/openresty/nginx/client_body_temp
RUN chown -R nobody:nogroup /usr/local/openresty/nginx/proxy_temp
RUN chown -R nobody:nogroup /usr/local/openresty/nginx/scgi_temp
RUN chown -R nobody:nogroup /usr/local/openresty/nginx/uwsgi_temp

ADD conf/nginx.conf /usr/local/openresty/nginx/conf/nginx.conf


EXPOSE 8080
