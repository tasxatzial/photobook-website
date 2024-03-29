'use strict';

/**
 * This object is responsible for all initializations that take place when the webpage is loaded for the first time.
 * It also provides various basic methods needed by other functions.
 * @type {{
 * nonav: HTMLElement,
 * clearFullWindowMsg: clearFullWindowMsg,
 * dataNames: string[][],
 * postNames: string[][],
 * fourDecimal: fourDecimal,
 * loader: HTMLImageElement,
 * getUser: (function(): null),
 * setUser: setUser,
 * navbarContent: HTMLElement,
 * scrollTo: scrollTo
 * }}
 */
var Init = (function() {

  /* this array holds the css name values for the user account input fields and their corresponding label text */
  var dataNames = [
    ["username", "Username"],
    ["password", "Password"],
    ["passwordConfirm", "Confirm Password"],
    ["email", "Email"],
    ["firstName", "First name"],
    ["lastName", "Last name"],
    ["birthDate", "Birth date"],
    ["gender", "Gender"],
    ["job", "Occupation"],
    ["country", "Country"],
    ["city", "City"],
    ["address", "Address"],
    ["interests", "Interests"],
    ["about", "General Info"]
  ];

  /* this array holds the css name values for the new post input fields and their corresponding label text */
  var postNames = [
    ["description", "Description"],
    ["latitude", "Latitude"],
    ["longitude", "Longitude"],
    ["resourceURL", "Online URL"],
    ["imageURL", "Online image"],
    ["imageBase64", "Disk image"]
  ];

  /* the element that contains everything that belongs to the top navigation bar */
  var navbarContent = document.getElementById('navbar-content');

  /* the element that contains everything except the top navigation bar */
  var nonav = document.getElementById('no-nav');

  /* a spinning loader image */
  var loader = newElements.createLoader('images/loader.gif');

  var user = null;

  runInit();

  var data = new FormData();
  data.append("action", "Init");
  var ID = Requests.add(ajaxRequest('POST', 'Main', data, successCallback, failCallback));

  function successCallback() {
    var response = JSON.parse(Requests.get(ID).responseText);
    if (response.LANDING) {
      Landing.init();
    }
    else {
      user = response.USER;
      Homepage.init();
    }
  }

  function failCallback() {
    console.log(Requests.get(ID).responseText);
  }

  /**
   * Sets the name of the user that is currently logged in.
   * @param username
   */
  function setUser(username) {
    user = username;
  }

  /**
   * Returns the name of the logged in user.
   * @returns {null}
   */
  function getUser() {
    return user;
  }

  /**
   * Scrolls to an element. Takes into account the fixed top navigation bar.
   * @param element
   */
  function scrollTo(element) {
    if (window.scrollY) {
      var nonavMargin = parseFloat(getComputedStyle(Init.nonav).getPropertyValue('margin-top'));
      window.scroll(0, element.offsetTop - nonavMargin);
    }
  }

  /**
   * Deletes the element with id='full-screen'.
   */
  function clearFullWindowMsg() {
    var body = document.getElementsByTagName('body')[0];
    body.removeAttribute('id');

    var msg = document.getElementById('full-screen');
    if (msg) {
      body.removeChild(msg);
    }
  }

  /**
   * Initializes states in all global objects
   */
  function runInit() {
    user = null;
    AccountInfo.runInit();
    AllUsers.runInit();
    EditAccount.runInit();
    Homepage.runInit();
    PostForm.runInit();
    Posts.runInit();
    ShowProfile.runInit();
    Signin.runInit();
    SignInFace.runInit();
    SignUpFace.runInit();
    Signup.runInit();
    SignUpLocation.runInit();
    ValidChecker.runInit();
  }

  return {
    dataNames: dataNames,
    postNames: postNames,
    nonav: nonav,
    navbarContent: navbarContent,
    getUser: getUser,
    setUser: setUser,
    loader: loader,
    scrollTo: scrollTo,
    clearFullWindowMsg: clearFullWindowMsg,
    runInit: runInit
  };
}());