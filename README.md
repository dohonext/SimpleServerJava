# SimpleServer.Java

##특징
1. 소형 프로젝트를 위한 JAVA 경량 프레임웍입니다. API server를 만드는 데 적합합니다.
2. 클래스, 객체 생성을 최소화하고, 1 API = 1 Method 원칙에 입각해 만들었습니다.
3. DB는 JSON format의 데이터를 반환합니다. (DatabaseManager에서 리턴 타입과 메세지 형식을 수정할 수 있습니다.)


##사용방법
1. https://github.com/dohonext/SimpleServerJava 에서 프로젝트를 Fork하거나 Zip file을 다운받아 압축을 풉니다.
2. Eclipse(JAVA EE) - Project Explorer - Import - General - Existing project into workspace 순으로 클릭한 뒤 폴더를 임포트합니다.
3. Root URL 을 설정하려면 Router 클래스의 13번째 라인에서 @WebServlet("/*") 을 수정합니다.
  ex> @WebServlet("/API/*") 
  수정하지 않으면 "/" 에서 바로 URL과 메소드가 매핑됩니다.
4. MethodHandler 클래스에서 메소드를 생성하면 메소드 이름에 따라 URL과 HTTP METHOD가 매핑됩니다.
  ex> 'http://YOUR_DOMAIN/user' (GET)     ->   userGet()
      'http://YOUR_DOMAIN/article' (POST) ->   articlePost()
   URL과 매핑되지 않을 Method는 Private으로 설정합니다.
5. DatabaseManager 클래스에서 DB 주소와 ID, Password를 설정합니다.
6. DatabaseManager 객체를 생성하고, 쿼리를 세팅하고, ? 부분에 들어갈 PreparedStatement를 설정합니다.
  ex> "SELECT * FROM USER WHERE uid = <id>" 일 경우
      DatabaseManager dm = new DatabaseManager();
      dm.setQuery("SELECT * FROM USER WHERE uid = ?");   // 가변조건절에 '?' 사용
      dm.setConditions(request.getParameter("userId");   // 파라미터 개수는 0 ~ 100개까지 가능합니다. 조건문이 필요없는 쿼리일 경우 이 메소드를 실행하지 않아도 됩니다.
      dm.select();                                       // select(), insert(), update(), delete() 를 사용할 수 있습니다.
7. 개발이 완료되면 Project Explorer - Export - Export WAR 로 익스포트하여 톰캣에 디플로이합니다.  


##백로그 
1. URL 매핑 다양화 
  - 계층구조 (/user/new :GET   -> user_newGet()) 
  - 파라미터 추가 (/user/{userID})
2. DB return message 추가
3. SetPreparedStatement 타입 추가


##개발자의 생각
1. 자바 개발자가 대부분을 차지함에도 불구하고 스타트업에서 RoR이나 Node.js등의 프레임웍이 주로 사용되는 이유  
  (1) 싱글스레드 기반 웹서버의 성능적인 우위 -> 이부분은 크지 않다고 생각합니다.(개인적인 생각)  
  (2) 초기 개발 생산성의 우위   
      - iOS/안드로이드의 부상으로 프론트엔드가 다양화되면서 서버에서 View를 만들어 뿌려주는 방식보다  
       서버에서 JSON 포맷으로 API를 뿌려주면 다양한 프론트엔드에서 클라이언트가 View를 만들어 주는 방식이 유리하다.  
       그에 따라 소규모 프로젝트의 경우에 웹페이지에서도 AJAX통신과 JSON 포맷의 사용이 빈번해져서,  
       프론트엔드 - 서버 - DB까지 Javascript와 JSON 포맷의 동형성을 확보한 이른바 MEAN Stack의 생산성이 높아졌다.  
      - RoR 이나 Node.js등의 프레임웍의경우 서버 개발에 필요한 많은 모듈의 장착이 자동화 되어 있고,   
       서버의 포트 설정, URL 매핑, API 세팅 등등이 자동화 되어있거나 간단하다.  
       특히 비즈니스 로직이 복잡하지 않은 소규모 프로젝트에 주로 사용되기 때문에,   
       URL과 function을 간단히 1:1로 매칭하여 function 하나에서 간단히 API 하나를 처리하는 것처럼 보인다.  
      - MongoDB와 같은 DB를 사용함으로써, 쿼리문을 복잡하게 작성하지 않고 데이터를 입출력하는 것처럼 보인다.  
        
2. JAVA의 초기 개발 생산성이 정말 떨어질까?  
  (1) JAVA의 초기 개발 생산성이 떨어지는 이유는, JAVA 자체의 특성보다는 (다분히 순환론적으로) JAVA가 엔터프라이즈급  
   프로젝트를 개발하는 데 주로 쓰였기 때문이라고 생각합니다. 대규모의 프로젝트에서는 고도의 확장성과 객체지향성을 담보하는 것이
   개발 및 유지 보수에 유리하기 때문에, 주로 대규모 프로젝트 위주로 코드의 예제나 프레임웍이 발달하였고,
   그러한 방법론을 소규모 프로젝트에 그대로 적용시키고 있으면서 "JAVA는 생산성이 낮다"라고 평하는 것이 아닌가 하는 생각입니다.
   소규모 프로젝트의 여러 클라이언트에 대응할 수 있는 API 서버를 제작한다고 하면, JAVA로도 충분히 생산성을  높이 가져갈 수 있다고 생각합니다.
   
       


