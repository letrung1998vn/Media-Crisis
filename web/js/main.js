
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

    $('.btn-add-new-keyword').on('click', function () {
        if ($('.keyword-input').val() == "") {
            alert('null');
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

    $('.validate-form-add-keyword').on('submit', function () {
        var check = true;
        for (var i = 0; i < input.length; i++) {
            if (validateAddKeyword(input[i]) == false) {
                check = false;
            }
        }

        return check;
    });

    $('.validate-form-add-keyword-admin').on('submit', function () {
        var check = true;
        for (var i = 0; i < input.length; i++) {
            if (validateAddKeywordAdmin(input[i]) == false) {
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

    $('.validate-form-search-keyword-admin').on('submit', function () {
        var check = true;
        input = $('.username');
        var input1 = $('.username-details');

        for (var i = 0; i < input.length; i++) {
            if (validateInputUser(input[i]) == false) {
                check = false;
            }
        }
        if (check) {
            check = false;
            input1.each(function () {
                if ($(this).val().toLowerCase() == input.val()) {
                    check = true;
                }
            });
            if (!check) {
                $.notify({
                icon: "pe-7s-bell",
                message: 'Username you have inputed is not existed!'

            }, {
                type: type[4],
                timer: 4000,
                placement: {
                    from: 'top',
                    align: 'left'
                }
            });
            }
        }
        return check;
    });

    function validateInputUser(input) {

        if ($(input).val().trim() == '') {
            $.notify({
                icon: "pe-7s-bell",
                message: 'This field is empty, can not search'

            }, {
                type: type[4],
                timer: 4000,
                placement: {
                    from: 'top',
                    align: 'left'
                }
            });
            return false;
        }

    }

    function validateAddKeyword(input) {

        if ($(input).val().trim() == '') {
            $.notify({
                icon: "pe-7s-bell",
                message: 'Keyword field is empty, can not add'

            }, {
                type: type[4],
                timer: 4000,
                placement: {
                    from: 'top',
                    align: 'left'
                }
            });
            return false;
        } else {
            var keywordsinput = $('.search-keyword').val().toLowerCase();
            var checkExist = true;
            var numberOfKeyword;
            $('#myTable td.keywordsNo').each(function () {
                numberOfKeyword = $(this).html();
            });
            if (numberOfKeyword >= 10) {
                $.notify({
                    icon: "pe-7s-bell",
                    message: 'You have reached the limit for the number of keywords! Please contact admin for more infomation'

                }, {
                    type: type[4],
                    timer: 4000,
                    placement: {
                        from: 'top',
                        align: 'left'
                    }
                });
                return false;
            } else {
                $('#myTable td.keywords').each(function () {
                    if ($(this).html().toLowerCase() == $(input).val().trim()) {
                        checkExist = false;
                    }
                });
                if (!checkExist) {
                    $.notify({
                        icon: "pe-7s-bell",
                        message: 'This keyword is existed!'

                    }, {
                        type: type[4],
                        timer: 4000,
                        placement: {
                            from: 'top',
                            align: 'left'
                        }
                    });
                    return false;
                }
            }
        }
    }

    function validateAddKeywordAdmin(input) {

        if ($(input).val().trim() == '') {
            $.notify({
                icon: "pe-7s-bell",
                message: 'Keyword field is empty, can not add'

            }, {
                type: type[4],
                timer: 4000,
                placement: {
                    from: 'top',
                    align: 'left'
                }
            });
            return false;
        } else {
            var keywordsinput = $('.search-keyword').val().toLowerCase();
            var checkExist = true;

            $('#myTable td.keywords').each(function () {
                if ($(this).html().toLowerCase() == $(input).val().trim()) {
                    checkExist = false;
                }
            });
            if (!checkExist) {
                $.notify({
                    icon: "pe-7s-bell",
                    message: 'This keyword is existed!'

                }, {
                    type: type[4],
                    timer: 4000,
                    placement: {
                        from: 'top',
                        align: 'left'
                    }
                });
                return false;
            }
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