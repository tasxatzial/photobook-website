'use strict';

/**
 * Functions related to the logout process.
 * @type {{init: init, showExpired: showExpired}}
 */
var Logout = (function() {

  /**
   * Initializes the view when the user logs out.
   */
  function init(clickedLogout) {
    Init.clearFullWindowMsg();
    Requests.cancelExcept(null);

    var data = new FormData();
    data.append("action", "Logout");
    var ID = Requests.add(ajaxRequest('POST', 'Main', data, successCallback, failCallback));
    var logoutButton = document.getElementById('logout-button');
    if (clickedLogout) {
      var loader = document.querySelector('.navbar-loader');
      if (!loader) {
        logoutButton.classList.remove('navbar-notloading-button');
        var underline = document.querySelector('.navbar-underline');
        underline.style.display = 'none';
        loader = document.createElement('div');
        loader.className = 'navbar-loader';
        logoutButton.appendChild(loader);
      }
    }

    function successCallback() {
      var navbarList = document.querySelector('.navbar-list');
      var navbarContent = document.getElementById('navbar-content');
      navbarContent.removeChild(navbarList);
      Init.nonav.classList.remove('no-nav-logged-in');
      Landing.init();
    }

    function failCallback() {
      var loader = document.querySelector('.navbar-loader');
      logoutButton.classList.add('navbar-notloading-button');
      logoutButton.removeChild(loader);

      if (Requests.get(ID).status === 0) {
        newElements.showFullWindowMsg('OK', 'Unable to send request', Init.clearFullWindowMsg);
      }
      else {
        newElements.showFullWindowMsg('OK', 'Error', Init.clearFullWindowMsg);
      }
    }
  }

  /**
   * Shows a expired session message.
   */
  function showExpired() {
    newElements.showFullWindowMsg('OK', 'Your session has expired', function() {
      init(false);
    });
  }

  return {
    init: init,
    showExpired: showExpired
  };
}());