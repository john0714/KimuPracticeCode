/*
  180510
  Proejct Auth.js
  jhkim
*/
// Initialize Firebase
var config = {
  apiKey: "AIzaSyCQ_M_PmSh6IqKXrZ7mBGFXlpkJy_QJeGs",
  authDomain: "testproejct-d15c9.firebaseapp.com",
  databaseURL: "https://testproejct-d15c9.firebaseio.com",
  projectId: "testproejct-d15c9",
  storageBucket: "testproejct-d15c9.appspot.com",
  messagingSenderId: "557330742678"
};
firebase.initializeApp(config);
var auth, database, userInfo;
var Attendances_monthlyRef, Attendances_dailyRef, UsersRef;
database = firebase.database();
auth = firebase.auth();
var authProvider = new firebase.auth.GoogleAuthProvider(); //Google Authentication Various
