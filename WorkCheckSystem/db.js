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
var auth, database;
database = firebase.database();
auth = firebase.auth();
