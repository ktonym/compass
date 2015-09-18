/**
 * Created by akipkoech on 18/09/15.
 */

Ext.define('compass.view.security.UserForm',{

    extend: 'Ext.window.Window',
    alias: 'widget.user-form',
    height: 270,
    width: 600,

    requires: ['compass.util.Util','compass.util.Glyphs'],

    layout: {
        type: 'fit'
    },

    bind: {
        title: '{title}'
    },

    closable: false,
    modal: true,
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
                        labelWidth: 75
                    },
                    items: [
                        {
                            xtype: 'hiddenfield',
                            name: 'id',
                            fieldLabel: 'Label',
                            bind: '{currentUser.id}'
                        },
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
                            displayField: 'name',
                            valueField: 'id',
                            queryMode: 'local',
                            forceSelection: true,
                            editable: false,
                            name: 'idGroup',
                            bind: {
                                value: '{currentUser.idGroup}',
                                store: '{groups}',
                                selection: '{currentUser.group}'
                            }
                        },
                        {
                            xtype: 'filefield',
                            fieldLabel: 'Photo',
                            name: 'picture',
                            buttonText: 'Select Photo...',
                            afterLabelTextTpl: '',
                            listeners: {
                                change: 'onFileFieldChange'
                            }
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    title: 'Photo',
                    width: 170,
                    items: [
                        {
                            xtype: 'image',
                            reference: 'userPicture',
                            height: 150,
                            width: 150,
                            bind: {
                                src: 'resources/profileImages/{currentUser.picture}'
                            }
                        }
                    ]
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'bottom',
                    ui: 'footer',
                    layout: {
                        pack: 'end',
                        type: 'hbox'
                    },
                    items: [
                        {
                            xtype:'button',
                            text: 'Save',
                            glyph: compass.util.Glyphs.getGlyph('save'),
                            listeners: {
                                click: 'onSave'
                            }
                        },
                        {
                            xtype:'button',
                            text: 'Cancel',
                            glyph: compass.util.Glyphs.getGlyph('cancel'),
                            listeners: {
                                click: 'onCancel'
                            }
                        }
                    ]
                }
            ]
        }
    ]

});
