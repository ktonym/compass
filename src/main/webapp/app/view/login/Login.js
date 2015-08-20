Ext.define('compass.view.login.Login',{
    extend: 'Ext.window.Window',

    xtype: 'login-dialog',

    controller: 'login',

    requires: ['compass.view.login.LoginController',
                'compass.view.locale.Translation'],

    autoShow: true,
    height: 170,
    width: 360,
    layout: {
        type: 'fit'
    },
    iconCls: 'fa fa-key fa-lg',
    title: translations.login,
    closeAction: 'hide',
    closable: false,
    draggable: false,
    resizable: false,

    items: [
        {
            xtype: 'form',
            reference: 'form',
            bodyPadding: 15,
            defaults: {
                xtype: 'textfield',
                anchor: '100%',
                labelWidth: translations.labelWidth,
                allowBlank: false,
                vtype: 'alphanum',
                minLength: 3,
                msgTarget: 'under',
                listeners: {
                    specialKey: 'onTextFieldSpecialKey'
                }
            },
            items: [
                {
                    name: 'j_username',
                    fieldLabel: translations.user,
                    maxLength: 25
                },
                {
                    inputType: 'password',
                    name: 'j_password',
                    fieldLabel: translations.password,
                    maxLength: 15,
                    msgTarget: 'side',
                    vtype: 'customPass',
                    id: 'password',
                    enableKeyEvents: true,
                    listeners: {
                        keypress: 'onTextFieldKeyPress',
                        specialKey: 'onTextFieldSpecialKey'
                    }
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'bottom',
                    items: [
                        {
                            xtype:'translation'
                        },
                        {
                            xtype: 'tbfill'
                        },
                        {
                            xtype: 'button',
                            iconCls: 'fa fa-times fa-lg',
                            text: translations.cancel,
                            listeners: {
                                click: 'onButtonClickCancel'
                            }
                        },
                        {
                            xtype: 'button',
                            formBind: true,
                            iconCls: 'fa fa-sign-in fa-lg',
                            text: translations.submit,
                            listeners: {
                                click: 'onButtonClickSubmit'
                            }
                        }
                    ]
                }
            ]

        }
    ]

});
