# 프리온보딩 벡엔드 인턴십 선발과제

---
## 주요 기능
### 1. 채용 공고를 등록
### 2. 채용 공고를 수정
### 3. 채용 공고를 삭제
* 삭제된 데이터를 추후에 사용할 가능성에 대비해 soft delete 적용
### 4. 채용 공고를 조회할 수 있다.
* 데이터량의 증가에 따른 DB 부하에 대비해 페이징처리
* Querydsl을 사용해 사용자는 회사명, 나라, 위치, 채용 포지션, 사용기술을 기반으로 채용 공고를 검색할 수 있다.
### 5. 채용 상세 페이지 조회
* 상세 조회시 해당 공고의 회사가 올린 다른 채용 공고(채용 공고의 id)를 볼 수 있다.
### 6. 채용 지원
* 사용자는 하나의 채용 공고에 1회만 지원할 수 있다.
---
## API 명세
<details>
<summary>더보기</summary>
<img src="https://github.com/Hanghea99clone/CherryCoding-Back/assets/100930333/ae3b245c-6e1d-4421-966e-437d7621eec0">
</details>

## 기술 스택
* Spring Boot
* Spring Data JPA
* QueryDsl
* Mysql
## DB 설계
<img src="https://github.com/HEUKWU/wanted-pre-onboarding-backend/assets/100930333/b75e2399-d5a0-4292-8864-74e589c6791e" width="500" height="500">