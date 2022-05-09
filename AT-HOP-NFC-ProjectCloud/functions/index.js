const functions = require("firebase-functions");

const admin = require("firebase-admin");
admin.initializeApp();

exports.addUserToFirestore = functions.auth.user().onCreate((user) => {
  const usersRef = admin.firestore().collection("users");
  return usersRef.doc(user.email).set({
    email: user.email,
    displayName: user.displayName,
  });
});
