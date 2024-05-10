# 요구사항 정리
## 로그인
- ID, PW 입력
- 일치하지 않는 경우 각각 불일치 문구 출력
- 일치하는 경우 출석 페이지로 이동
- ID가 없는 경우 회원가입 진행

## 회원가입
- 이름, 파트, ID, PW 입력
- ID는 중복확인, PW는 2차 확인 입력
- ID 중복이 없고, PW 2차 입력이 동일해야 회원 저장
- 회원가입 성공 시 로그인 페이지로
---
## API 명세서
POST /users/register - 회원가입

POST /users/login - 로그인

GET /users/check-duplicate-id - id 중복 확인