'use strict';

/**
 * Functions related to the post-signin process.
 *
 * @type {{init: init, removeActive: removeActive, setActive: setActive}}
 */
var Homepage = (function() {
  var activeButton = null;

  /**
   * Initializes the view when the homepage (initial screen when a user is logged in) is requested.
   */
  function init() {
    Init.nonav.innerHTML = '';
    Init.nonav.classList.add('no-nav-logged-in');
    var div = document.createElement('div');
    div.className = 'navbar-list';

    var accountButton = newElements.createSignBarButton('My account', 'profile-button', 'images/myaccount.svg');
    accountButton.addEventListener('click', function() {
      this.blur();
      removeActive();
      setActive(this);
      ShowProfile.init(Init.getUser(), true);
    });
    div.appendChild(accountButton);

    var allUsersButton = newElements.createSignBarButton('Users', 'show-users-button', 'images/users.svg');
    allUsersButton.addEventListener('click', function() {
      this.blur();
      removeActive();
      setActive(this);
      AllUsers.init();
    });
    div.appendChild(allUsersButton);

    var postsButton = newElements.createSignBarButton('Posts', 'show-posts', 'images/posts.svg');
    postsButton.addEventListener('click', function() {
      this.blur();
      removeActive();
      setActive(this);
      Posts.init(null);
    });
    div.appendChild(postsButton);

    var logoutButton = newElements.createSignBarButton('Log out', 'logout-button', 'images/logout.svg');
    logoutButton.addEventListener('click', function() {
      this.blur();
      setActive(this);
      Logout.init(true);
    });
    div.appendChild(logoutButton);
    Init.navbarContent.appendChild(div);

    setActive(postsButton);
    Posts.init(null);
  }

  function setActive(button) {
    activeButton = button;
    button.classList.add('navbar-active-button');
  }

  function removeActive() {
    if (activeButton) {
      activeButton.classList.remove('navbar-active-button');
      activeButton = null;
    }
  }

  return {
    init: init,
    setActive: setActive,
    removeActive: removeActive
  };
}());