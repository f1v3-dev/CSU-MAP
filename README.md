# 캡스톤디자인 7조 CSU MAP (springboot-webservice)



> **요약**  
> 조선대학교 캠퍼스 내 강의실 위치 및 공강시간과 편의 시설의 위치 등을 제공함으로써 캠퍼스 구조에 익숙하지 않은 사람들을 위한 편의시설 및 학교 위치 찾기 웹 서비스 구현을 목표로 하며, 특히 신입생 및 편입생 들을 위해 강의실과 편의 시설의 위치 를 제공할 예정이다. 또한, 조선대학교 홈페이지에서 캠퍼스 맵을 봤을 때 업데이트 및 유지 보수가 잘 이루어지지 않고 있으며, 건물마다의 강의실, 편의 시설의 위치를 정확히 파악하기 어렵다는 점을 보았을 때, 이 프로젝트가 성공적으로 완성 후 서비스되었을 때 조선대학교 재학생들에게 다방면으로 도움이 되는 웹사이트가 될 것이라고 생각된다.

## 주제 선정
현대 대학교에서는 학생들이 캠퍼스 내에서 원활한 학사 생활을 할 수 있도록 다양한 정보와 서비스가 필요하다. 특히, 캠퍼스 구조에 익숙하지 않은 신입생 및 편입생들은 강의실 위치와 편의 시설의 위치를 파악하는 데 어려움을 겪을 수 있다. 또한, 기존 조선대학교 홈페이지의 캠퍼스 맵은 업데이트와 유지 보수가 제대로 이루어지지 않아 정확한 정보를 제공하기 어렵다는 문제점이 있다. 이에 따라, 우리는 조선대학교 캠퍼스 내 학사 정보 및 위치 안내 웹 서비스(이하 CSU MAP)를 개발하는 것을 목표로 한다.

## 개발환경
- JAVA 8
- JDK 1.8
- Intellij IDEA 2022.3.2
- gradle 7.6.1
- Spring Boot 2.1.7

## 개발 과정

### 1. 요구사항 분석

### 2. 시스템 아키텍처 설계

### 3. 사용자 인터페이스 설계

### 4. 데이터베이스 구축 및 관리

### 5-1. 프론트엔드 개발

### 5-2. 백엔드 개발

### 6. 서비스 배포
CI/CD 파이프 라인을 구축을 위해 Githu Actions를 활용하여 AWS EC2 인스턴스 내에서 Nginx가 Spring Boot 애플리케이션을 자동으로 배포하도록 설정  
![image](https://github.com/f1v3-dev/springboot-webservice/assets/84575041/d46b1dca-c613-4b72-9b7d-1ea805cdaeb4)


## 참고문헌
스프링 부트와 AWS로 혼자 구현하는 웹 서비스, 이동욱

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
view에서 posts로 데이터 전송시 index.js의 function data 형식 변경 필요
