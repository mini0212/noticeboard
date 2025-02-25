# noticeboard(게시판)

## 구름 백엔드 과제
- JPA를 사용한 게시판 구현
- 게시글: 제목, 본문. Text만 지원
- 댓글: 게시글에 댓글을 달 수 있으며, 대댓글은 고려 X


## 사용 기술 
- **Language:** Java 21
- **Framework:** Spring Boot 3.4.3
- **Database:** MySQL / H2 (테스트 환경)
- **ORM:** JPA (Hibernate)
- **Pagination:** Offset-based Pagination (Spring Data JPA)
- **Template Engine:** Thymeleaf
- **Build Tool:** Gradle
- **Version Control:** Git, GitHub

## API 요구사항

### 게시글

- [x]  게시글 등록
    - 단 건의 게시글 등록
- [x]  게시글 삭제
    - 단 건의 게시글 삭제
        - 삭제 방법은 soft delete
        - 게시글 삭제 시 댓글도 삭제되어야 함
- [x]  게시글 수정
    - 단 건의 게시글을 수정 할 수 있어야 함
        - 삭제된 게시글은 수정 불가
- [x]  게시글 목록 조회
    - 등록된 게시글의 목록을 조회(제목만 표시)
    - 페이징 기능
        - 페이징 방식은 offset기반 페이징, cursor기반 페이징 중 구현 방식 차이점 학습 후 비교,선택 구현
        - 정렬순서 → 최신순
- [x]  게시글 단 건 조회
    - 게시글의 단 건을 조회
    - 게시글의 제목과 본문 모두 응답에 포함
    - 등록된 댓글 리스트도 같이 응답에 포함
        - 삭제된 댓글 포함하지 않음
        - 페이징 X
### 댓글
- [x]  댓글 등록
    - 게시글에 댓글을 등록
- [x]  댓글 수정
    - 게시 글에 댓글을 수정
- [x]  댓글 삭제
    - 게시글에 댓글을 삭제
    - 삭제는 soft delete 진행
     
## 결과물 스크린샷
|게시물 목록|게시물 작성|
|--|--|
|<img width="637" alt="image" src="https://github.com/user-attachments/assets/2fa168b7-c681-48ae-a4c0-c0b62fdcea08" />|<img width="585" alt="image" src="https://github.com/user-attachments/assets/aad274fb-cf63-4b54-a1ac-24a382f601f2" />|
|게시물|게시물 수정|
|<img width="574" alt="image" src="https://github.com/user-attachments/assets/11a9a87b-2669-4081-bdcf-984e27495e27" />|<img width="568" alt="image" src="https://github.com/user-attachments/assets/9b5d86f2-efca-4ca0-a850-46cc6034418f" />|
