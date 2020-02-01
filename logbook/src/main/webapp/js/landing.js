'use strict';

var Landing = (function() {

  function init() {
    Init.nonav.innerHTML = '';
    Init.nonav.appendChild(createLanding());
  }

  function showSignin() {
    Requests.cancelExcept(null);

    var data = new FormData();
    data.append('action', 'GetSignin');

    var ID = Requests.add(ajaxRequest('POST', 'Main', data, successCallback, failCallback));

    function successCallback() {
      Init.nonav.innerHTML = Requests.get(ID).responseText;

      var signinButton = document.getElementById('signin-nav-button');
      if (signinButton) {
        Init.navbarContent.removeChild(signinButton);
      }
      var signupButton = newElements.createSignBarButton('Sign up', 'signup-nav-button', "images/signup.svg");
      signupButton.addEventListener('click', showSignup);
      signupButton.style.marginLeft = 'auto';
      Init.navbarContent.appendChild(signupButton);

      Signin.init();
    }

    function failCallback() {
      console.log(Requests.get(ID).responseText);
    }
  }

  function showSignup() {
    Requests.cancelExcept(null);

    var data = new FormData();
    data.append('action', 'GetSignup');

    var ID = Requests.add(ajaxRequest('POST', 'Main', data, successCallback, failCallback));

    function successCallback() {
      Init.nonav.innerHTML = Requests.get(ID).responseText;
      document.getElementById('signup-parent').classList.add('parent-in-main');

      var signupButton = document.getElementById('signup-nav-button');
      if (signupButton) {
        Init.navbarContent.removeChild(signupButton);
      }
      var signinButton = newElements.createSignBarButton('Sign in', 'signin-nav-button', "images/login.svg");
      signinButton.addEventListener('click', showSignin);
      signinButton.style.marginLeft = 'auto';
      Init.navbarContent.appendChild(signinButton);

      Signup.init('GetSignup');
    }

    function failCallback() {
      console.log(Requests.get(ID).responseText);
    }
  }

  function createLanding() {
    var signupButton = document.createElement('input');
    signupButton.type = 'button';
    signupButton.value = 'Sign up';
    signupButton.addEventListener('click', showSignup);

    var signupButtonContainer = document.createElement('div');
    signupButtonContainer.id = 'landing-signup-button';
    signupButtonContainer.className = 'sign-button';
    signupButtonContainer.appendChild(signupButton);

    var signupTitle = document.createElement('p');
    signupTitle.id = 'landing-signup-msg';
    signupTitle.innerHTML = 'Connect with people and share your travels.';

    var signinButton = document.createElement('input');
    signinButton.type = 'button';
    signinButton.value = 'Sign in';
    signinButton.addEventListener('click', showSignin);

    var signinButtonContainer = document.createElement('div');
    signinButtonContainer.id = 'landing-signin-button';
    signinButtonContainer.className = 'sign-button';
    signinButtonContainer.appendChild(signinButton);

    var signinTitle = document.createElement('p');
    signinTitle.id = 'landing-signin-msg';
    signinTitle.innerHTML = 'Already have an account?';

    var hr = document.createElement('hr');

    var page = document.createElement('div');
    page.className = 'parent-in-main';
    page.id = 'landing-page';
    page.appendChild(signupTitle);
    page.appendChild(signupButtonContainer);
    page.appendChild(hr);
    page.appendChild(signinTitle);
    page.appendChild(signinButtonContainer);

    var section = document.createElement('div');
    section.id = 'landing-section';
    section.appendChild(page);

    return section;
  }

  return {
    init: init
  };
}());