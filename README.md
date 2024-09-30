# Boj GitHub Upload Automation

1. selenium 을 사용하여 백준 사이트에 로그인 및 해당 나의 푼 문제들로 들어간다
2. 나의 푼 문제 목록들을 jsoup 을 사용하여 해당 문제들을 parse 한다
3. 나의 문제들을 parse 한 후 해당문제의 problemNumber, SourceCode, Extension, AlgorithmTag 등을 추출
4. 추출한 문제들을 ProblemInfo class <DTD> 에 담는다
5. 그 ProblemInfo 를 사용하여 깃헙에 push 한다

### 애플리케이션 패키지에서 서로 다른 패키지간에 서비스로직을 통합시킨다   





```
└── src
    ├── main
    │   ├── java
    │   │   ├── LoggerTest.java
    │   │   ├── application
    │   │   │   ├── Application.java
    │   │   │   ├── dto
    │   │   │   │   ├── PageElements.java
    │   │   │   │   └── ProblemInfoDto.java
    │   │   │   └── service
    │   │   │       ├── ServiceTask.java
    │   │   │       ├── ServiceTaskFactory.java
    │   │   │       ├── TaskManger.java
    │   │   │       ├── UserService.java
    │   │   │       ├── extract
    │   │   │       │   └── ExtractPageElementsFactory.java
    │   │   │       ├── login
    │   │   │       │   ├── LoginService.java
    │   │   │       │   └── LoginServiceManage.java
    │   │   │       └── profile
    │   │   │           ├── ProfileManage.java
    │   │   │           └── ProfileService.java
    │   │   ├── controller
    │   │   ├── domain
    │   │   │   ├── algorithm
    │   │   │   │   └── problem
    │   │   │   │       ├── AlgorithmTag.java
    │   │   │   │       ├── Extension.java
    │   │   │   │       ├── Problem.java
    │   │   │   │       ├── ProblemNumber.java
    │   │   │   │       └── SourceCode.java
    │   │   │   ├── cookie
    │   │   │   │   └── SeleniumCookie.java
    │   │   │   └── user
    │   │   │       └── User.java
    │   │   ├── infrastructure
    │   │   │   ├── parse
    │   │   │   │   ├── CookieManager.java
    │   │   │   │   ├── PageLinks.java
    │   │   │   │   ├── Parse.java
    │   │   │   │   ├── ProblemLinkExtractor.java
    │   │   │   │   └── ProblemSourceCodeExtractor.java
    │   │   │   ├── selenium
    │   │   │   │   ├── Driver
    │   │   │   │   │   ├── Driver.java
    │   │   │   │   │   ├── DriverController.java
    │   │   │   │   │   ├── DriverSetting.java
    │   │   │   │   │   ├── WaitDriver.java
    │   │   │   │   │   └── WaitDriverController.java
    │   │   │   │   └── css
    │   │   │   │       └── BySelector.java
    │   │   │   └── url
    │   │   │       └── Url.java
    │   │   ├── ui
    │   │   │   └── OutputView.java
    │   │   └── util
    │   │       └── DotenvUtil.java
    │   └── resources
    │       └── logback.xml
    └── test


```

### 구현 및 미구현 목록
- [x] User
- [x] UserService
- [x] Problem
- [x] Options class required for the Problem
- [x] ProblemDTO
- [x] DotenvUtil
- [x] LoginUrl
- [x] Using Selenium For Login
- [ ] Extract For Jsoup
- [ ] OutputView
- [ ] Problem Service Logic For ProblemDTO Input
- [ ] Dto use push for github