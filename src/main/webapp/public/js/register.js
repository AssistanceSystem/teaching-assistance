$(function() {
  var $email = $('#email'),
      $password = $('#password'),
      $passwordConfirm = $('#passwordConfirm'),
      $nickname = $('#nickname');

  function verifyEmail(email) {
    var EMAIL_VERIFY = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    return EMAIL_VERIFY.test(email);
  }

  function verifyPassword(password) {
    return password.length > 5 && password.length < 17;
  }

  function changeEmailStatus(email) {
    if (verifyEmail(email)) {
      $('.wrong-email').css('display', 'none');
      $email.pass = true;
    }else {
      $('.wrong-email').css('display', 'inline');
      $email.pass = false;
    }

    if($email.pass) {
      $.ajax({
        url: "/register/check",
        type: "POST",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
          loginName: email
        }),
        complete: function(data) {
          if (data.responseJSON.status == 200){
            $('.email-exist').hide();
            $('.email-exist').data('exist', false);
          }else {
            $('.email-exist').show();
            $('.email-exist').data('exist', true);
          }
        }
      });
    }
  }

  function changePasswordStatus(password) {
    if ($passwordConfirm.val()) {
      checkPasswordConfirm();
    }
    if (verifyPassword(password)) {
      $('.wrong-password').css('display', 'none');
      $password.pass = true;
    }else {
      $('.wrong-password').css('display', 'inline');
      $password.pass = false;
    }
  }

  function checkPasswordConfirm() {
    var isSame = $password.val() === $passwordConfirm.val();
    if (isSame) {
      $('.password-confirm').css('display', 'none');
      $passwordConfirm.pass = true;
    }else {
      $('.password-confirm').css('display', 'inline');
      $passwordConfirm.pass = false;
    }
  }

  function checkRegisterData(id) {
    if (id === 'email') {
      changeEmailStatus($email.val());
    }else if (id === 'password') {
      changePasswordStatus($password.val());
    }else if (id === 'passwordConfirm') {
      checkPasswordConfirm();
    }
  }

  $('input').on('blur', function (event) {
    checkRegisterData(event.target.id);
  });

  function isPass() {
    var isEmailExist = $('.email-exist').data('exist');
    return !isEmailExist && $email.pass && $password.pass && $passwordConfirm.pass;
  }

  $('#register').on('click', function() {
    changeEmailStatus($email.val());
    changePasswordStatus($password.val());
    checkPasswordConfirm();

    if (isPass()) {
      $.ajax({
        url: "/register/create",
        type: "POST",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
          loginName: $email.val(),
          password: $password.val(),
          name: $nickname.val()
        }),
        complete: function(data) {
          if (data.responseJSON.status == 200){
            console.log('注册成功');
          }else{
            console.log(data.responseJSON.message);
          }
        }
      });
    }else {
      console.log('验证不通过');
    }
  });
});
