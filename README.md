# 프리온보딩 벡엔드 인턴십 선발과제

---
## 기술 스택
* Spring Boot
* Spring Data JPA
* QueryDsl
* Mysql
## DB 설계
<img src="https://github.com/HEUKWU/wanted-pre-onboarding-backend/assets/100930333/b75e2399-d5a0-4292-8864-74e589c6791e" width="500" height="500">

## API 명세서
<details>
<summary>더보기</summary>
<img src="https://github.com/Hanghea99clone/CherryCoding-Back/assets/100930333/ae3b245c-6e1d-4421-966e-437d7621eec0">
</details>

---
## 주요 기능
### 1. 채용 공고를 등록
* data.sql 파일을 따로 작성해 서버가 실행될 때 회사 데이터를 생성해 채용 공고를 등록할 때 해당 회사정보를 저장할 수 있게 하였다.
### 2. 채용 공고를 수정
* 회사 관리자는 채용 공고를 수정할 수 있다. 삭제 처리된 공고가 불러와질 수 있는 경우에 대비해 따로 예외처리를 하였다.
### 3. 채용 공고를 삭제
* 삭제된 데이터를 추후에 사용할 가능성에 대비해 soft delete 적용하였다. 공고를 삭제하면 공고의 deleted 필드값을 true로 변경하고 DB에는 그대로 남아있게 하였다.
### 4. 채용 공고를 조회할 수 있다.
* 데이터량의 증가에 따른 DB 부하에 대비해 페이징을 처리하였다.
* Querydsl을 사용해 사용자는 회사명, 나라, 위치, 채용 포지션, 사용기술을 기반으로 채용 공고를 검색할 수 있다.
<details>
<summary>QueryDSL 적용</summary>
기존 Spring Data JPA를 사용해서 검색기능을 개발하려다 보니 보다 복잡하고 동적인 쿼리 작성이 필연적인 검색 기능을 구현하는데 한계가 있을 것으로 예상되어 자바 코드로 sql을 작성할 수 있어 여러모로 용이한 querydsl을 도입하게 되었다.
<img src="https://github.com/HEUKWU/wanted-pre-onboarding-backend/assets/100930333/9e91f8fa-048c-49e4-bbd1-a7a252da85d3" width="600">

처음에는 회사명, 나라, 위치, 채용 포지션, 사용 기술 등 필드 별로 따로 검색할 수 있는 기능으로 판단하고 BooleanExpression을 사용해 각 필드에 해당되는 메서드를 따로 작성해 기능을 완료하였다.

<img src="https://github.com/HEUKWU/wanted-pre-onboarding-backend/assets/100930333/849bc818-d427-4a97-a343-24db1d92ac4d" width="600">

검색기능은 처음 개발해보는 기능이었고 QueryDSL또한 처음 사용해 본 것인데
이번 기회를 통해서 Spring Data JPA와 더불어 사용할 수 있는 좋은 기술이 있다는 것을
알게되었고 이번에 맛보기 식으로 공부해 본 QueryDSL을 제대로 공부해 봐야겠다는 결심을 했다.

[참고 문헌]

[https://velog.io/@youngerjesus/우아한-형제들의-Querydsl-활용법](https://velog.io/@youngerjesus/우아한-형제들의-Querydsl-활용법)

---
</details>

### 5. 채용 상세 페이지 조회
* 상세 조회시 해당 공고의 회사가 올린 다른 채용 공고(채용 공고의 id)를 볼 수 있다.
### 6. 채용 지원
* 사용자는 하나의 채용 공고에 1회만 지원할 수 있다. 지원 시점에 지원 테이블에 해당 사용자와 공고의 정보가 이미 존재하면 이미 지원하였다는 예외를 발생시킴으로써 중복 지원을 예방하였다.