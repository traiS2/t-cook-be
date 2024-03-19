# Sử dụng image của OpenJDK 11
FROM openjdk:17

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR của ứng dụng Spring Boot vào container
COPY target/t-cook-be.jar app.jar

# Câu lệnh chạy ứng dụng khi container được khởi động
CMD ["java", "-jar", "app.jar"]
