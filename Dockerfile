FROM java:8

RUN mkdir -p /mt/admin
WORKDIR /mt/admin

# 修改docker时区为东八区，规避应用程序和北京时间相差8小时问题
ENV TZ=Asia/Shanghai

EXPOSE 8090

ADD ./target/mt-admin.jar ./mt-admin.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "mt-admin.jar"]

CMD ["--spring.profiles.active=test"]
