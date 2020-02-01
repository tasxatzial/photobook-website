'use strict';

var AccountInfo = (function() {
  var el = {
    confirmDelete: null,
    deleteAccountMsg: null,
    deleteAccountButton: null
  };

  function init() {
    var accountSubsection = document.getElementById('account-subsection');
    accountSubsection.innerHTML = '';
    accountSubsection.appendChild(createEditAccountSection());
  }

  function editAccount() {
    Requests.cancelExcept(null);

    var data = new FormData();
    data.append("action", "AccountInfo");
    var ID = Requests.add(ajaxRequest('POST', 'Main', data, successCallback, failCallback));

    function successCallback() {
      document.getElementById('account-subsection').innerHTML = Requests.get(ID).responseText;
      var signupParent = document.getElementById('signup-parent');
      signupParent.classList.remove('parent-in-main');
      signupParent.classList.add('parent-in-myaccount');
      Signup.init('AccountInfo');
    }

    function failCallback() {
      if (Requests.get(ID).status === 401) {
        Logout.showExpired();
        return;
      }

      var error = null;
      if (Requests.get(ID).status === 400) {
        if (JSON.parse(Requests.get(ID).responseText).ERROR === 'INVALID_ACTION') {
          error = newElements.createKeyValue('Error', 'Invalid action');
        }
        else {
          error = newElements.createKeyValue('Error', 'Invalid user');
        }
      }
      else if (Requests.get(ID).status === 500) {
        error = newElements.createKeyValue('Error', 'Server error');
      }
      else if (Requests.get(ID).status === 0) {
        error = newElements.createKeyValue('Error', 'Unable to send request');
      }
      else {
        error = newElements.createKeyValue('Error', 'Unknown');
      }
      var editAccountParent = document.getElementById('edit-account-parent');
      editAccountParent.innerHTML = '';
      editAccountParent.appendChild(error);
    }
  }

  function deleteAccount() {
    Requests.cancelExcept(null);

    var formData = new FormData();
    formData.append("action", "DeleteAccount");

    var loader = newElements.createLoader('images/loader.gif');
    formMsg.showElement(el.deleteAccountMsg, loader);

    var ID = Requests.add(ajaxRequest("POST", "Main", formData, successCallback, failCallback));

    function successCallback() {
      if (JSON.parse(Requests.get(ID).responseText).ERROR) {
        el.confirmDelete.children[0].scrollIntoView();
        formMsg.showError(el.deleteAccountMsg, 'Error: Session has expired');
        var button = newElements.createFullWindowButton();
        button.addEventListener('click', function () {
          clearFullWindow();
          Logout.init();
        });
        el.confirmDelete.children[0].appendChild(button);
        return;
      }
      clearFullWindow();
      Logout.init();
    }

    function failCallback() {
      el.confirmDelete.children[0].scrollIntoView();
      formMsg.showError(el.deleteAccountMsg, 'Error');
      var button = newElements.createFullWindowButton();
      button.addEventListener('click', clearFullWindow);
      el.confirmDelete.children[0].appendChild(button);
      console.log(Requests.get(ID).responseText);
    }
  }

  function clearFullWindow() {
    var body = document.getElementsByTagName('body')[0];
    body.removeAttribute('id');
    body.removeChild(el.confirmDelete);
  }

  function showConfirmDelete() {
    var yesNoButtons = newElements.createYesNoButtons('account-delete-confirm');
    yesNoButtons.children[1].addEventListener('click', function () {
      el.confirmDelete.children[0].removeChild(yesNoButtons);
      el.deleteAccountMsg = document.createElement('p');
      el.confirmDelete.children[0].appendChild(el.deleteAccountMsg);
      deleteAccount();
    });
    yesNoButtons.children[2].addEventListener('click', clearFullWindow);

    el.confirmDelete = newElements.createFullWindow('Your account & posts will be deleted!');
    el.confirmDelete.children[0].appendChild(yesNoButtons);

    var body = document.getElementsByTagName('body')[0];
    body.id = 'full-body';
    document.getElementsByTagName('body')[0].appendChild(el.confirmDelete);
    el.confirmDelete.children[0].scrollIntoView();
  }

  function createEditAccountSection() {
    var editAccountDiv = newElements.createBlueButton('Edit Account', 'edit-account-button');
    editAccountDiv.children[0].addEventListener('click', editAccount);

    var deleteAccountDiv = newElements.createBlueButton('Delete Account', 'delete-account-button');
    el.deleteAccountButton = deleteAccountDiv.children[0];
    el.deleteAccountButton.addEventListener('click', showConfirmDelete);

    var div = document.createElement('div');
    div.id = 'edit-account-parent';
    div.className = 'parent-in-myaccount';

    div.appendChild(editAccountDiv);
    div.appendChild(deleteAccountDiv);

    var section = document.createElement('div');
    section.id = 'edit-account-section';
    section.appendChild(div);

    return section;
  }

  return {
    init: init
  };
}());