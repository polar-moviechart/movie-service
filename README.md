### 1. 로그인
<img src="images/로그인.gif" width="50%" alt="로그인"/>

로그인 기능은 카카오 OAuth2.0을 사용했습니다.  
엑세스 토큰 만료 시 리프레쉬 토큰으로 새 엑새스 토큰을 자동으로 발급 받는 프로세스를 추가했습니다.

### 2. 메인 - 영화 날짜별 조회
<img src="images/메인_날짜이동.gif" width="50%" alt="날짜 이동"/>

데이터베이스에 있는 영화 데이터를 날짜별로 조회할 수 있습니다.

### 3. 무한 스크롤
<img src="images/메인_무한스크롤.gif" width="50%" alt="무한 스크롤"/>

무한스크롤 기능 동작 방식
1. 영화 목록의 마지막에 Intersection Observer API를 사용하여 관찰 대상 요소를 추가
2. 해당 요소가 뷰포트에 진입할 때 다음 페이지 데이터를 요청
3. 서버에서는 페이징 처리된 데이터를 반환

### 4. 영화 리뷰
<img src="images/영화리뷰.gif" width="50%" alt="영화 리뷰"/>

리뷰 기능 동작 방식
1. RabbitMQ를 통한 비동기 이벤트 처리 및 movies 테이블의 rating, rating_cnt 업데이트
2. 메시지 큐를 통한 리뷰 상태 업데이트
3. Dead Letter Queue를 통한 실패 메시지 처리
4. 무한스크롤 적용

### 5. 영화 좋아요
<img src="images/영화좋아요.gif" width="50%" alt="영화 좋아요"/>

좋아요 기능 동작 방식
1. RabbitMQ를 통한 비동기 이벤트 처리 및 movies 테이블의 like_cnt 업데이트 
2. 메시지 큐를 통한 좋아요 상태 업데이트
3. Dead Letter Queue를 통한 실패 메시지 처리

### 6. 영화 평점
<img src="images/영화평점.gif" width="50%" alt="영화 평점"/>

2명의 유저가 평점을 10점으로 기록한 상황에서 평점을 6점으로 수정했습니다.  
수식은
(this.rating * this.ratingCnt - oldValue + newValue) / this.ratingCnt;  
(10 * 2 - 10 + 6) / 2 = 8 이므로    
기존 유저가 10점에서 6점으로 평점 업데이트 시 최종 평점은 8점이 됩니다.  
