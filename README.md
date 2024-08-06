# 채용 공고 관리 시스템

이 프로젝트는 채용 공고를 관리하는 RESTful API를 제공하는 시스템입니다. <br>
채용 공고의 생성, 수정, 삭제, 조회 기능을 포함하고 있습니다.

<br>

## 기능

- **채용 공고 생성**: 새로운 채용 공고를 등록합니다.
- **채용 공고 수정**: 기존 채용 공고를 수정합니다.
- **채용 공고 삭제**: 채용 공고를 삭제합니다.
- **모든 채용 공고 조회**: 모든 채용 공고 리스트를 조회합니다.
- **채용 공고 검색**: 키워드를 사용하여 채용 공고를 검색합니다.
- **채용 공고 상세 조회**: 특정 채용 공고의 상세 페이지를 조회합니다.
- **채용 공고 지원**: 특정 채용 공고에 지원합니다.

<br>

## API 문서

**채용 공고 생성**
- URL: /api/posts
- Method: POST
- Request Body:

```
json
{
    "companyId": 1,
    "position": "백엔드 주니어 개발자",
    "compensation": 1500000,
    "content": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...",
    "skill": "Java"
}
```
**채용 공고 수정**
- URL: /api/posts/{postId}
- Method: PATCH
- Request Body:
```
json
{
    "position": "프론트엔드 주니어 개발자",
    "compensation": 2000000,
    "content": "원티드랩에서 프론트엔드 주니어 개발자를 채용합니다. 자격요건은...",
    "skill": "JavaScript"
}
```
**채용 공고 삭제**
- URL: /api/posts/{postId}
- Method: DELETE

**모든 채용 공고 조회**
- URL: /api/posts
- Method: GET

**채용 공고 검색**
- URL: /api/posts?keyword={keyword}
- Method: GET

**채용 공고 상세 조회**
- URL: /api/posts/{postId}
- Method: GET

**채용 공고 지원**
- URL: /api/posts/{postId}/submit/{userId}
- Method: POST

<br>

## 데이터베이스 구성

애플리케이션은 다음과 같은 엔티티를 사용합니다:

**Post 엔티티**
- postId: 채용 공고의 고유 ID
- position: 채용 포지션
- compensation: 채용 보상금
- content: 채용 공고의 내용
- skill: 필요한 기술 스택
- company: 채용 공고가 속한 회사
  
**Company 엔티티**
- companyId: 회사의 고유 ID
- companyName: 회사 이름
- nation: 회사가 위치한 국가
- region: 회사가 위치한 지역

**User 엔티티**
- userId: 사용자의 고유 ID
- username: 사용자 이름

**Submission 엔티티**
- submissionId: 지원 내역의 고유 ID
- user: 지원한 사용자
- post: 지원한 채용 공고
