
(function ($) {
    "use strict";


    /*==================================================================
     [ Focus input ]*/
    $('.input100').each(function () {
        $(this).on('blur', function () {
            if ($(this).val().trim() != "") {
                $(this).addClass('has-val');
            } else {
                $(this).removeClass('has-val');
            }
        })
    });

    $('.addKeywordDropdown').on('click', function () {
        if ($('.dropdown-create-keyword').hasClass("hide")) {
            $('.dropdown-create-keyword').removeClass("hide");
//            alert('had');
        } else {
            $('.dropdown-create-keyword').addClass("hide");
//            alert('hadnot');
        }
    })

    $('.search-keyword').on('input', function () {
        var keywordsinput = $('.search-keyword').val().toLowerCase();
        $('#myTable td.keywords').each(function () {
            $(this).parent().removeClass("hide");
            if ($(this).html().toLowerCase().indexOf(keywordsinput) == -1) {
                $(this).parent().addClass("hide");
            }
        });
    });

    /*==================================================================
     [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form-login').on('submit', function () {
        var check = true;

        for (var i = 0; i < input.length; i++) {
            if (validateLogin(input[i]) == false) {
                showValidate(input[i]);
                check = false;
            }
        }

        return check;
    });

    $('.validate-form-signup').on('submit', function () {
        var check = true;

        for (var i = 0; i < input.length; i++) {
            if (validateSignup(input[i]) == false) {
                showValidate(input[i]);
                check = false;
            }
        }

        return check;
    });

    $('.validate-form-login .input100').each(function () {
        $(this).focus(function () {
            hideValidate(this);
        });
    });

    $('.validate-form-signup .input100').each(function () {
        $(this).focus(function () {
            hideValidate(this);
        });
    });
    function validateSignup(input) {
        if ($(input).attr('type') == 'email' || $(input).attr('name') == 'txtEmail') {
            if ($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        } else if ($(input).attr('name') == 'txtPassword') {
            if ($(input).val().trim().match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/) == null) {
                return false;
            }
        } else if ($(input).attr('name') == 'txtPasswordConf') {
            if (!$(input).val().trim().match($("input[name=txtPassword]").val())) {
                return false;
            }
        } else {
            if ($(input).val().trim() == '') {
                return false;
            }
        }
    }

    function validateLogin(input) {

        if ($(input).val().trim() == '') {
            return false;
        }

    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }

    /*==================================================================
     [ Show pass ]*/
    var showPass = 0;
    $('.btn-show-pass').on('click', function () {
        if (showPass == 0) {
            $(this).next('input').attr('type', 'text');
            $(this).find('i').removeClass('zmdi-eye');
            $(this).find('i').addClass('zmdi-eye-off');
            showPass = 1;
        } else {
            $(this).next('input').attr('type', 'password');
            $(this).find('i').addClass('zmdi-eye');
            $(this).find('i').removeClass('zmdi-eye-off');
            showPass = 0;
        }

    });


})(jQuery);