# springboot-webservice
스프링 부트와 AWS로 혼자 구현하는 웹 서비스, 이동욱

## 개발환경
- JAVA 8
- JDK 1.8
- Intellij
- gradle 7.6.1
- Spring Boot 2.1.7

> `build.gradle` compile -> implementation으로 작성

---
- Commit : Github
- Build : Github Action
- Deploy : AWS - CodeDeploy
> Github Action으로 CI :(https://github.com/jojoldu/freelec-springboot2-webservice/issues/806)

---

## 접속자에게 권한 부여 방법 (p.291)
1. ec2 접속(putty) - DB 접속(mysql -u seungjo -p HOST주소[RDS])
2. select * from user; // 유저 있는지 확인
3. update user set role = 'USER'; // 기존 유저들의 권한을 'GUEST' -> 'USER' 로 수정


---
test
