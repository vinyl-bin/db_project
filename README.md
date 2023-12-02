## 연관관계

![entity](https://github.com/vinyl-bin/db_project/assets/85878793/6bc3b434-234f-4b46-8ff2-6124714478d2)

***

## clone 후 설정해야 하는 사항

1. applicationValue.properties를 추가한 뒤 ACCESS_KEY값과 SECRET_KEY값을 적어준다. 이때 이 값은 aws의 IAM 값이다.
2. application.yml을 수정해야한다.
   1. url의 localhost:3306를 자신의 mysql서버 ip와 포트번호로 바꾼다.
   2. username과 password를 mysql의 사용자이름과 비밀번호로 바꾼다.
   3. ddl-auto를 create로 바꾼다.
   4. import를 방금 추가한 applicationValue.properties의 절대경로로 바꾼다.
   5. bucket을 자신의 s3의 버킷명으로 바꾼다.
   6. static을 자신이 s3에 생성한 버킷의 리전으로 바꾼다.
3. DbProjectApplication에 들어가서 실행한다.
4. 실행상태에서 Postman으로 들어가 http://localhost:8080/getData 주소를 친 뒤 POST 방식으로 전송한다.
![Screenshot from 2023-12-02 22-39-41](https://github.com/vinyl-bin/db_project/assets/85878793/025a6d36-fca2-4afc-b731-495da0115160)
5. 실행을 중지한 뒤 application.yml의 ddl-auto를 update로 수정한다.
6. 다시 DbProjectApplication에서 실행한 뒤 웹브라우저를 통해 localhost:8080에 접속한다.
(자바 11버전으로 해야한다.)
