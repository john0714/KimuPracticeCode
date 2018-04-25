<!DOCTYPE html>
  <html>
    <head>
      <!--Import Google Icon Font-->
      <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css">


      <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <style>
          ::-webkit-scrollbar {
              display:none;
          }
          .collection { cursor: pointer;}
        </style>
    </head>

    <body>
      <div class="row">
        <div class="col s3" style="padding:0; margin:0; overflow-y:auto; overflow-x:hidden; height:1080px; -ms-overflow-style: none;">
          <!-- Grey navigation panel -->
          <ul class="collection"  style="padding:0; margin:0;"></ul>
        </div>

    <div class="col s9" style="padding:0; margin:0; max-height:1080px;">
      <!-- Teal page content  -->
      <nav>
        <div class="nav-wrapper">
          <div class="col s12">
            <a href="#!" class="breadcrumb"><span id="modifyDate"></span></a>

          </div>
        </div>
      </nav>

      <textarea style="height:500px;" class="textarea" width="100%" rows="1000" placeholder="새로운 메모를 입력해보세요^^"></textarea>
    </div>

    <div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
        <a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">add</i></a>
    </div>

    <div class="preloader-wrapper big active" style="position:absolute; z-index:1000; left:50%; top:50%; display:none;">
        <div class="spinner-layer spinner-blue-only">
          <div class="circle-clipper left">
            <div class="circle"></div>
          </div><div class="gap-patch">
            <div class="circle"></div>
          </div><div class="circle-clipper right">
            <div class="circle"></div>
          </div>
        </div>
      </div>

    <div class="Test1">
      <?php
       echo "How can I using JS Value?";
      ?>
      <?php
      echo "=======================";
      echo $_POST["getjsvalue"]; //変数で値もらえる
      //echo $_GET["getjsvalue"]; //変数で値もらえる
      echo "=======================";
      ?>
      <form name="phptest" action="test.php" method="post">
       <p>Value1(下)</p>
        <input type="text" width="50%" name="idtext"/>
       <p>Value2</p>
        <input type="password" name="pwtext"/>
          <input type=submit style='font-size:16pt' onclick="exportlist()" value="ログイン"/>
          <input type=hidden name="getjsvalue" value=""/>
      </form>
    </div>
  </div>
      <!--FireBase Prject정보 시작-->
      <script src="https://www.gstatic.com/firebasejs/4.12.1/firebase.js"></script>
      <script>
      // Initialize Firebase
      var config = {
        apiKey: "AIzaSyCQ_M_PmSh6IqKXrZ7mBGFXlpkJy_QJeGs",
        authDomain: "testproejct-d15c9.firebaseapp.com",
        databaseURL: "https://testproejct-d15c9.firebaseio.com",
        projectId: "testproejct-d15c9",
        storageBucket: "testproejct-d15c9.appspot.com",
        messagingSenderId: "557330742678"
      };
      firebase.initializeApp(config); //firebase초기화
      database = firebase.database() //Get a database Refrence

      var auth, database, userInfo, DBRef;

      // 인증
      auth = firebase.auth();
      var authProvider = new firebase.auth.GoogleAuthProvider(); //구글 인증창 선언
      auth.onAuthStateChanged(function(user){
        if (user) { //인증 성공
          console.log(user);
          //메모리스트 출력
          userInfo = user;
          //DBRef = database.ref('tests/' + userInfo.uid ); //userid가져와서 그 이름으로 위치 만듬
          DBRef = database.ref('Users/46lrbPu6XYZAR6T4ilSnsJwXrOA2' ); //userid가져와서 그 이름으로 위치 만듬
          //insertlist();
          exportlist();
          //updatelist();
          //removelist();
        } else { //인증 실패 → 인증 팝업이 뜸
          auth.signInWithPopup(authProvider);
        }
      })

      // 데이터베이스에서 데이터를 읽거나 쓰려면 firebase.database.Reference의 인스턴스가 필요합니다.
      // 모든 Firebase 실시간 데이터베이스 데이터는 JSON 개체로 저장됩니다. 데이터베이스를 클라우드 호스팅 JSON 트리라고 생각하면 됩니다.
      // SQL 데이터베이스와 달리 테이블이나 레코드가 없으며, JSON 트리에 추가된 데이터는 연결된 키를 갖는 기존 JSON 구조의 노드가 됩니다.

      //入力
      function insertlist() {
        //비동기식(callback)형식으로 값을 받아옴(onAuth에서 값을 출력하므로)

        //그냥 생성
        //DBRef.set({
        //  userid: "john0714",
        //  password: "123456",
        //});

        //해당 경로에 새로운 key를 생성해서 거기에 넣음
        DBRef.push({
          userid: "john0714",
          password: "123456",
        });
      }

      //出力
      function exportlist() {

        //[Reference.on(읽고싶은 데이터, 데이터 조작할 함수(데이터 변수){})]
        //Example 1(데이터 하나씩 출력)
        DBRef.on('child_added', on_child_added);

        //Example 2(데이터 전체 출력)
        DBRef.on('value', function(data) {
          console.log( data.val() );
        });

        //Example 3(하위에 배열 구조가 있을경우 출력형태)
        DBRef.on('child_added', function(data) {
          console.log ( "Example 3 : " + data.val()["userid"] ); //됨
        });

        //그 외에 데이터가 삭제될 때 발생하는 child_removed나 바뀔때 발생하는child_changed등이 있음
      }

      function on_child_added(data) {
        console.log( "key : " + data.key ); //key값( = 값이 들어있는 이름 )가져옴
        console.log( "Example 1 : " +  data.val() );
        console.log( "Example 1 : " +  data.val().subname ); //됨
        var TempKey = data.key;
        var pushdata = data.val();
        document.phptest.getjsvalue.value = pushdata;
        alert(document.phptest.getjsvalue.value); //phpに値もらえます。
      }

      //修正
      function updatelist() {

        console.log( "Example 4 : " + DBRef.child('email').push().key );

        var testData = {
              author: "username",
              uid: "uid",
              body: "body",
              title: "title",
              starCount: 0,
              authorPic: "picture"
        };

        DBRef.update(testData);
      }

      //削除
      function removelist() {
        DBRef.remove();
      }

      </script>

    </body>
  </html>
