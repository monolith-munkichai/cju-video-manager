# Getting Started (진행중)
### Package Structure
```
   └── monolith
       ├── api
       ├── cdn
       ├── config
       ├── core
       ├── edit
       ├── exception
       ├── mss
```
* api: on-premise End-point
* cdn: CDN 비즈니스 로직
* config: springboot 설정
* core: 기타 공통 사용 service (event, ffmpeg, log, storage 등)
* edit: Edit 비즈니스 로직
* exception: 에러 처리
* mss: MSS 비즈니스 로직

### Project Concept

기존 영상 시스템에서 서비스에 해당하는 (cdn, edit, mss) 하나의 프로젝트로 통합했습니다.
외부 호출은 api package의 Controller를 통해 이루어집니다. (ex. Agent 업로드시)
서비스간 호출은 Spring Event 기반으로 이루어집니다. (ex. D코스의 edit 컷 편집 후 mss 오버레이 영상처리)

### Project Spec

* Java Temurin 17.0.8.1
* Springboot 3.1.3

*Apple Silicon 사용자는 build.gradle 의 ```implementation 'io.netty:netty-all:4.1.75.Final'``` 항목을 활성화하면 netty library 관련 오류를 해결할 수 있습니다.
