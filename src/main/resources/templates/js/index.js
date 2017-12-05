/* Copyright (C) 2017
* This file is part of the FAQ System.
*
* filename : default
* function :
* action   :
* version  : 0.1
* author   : MarissaMan
* date     : 2017-11-29
* modify   : 此文件如需修改,请联系MarissaMan
*/

var OFFSET = 0,
    LAST = 0,
    STYLE_TAG = 0,
    IS_SET_PAY_PWD,
    COUNTDOWN = 60;

function pageInit() {

    initBuyerData();
    initHeadImg();
    getAccountList();
    eventTrigger();
}

/**
 * 获取用户信息
 */
function initBuyerData() {
    var params = {};
    params['action'] = 'get_buyer_info';
    if (epm.c.market != null) {
        params['market_iid'] = epm.c.market['market_iid'];
    }

    epm.ajax(params, function(result) {
        if (result.ans != 'ok') {
            return;
        }

        var userName = result.data['buyer_name'],
            imgURL = epm.isEmpty(result.data['image_url']) ? 'img/userpic_2.png' : epm.v.imageURLPrefix + result.data['image_url'],
            balanceMoney = result.data['balance_money'],
            $generalText = $('.general-text');
        IS_SET_PAY_PWD = result.data['is_set_pay_pwd'];

        $('.user-name').text(userName);
        $('.user-img').attr('src', imgURL);
        $('.user-img-upload').html('<img src="' + imgURL + '" alt="" />');

        if (!balanceMoney) {
            balanceMoney = 0;
        }

        if (IS_SET_PAY_PWD == 1) {
            $generalText.text('更改支付密码');
        } else {
            $generalText.text('设置支付密码');
        }
        $('.balance-money').html(balanceMoney);
    });

}

/**
 * 初始化对账单
 */
function getAccountList() {
    var params = {};
    params['action'] = 'get_account_list';
    params['offset'] = OFFSET;
    params['rows'] = 10;
    params['style_tag'] = STYLE_TAG;

    epm.ajax(params, function(result) {
        if (result.ans != 'ok') {
            return;
        }

        var $detailList = $('.detail-list'),
            html = '',
            lastUpdateTime, inPrice, outPrice, price, styleTag;

        if (epm.isEmpty(result.data[0])) {
            if (OFFSET == 0) {
                $('.detail-content').hide();
                $('.cm-empty').show();
            }
            LAST = 1;
            OFFSET -= 10;
            return;

        } else {
            $('.detail-content').show();
            $('.cm-empty').hide();
        }

        $.each(result.data, function(key, value) {
            lastUpdateTime = epm.getDateTime(value['last_update_time']);
            inPrice = epm.getMoney(value['in_price']);
            outPrice = epm.getMoney(value['out_price']);
            price = inPrice == 0 ? '-' + outPrice : '+' + inPrice;
            styleTag = value['style_tag'];

            html += '<li class="detail-item"><div>' + lastUpdateTime + '</div>'
                + '<div>' + getStyle(styleTag) + '</div>'
                + '<div class="price-num">' + price + '</div></li>';
        });

        $detailList.html(html);

        $detailList.find('li:odd').css('background-color', 'rgba(244, 244, 244, 0.35)');

    });
}

/**
 * 收入支出状态
 * @param style_tag 钱包消费类型
 * @returns {string}
 */
function getStyle(style_tag) {
    var result = '未知';
    switch (style_tag) {
        case 0:
            result = '全部';
            break;
        case 1:
            result = '充值';
            break;
        case 2:
            result = '订单';
            break;
        case 3:
            result = '退货';
            break;
    }
    return result;
}

/**
 *  事件触发
 */
function eventTrigger() {

    var $detailMenu = $('.detail-menu'),
        $rechargeBtn = $('#rechargeBtn');

    //点击收入支出刷新页面 2收入 4支出
    $detailMenu.find('div').on('click', function() {
        var index = $(this).index();
        OFFSET = 0;
        LAST = 0;
        switch (index) {
            case 0:
                STYLE_TAG = '0';
                break;
            case 2:
                STYLE_TAG = '1,3';
                break;
            case 4:
                STYLE_TAG = '2';
                break;
            default:
                break;
        }

        $(this).addClass('text-selected').siblings().removeClass('text-selected');
        getAccountList();
    });

    // 点击充值按钮
    $rechargeBtn.on('click', function() {
        if (IS_SET_PAY_PWD == 1) {
            window.location.href = 'recharge.html';
        } else {
            setPassword();
        }
    });

}

/**
 * 设置支付密码的弹窗
 */
function setPassword() {
    var $shade = $('.cm-shade'),
        $identifyCont = $('.set-identify-cont'),
        $closeBtn = $('.i-close-btn'),
        $phone = $('.password-phone'),
        $code = $('#identifyCode'),
        $sendBtn = $('#identifySend'),
        $errorIdentifyText = $('#errorIdentifyText'),
        $nextBtn = $('#nextBtn'),
        $setPassword = $('#setPassword'),
        $ensure = $('#ensure'),
        $reSetPassword = $('#reSetPassword');

    $('.set-password').show();
    $shade.show();
    $identifyCont.show();

    // 点击关闭按钮
    $closeBtn.on('click', function() {
        window.location.reload();
    });

    // 手机号输入框事件
    $phone.on({
        click: function(e) {
            $('input').removeClass('input-active');
            $phone.addClass('input-active');
            e.stopPropagation();
        },
        input: function() {
            var phoneNum = $phone.val();

            if (phoneNum.length > 11) {
                $('#errorPhoneNum').show().text('请输入11位手机号');
                $(this).val($(this).val().substr(0, 11));
            } else if (phoneNum.length == 11) {
                $sendBtn.addClass('identify-send-active').removeAttr('disabled');

                var codeNum = $code.val();
                if (codeNum.length == 4) {
                    $nextBtn.addClass('cm-btn-active');
                } else {
                    $nextBtn.removeClass('cm-btn-active');
                }
            } else {
                $sendBtn.removeClass('identify-send-active').attr('value', '发送验证码');
                $nextBtn.removeClass('cm-btn-active');
            }
        }
    });

    // 发送验证码按钮事件
    $sendBtn.on('click', function() {
        if ($sendBtn.hasClass('identify-send-active')) {
            if ($phone.val() != epm.c.buyer.mobile) {
                $('#errorPhone').show().find('#errorPhoneNum').text('请输入注册手机号码');
            } else {
                var params = {};
                params['action'] = 'send_random_code';
                params['mobile'] = $phone.val();
                epm.ajax(params, function(result) {
                    if (result.ans == 'ok') {
                        $errorIdentifyText.text('验证码发送成功').parent('#errorIdentify').show();
                        $sendBtn.removeClass('identify-send-active');
                        setTime();
                    } else {
                        $errorIdentifyText.text('验证码发送失败,请再次点击发送').parent('#errorIdentify').show();
                    }
                });
            }
        }
    });

    // 验证码输入框事件
    $code.on({
        click: function(e) {
            $('input').removeClass('input-active');
            $code.addClass('input-active');
            e.stopPropagation();
        },
        input: function() {
            var phoneNum = $phone.val(),
                codeNum = $code.val();
            if (codeNum.length == 4 && phoneNum.length == 11) {
                $nextBtn.addClass('cm-btn-active').removeAttr('disabled');
            } else {
                $nextBtn.removeClass('cm-btn-active').attr('disabled');
            }
        }
    });

    // 点击下一步按钮事件
    $nextBtn.on('click', function() {

        var params = {};
        params['action'] = 'check_random_code';
        params['mobile'] = $phone.val();
        params['random_code'] = $code.val();
        epm.ajax(params, function(result) {
            if (result.ans == 'ok') {
                $('#setPasswordBg').addClass('set-password-select');
                $nextBtn.parents('.set-identify-cont').hide().siblings('.set-password-cont').show();
            }
        });

        // 输入密码
        $setPassword.on({
            click: function(e) {
                $('input').removeClass('input-active');
                $(this).addClass('input-active');
                e.stopPropagation();
            },
            input: function() {
                if ($(this).val().length > 6) {
                    $('#setPasswordText').show().find('#setPasswordSix').text('请输入6位密码');
                    $(this).val($(this).val().substr(0, 6));
                } else if ($reSetPassword.val().length == 6 && $(this).val() != $reSetPassword.val()) {
                    $('#setPasswordText').show().find('#setPasswordSix').text('密码不一致,请重新输入');
                    $ensure.removeClass('cm-btn-active');
                } else {
                    $('#reSetPasswordText').hide();
                    $('#setPasswordText').hide();
                    $ensure.addClass('cm-btn-active');
                }
            }
        });

        // 重新输入密码
        $reSetPassword.on({
            click: function(e) {
                $('input').removeClass('input-active');
                $(this).addClass('input-active');
                e.stopPropagation();
            },
            input: function() {
                if ($(this).val().length > 6) {

                    $(this).val($(this).val().substr(0, 6));

                } else if ($(this).val().length == 6) {

                    if ($(this).val() != $setPassword.val()) {
                        $('#reSetPasswordText').show().find('#reSetPasswordTextSix').text('密码不一致,请重新输入');
                        $ensure.removeClass('cm-btn-active');

                    } else {

                        $('#reSetPasswordText').hide();
                        $('#setPasswordText').hide();
                        $ensure.addClass('cm-btn-active');

                    }
                } else {
                    $ensure.removeClass('cm-btn-active');
                }
            }
        });

        // 输入密码确定按钮事件
        $ensure.on('click', function() {
            if ($(this).hasClass('cm-btn-active')) {
                var params = {};
                params['action'] = 'set_pay_pwd';
                params['buyer_pay_pwd'] = $reSetPassword.val();
                epm.ajax(params, function(result) {
                    if (result.ans == 'ok') {
                        $('.set-password').hide();
                        $('.password-success').show();
                        $('#goBackThis').on('click', function() {
                            window.location.reload();
                        });
                    }
                });
            }
        });
    });
}

/**
 * 验证码倒计时
 * @type {number}
 */
function setTime() {
    var $sendBtn = $('.identify-send');
    if ($('#phone').val().length == 11) {
        if (COUNTDOWN == 0) {
            COUNTDOWN = 60;
            $sendBtn.attr('value', '发送验证码').addClass('identify-send-active').removeAttr('disabled');
        } else {
            $sendBtn.attr({'disabled': 'disabled', 'value': COUNTDOWN + 's后重新发送'});
            COUNTDOWN--;
            setTimeout(function() {
                setTime()
            }, 1000);
        }
    } else {
        $sendBtn.attr('value', '发送验证码');
        COUNTDOWN = 60;
    }
}

/**
 * 分页
 * @param action
 */
function flip(action) {
    if ('pre' == action) {
        //上一页
        if (OFFSET == 0) {
            return;
        }
        LAST = 0;
        OFFSET -= 10;
    } else if ('next' == action) {
        //下一页
        if (LAST == 1) {
            return;
        }
        OFFSET += 10;
    }

    getAccountList();
}

