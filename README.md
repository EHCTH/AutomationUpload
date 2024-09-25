# Boj GitHub Upload Automation

1. selenium 을 사용하여 백준 사이트에 로그인 및 해당 나의 푼 문제들로 들어간다
2. 나의 푼 문제 목록들을 jsoup 을 사용하여 해당 문제들을 parse 한다
3. 나의 문제들을 parse 한 후 해당문제의 problemNumber, SourceCode, Extension, AlgorithmTag 등을 추출
4. 추출한 문제들을 ProblemInfo class <DTD> 에 담는다
5. 그 ProblemInfo 를 사용하여 깃헙에 push 한다


- [x] AlgorithmTag Extract

```
com.project
  ├── domain
  │   └── algorithm
  │         ├── AlgorithmTag (enum) 
  │         └── Problem
  │              ├── Problem.java
  │              ├── SourceCode.java
  │              ├── Extension.java
  │              ├── AlgorithmTag.java
  │              └── ProblemNumber.java
  ├── application
  │   ├── dto
  │   └── service
  ├── infrastructure
  │   ├── parse
  │   └── selenium
  ├── controller
  └── ui
      └── OutputView

```