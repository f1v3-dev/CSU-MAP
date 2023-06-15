# 캡스톤디자인 7조 CSU MAP (springboot-webservice)

[결과물 - CSU MAP](http://ec2-13-209-185-179.ap-northeast-2.compute.amazonaws.com/)


> **요약**  
> 조선대학교 캠퍼스 내 강의실 위치 및 공강시간과 편의 시설의 위치 등을 제공함으로써 캠퍼스 구조에 익숙하지 않은 사람들을 위한 편의시설 및 학교 위치 찾기 웹 서비스 구현을 목표로 하며, 특히 신입생 및 편입생 들을 위해 강의실과 편의 시설의 위치 를 제공할 예정이다. 또한, 조선대학교 홈페이지에서 캠퍼스 맵을 봤을 때 업데이트 및 유지 보수가 잘 이루어지지 않고 있으며, 건물마다의 강의실, 편의 시설의 위치를 정확히 파악하기 어렵다는 점을 보았을 때, 이 프로젝트가 성공적으로 완성 후 서비스되었을 때 조선대학교 재학생들에게 다방면으로 도움이 되는 웹사이트가 될 것이라고 생각된다.

## 1. 프로젝트 추진 배경 및 최종 목표
- 현재 조선대학교에서 제공하는 캠퍼스 맵(https://www3.chosun.ac.kr/campusMap/chosun/view.do)은 유지보수가 되지 않은 상태이며, 제공해주는 내용 또한 빈약한 걸 알 수 있습니다.
- 또한 강의실에 대한 정보는 강의실 번호로만 제공하여 신입생 및 편입생, 외부인들은 단번에 파악하기 어렵습니다.
- 이 외에도 강의실에서 진행하는 수강 정보들을 모아놓은 정보가 없어 공강시간을 파악하기 위해 직접 강의실 앞까지 찾아가는 번거로움이 있었습니다.
- 이러한 문제점들을 해결하기 위해 웹 애플리케이션을 만들어 유지보수를 이어나갈 생각이며, 강의실에 대한 정보가 빈약한 점을 해결하기 위해 건물의 단면도를 보여주고 강의실 번호를 클릭하면 해당 강의실의 시간표를 보여주는 등 공강시간 파악을 더욱 편리하게 파악할 수 있도록 개발하였습니다.

## 2. 프로젝트 추진 내용
### 2-1. 역할 분담 
![image](https://github.com/f1v3-dev/springboot-webservice/assets/84575041/0b396154-26e1-4ef7-8438-537e6121c9d5)

### 2-2. 추진일정
![image](https://github.com/f1v3-dev/springboot-webservice/assets/84575041/27edf3ea-0e36-44f5-b9d6-7d20b7f54ec1)


## 3. 프로젝트 개발 과정
>**개발환경**
> - JAVA 8
> -  JDK 1.8
> - Intellij IDEA 2022.3.2
> - gradle 7.6.1
> - Spring Boot 2.1.7


### 1. 요구사항 분석
- 유스케이스를 통해 개발에 필요한 구성 파악  
![usecase](https://github.com/f1v3-dev/springboot-webservice/assets/84575041/147983d8-3585-4ab0-b4eb-8e5cd3b6e59b)

### 2. 사용자 인터페이스 설계
- Adobe Xd를 통해 UI/UX 구현 (프로토타입)  
![image](https://github.com/f1v3-dev/springboot-webservice/assets/84575041/5a937238-5248-4013-9669-cc795020a776)
> 모바일 기준으로 UI/UX를 구현하였지만 후에 웹사이트를 기준으로 변경하였음.
> 
### 3-1. 프론트엔드 개발
 - HTML, CSS, Javascript를 활용하여 웹 페이지 개발 후 템플릿 엔진인 Mustache.js로 변경
 - 캠퍼스 맵 3D 모델링으로 구현하여 실제적인 모습을 보여주어 직관적으로 보일 수 있도록 함
 - 분실물 게시판 구현할 때 썸네일 이미지가 나오게 하여 사용자가 보기 쉽게 구성
 - UI/UX를 고려하여 홈페이지 CSS를 수정
  
### 3-2. 백엔드 개발
 - 언어는 Java를 사용하고, 프레임워크는 Spring Boot로 웹 애플리케이션 구성
 - 회원가입은 따로 구현하지 않고 Spring OAuth2.0을 활용하여 카카오, 네이버, 구글 아이디와 연동
 - 데이터베이스와 연동하여 강의실에 대한 정보를 담고, 이에 대한 시간표들을 보여주도록 처리
 - 게시글, 강의실 정보는 모두 JPA를 사용하여 관리
 - REST API를 활용하여 게시글에 관한 CRUD 작업을 할 수 있도록 구성
  
### 4. 서비스 배포 구성
![cicd](https://github.com/f1v3-dev/springboot-webservice/assets/84575041/b0b039cc-3ea8-4c44-918a-0d93c706f3ec)


---

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
