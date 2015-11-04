

Ext.define('compass.view.security.UserForm',{

    extend: 'compass.view.base.WindowForm',
    alias: 'widget.user-form',
    height: 290,
    width: 600,

    items: [
        {
            xtype: 'form',
            reference: 'form',
            bodyPadding: 5,
            modelValidation: true,
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'fieldset',
                    flex: 1,
                    title: 'User Information',
                    layout: 'anchor',
                    defaults: {
                        afterLabelTextTpl: compass.util.Util.required,
                        anchor: '100%',
                        xtype: 'textfield',
                        msgTarget: 'side',
                        labelWidth: 85
                    },
                    items: [
//                        {
//                            xtype: 'hiddenfield',
//                            name: 'id',
//                            fieldLabel: 'Label',
//                            bind: '{currentUser.idGroup}'
//                        },
                        {
                            fieldLabel: 'Username',
                            name: 'username',
                            bind: '{currentUser.username}'
                        },
                        {
                            fieldLabel: 'First Name',
                            name: 'firstName',
                            bind: '{currentUser.firstName}'
                        },
                        {
                            fieldLabel: 'Last Name',
                            name: 'lastName',
                            bind: '{currentUser.lastName}'
                        },
                        {
                            fieldLabel: 'Email',
                            name: 'email',
                            bind: '{currentUser.email}'
                        },
                        {
                            xtype: 'combo',
                            fieldLabel: 'Group',
                            displayField: 'groupName',
                            valueField: 'idGroup',
                            queryMode: 'local',
                            forceSelection: true,
                            editable: false,
                            name: 'idGroup',
                            bind: {
                                value: '{currentUser.idGroup}',
                                store: '{groups}',
                                selection: '{currentUser.group}'
                            }
                        }//,
//                        {
//                            xtype: 'filefield',
//                            fieldLabel: 'Photo',
//                            name: 'picture',
//                            buttonText: 'Browse',
//                            afterLabelTextTpl: '',
//                            listeners: {
//                                change: 'onFileFieldChange'
//                            }
//                        }
                    ]
                }
//                {
//                    xtype: 'fieldset',
//                    title: 'Photo',
//                    width: 170,
//                    items: [
//                        {
//                            xtype: 'image',
//                            reference: 'userPicture',
//                            height: 150,
//                            width: 150//,
////                            bind: {
////                                src: 'resources/profileImages/{currentUser.picture}'
////                            }
//                        }
//                    ]
//                }
            ]

        }
    ]

});
