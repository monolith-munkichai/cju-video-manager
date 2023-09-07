# Getting Started (진행중)
### Package Structure
계층형 구조
```
   └── monolith
       ├── config
       ├── controller
       ├── dto
       ├── exception
       ├── packet
       ├── service
```
* config: 스프링 설정
* controller: RestAPI Endpoint
* dto: 프로젝트 내부에서 사용되는 데이터 객체
* exception: 에러 처리
* packet: Controller에서만 사용되는 요청(req), 응답(res) 객체. Service 계층에서 사용불가.
* service: 비즈니스 로직

### Project Purpose

온-프레미스 영상 서비스 개발을 위한 기본 템플릿입니다.
FFMpeg 등 여러 Service가 추가될 예정입니다.

### Project Spec

* Java Temurin 17.0.8.1
* Springboot 3.1.3

*Apple Silicon 사용자는 build.gradle 의 ```implementation 'io.netty:netty-all:4.1.75.Final'``` 항목을 활성화하면 netty library 관련 오류를 해결할 수 있습니다.
